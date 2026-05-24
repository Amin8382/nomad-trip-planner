package com.nomad.service;

import com.nomad.dto.CreateTripRequest;
import com.nomad.dto.ItineraryDayDto;
import com.nomad.dto.TripDto;
import com.nomad.entity.*;
import com.nomad.mapper.ItineraryDayMapper;
import com.nomad.mapper.TripMapper;
import com.nomad.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final ItineraryDayMapper itineraryDayMapper;
    private final CityService cityService;
    private final AccommodationService accommodationService;
    private final CoworkingSpaceService coworkingSpaceService;
    private final FlightService flightService;
    private final BudgetCalculator budgetCalculator;

    public TripService(TripRepository tripRepository,
                       TripMapper tripMapper,
                       ItineraryDayMapper itineraryDayMapper,
                       CityService cityService,
                       AccommodationService accommodationService,
                       CoworkingSpaceService coworkingSpaceService,
                       FlightService flightService,
                       BudgetCalculator budgetCalculator) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
        this.itineraryDayMapper = itineraryDayMapper;
        this.cityService = cityService;
        this.accommodationService = accommodationService;
        this.coworkingSpaceService = coworkingSpaceService;
        this.flightService = flightService;
        this.budgetCalculator = budgetCalculator;
    }

    public List<TripDto> findByUser(User user) {
        return tripRepository.findByUser(user).stream()
                .map(tripMapper::toDto).toList();
    }

    public TripDto findById(Long id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found: " + id));
        return tripMapper.toDto(trip);
    }

    public TripDto createTrip(User user, CreateTripRequest request) {
        long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()) + 1;
        if (days <= 0) {
            throw new RuntimeException("End date must be after start date");
        }

        Vibe vibe;
        try {
            vibe = Vibe.valueOf(request.getVibe().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid vibe. Use CHILL, BALANCED, or FAST");
        }

        Trip trip = new Trip();
        trip.setUser(user);
        trip.setName(request.getName());
        trip.setVibe(vibe);
        trip.setTotalBudget(request.getTotalBudget());
        trip.setCurrency(request.getCurrency() != null ? request.getCurrency() : "USD");
        trip.setStartDate(request.getStartDate());
        trip.setEndDate(request.getEndDate());

        Trip savedTrip = tripRepository.save(trip);

        List<ItineraryDay> itineraryDays = generateItinerary(savedTrip, request.getOriginCityId());
        savedTrip.setItineraryDays(itineraryDays);

        return tripMapper.toDto(tripRepository.save(savedTrip));
    }

    private List<ItineraryDay> generateItinerary(Trip trip, Long originCityId) {
        long totalDays = ChronoUnit.DAYS.between(trip.getStartDate(), trip.getEndDate()) + 1;
        List<City> availableCities = cityService.findAll().stream()
                .map(dto -> cityService.getCityEntity(dto.getId()))
                .toList();

        if (availableCities.isEmpty()) {
            throw new RuntimeException("No cities available for itinerary generation");
        }

        BigDecimal dailyBudget = trip.getTotalBudget().divide(BigDecimal.valueOf(totalDays), 2, RoundingMode.HALF_DOWN);

        List<ItineraryDay> days = new ArrayList<>();

        for (int i = 0; i < totalDays; i++) {
            ItineraryDay day = new ItineraryDay();
            day.setTrip(trip);
            day.setDayNumber(i + 1);
            day.setDate(trip.getStartDate().plusDays(i));

            City city = budgetCalculator.pickCity(availableCities, trip.getVibe(), i, (int) totalDays);
            day.setCity(city);

            Accommodation accommodation = budgetCalculator.pickAccommodation(city, dailyBudget, trip.getVibe());
            day.setAccommodation(accommodation);

            int workDaysNeeded = budgetCalculator.workDaysNeeded(totalDays, trip.getVibe());
            if (i < workDaysNeeded) {
                CoworkingSpace coworking = budgetCalculator.pickCoworking(city, dailyBudget, trip.getVibe());
                day.setCoworkingSpace(coworking);
            }

            days.add(day);
        }

        return days;
    }

    public TripDto updateStatus(Long tripId, TripStatus status) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found: " + tripId));
        trip.setStatus(status);
        return tripMapper.toDto(tripRepository.save(trip));
    }

    public void deleteTrip(Long tripId) {
        tripRepository.deleteById(tripId);
    }
}
