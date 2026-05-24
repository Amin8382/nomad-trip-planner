package com.nomad.service;

import com.nomad.entity.*;
import com.nomad.repository.AccommodationRepository;
import com.nomad.repository.CoworkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;

@Service
public class BudgetCalculator {

    private final AccommodationRepository accommodationRepository;
    private final CoworkingSpaceRepository coworkingSpaceRepository;
    private final Random random = new Random();

    public BudgetCalculator(AccommodationRepository accommodationRepository,
                            CoworkingSpaceRepository coworkingSpaceRepository) {
        this.accommodationRepository = accommodationRepository;
        this.coworkingSpaceRepository = coworkingSpaceRepository;
    }

    public City pickCity(List<City> cities, Vibe vibe, int dayIndex, int totalDays) {
        if (cities.isEmpty()) {
            throw new RuntimeException("No cities available");
        }

        switch (vibe) {
            case FAST -> {
                int cityIndex = (dayIndex * cities.size()) / totalDays;
                return cities.get(Math.min(cityIndex, cities.size() - 1));
            }
            case CHILL -> {
                return cities.get(0);
            }
            default -> {
                if (dayIndex < totalDays / 2) {
                    return cities.get(0);
                }
                return cities.get(Math.min(1, cities.size() - 1));
            }
        }
    }

    public Accommodation pickAccommodation(City city, BigDecimal dailyBudget, Vibe vibe) {
        List<Accommodation> available = accommodationRepository.findByCityAndAvailableTrue(city);

        BigDecimal maxPrice = switch (vibe) {
            case CHILL -> dailyBudget.multiply(BigDecimal.valueOf(0.6));
            case BALANCED -> dailyBudget.multiply(BigDecimal.valueOf(0.4));
            case FAST -> dailyBudget.multiply(BigDecimal.valueOf(0.25));
        };

        List<Accommodation> affordable = available.stream()
                .filter(a -> a.getPricePerNight().compareTo(maxPrice) <= 0)
                .toList();

        if (affordable.isEmpty()) {
            return available.isEmpty() ? null : available.get(0);
        }
        return affordable.get(random.nextInt(affordable.size()));
    }

    public CoworkingSpace pickCoworking(City city, BigDecimal dailyBudget, Vibe vibe) {
        List<CoworkingSpace> spaces = coworkingSpaceRepository.findByCity(city);

        BigDecimal maxPrice = switch (vibe) {
            case CHILL -> dailyBudget.multiply(BigDecimal.valueOf(0.15));
            case BALANCED -> dailyBudget.multiply(BigDecimal.valueOf(0.2));
            case FAST -> dailyBudget.multiply(BigDecimal.valueOf(0.3));
        };

        List<CoworkingSpace> affordable = spaces.stream()
                .filter(s -> s.getPricePerDay().compareTo(maxPrice) <= 0)
                .toList();

        if (affordable.isEmpty()) {
            return spaces.isEmpty() ? null : spaces.get(0);
        }
        return affordable.get(random.nextInt(affordable.size()));
    }

    public int workDaysNeeded(long totalDays, Vibe vibe) {
        return switch (vibe) {
            case FAST -> (int) Math.ceil(totalDays * 0.8);
            case BALANCED -> (int) Math.ceil(totalDays * 0.5);
            case CHILL -> (int) Math.ceil(totalDays * 0.2);
        };
    }

    public BigDecimal estimateTotalCost(Trip trip) {
        BigDecimal total = BigDecimal.ZERO;

        for (ItineraryDay day : trip.getItineraryDays()) {
            if (day.getAccommodation() != null) {
                total = total.add(day.getAccommodation().getPricePerNight());
            }
            if (day.getCoworkingSpace() != null) {
                total = total.add(day.getCoworkingSpace().getPricePerDay());
            }
        }

        return total;
    }

    public boolean isWithinBudget(Trip trip) {
        if (trip.getTotalBudget() == null) return true;
        return estimateTotalCost(trip).compareTo(trip.getTotalBudget()) <= 0;
    }
}
