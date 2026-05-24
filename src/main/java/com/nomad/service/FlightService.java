package com.nomad.service;

import com.nomad.dto.FlightDto;
import com.nomad.entity.City;
import com.nomad.entity.Flight;
import com.nomad.mapper.FlightMapper;
import com.nomad.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final CityService cityService;

    public FlightService(FlightRepository flightRepository,
                         FlightMapper flightMapper,
                         CityService cityService) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.cityService = cityService;
    }

    public List<FlightDto> findFlightsBetween(Long originId, Long destinationId) {
        City origin = cityService.getCityEntity(originId);
        City destination = cityService.getCityEntity(destinationId);
        return flightRepository.findByOriginCityAndDestinationCity(origin, destination).stream()
                .map(flightMapper::toDto).toList();
    }

    public List<FlightDto> findFlightsBetweenMaxPrice(Long originId, Long destinationId, BigDecimal maxPrice) {
        City origin = cityService.getCityEntity(originId);
        City destination = cityService.getCityEntity(destinationId);
        return flightRepository.findByOriginCityAndDestinationCityAndPriceLessThanEqual(origin, destination, maxPrice)
                .stream().map(flightMapper::toDto).toList();
    }

    public List<FlightDto> findFlightsToCity(Long cityId) {
        City city = cityService.getCityEntity(cityId);
        return flightRepository.findByDestinationCity(city).stream()
                .map(flightMapper::toDto).toList();
    }

    public FlightDto create(FlightDto dto) {
        Flight flight = new Flight();
        flight.setAirline(dto.getAirline());
        flight.setOriginCity(cityService.getCityEntity(dto.getOriginCityId()));
        flight.setDestinationCity(cityService.getCityEntity(dto.getDestinationCityId()));
        flight.setDepartureDate(dto.getDepartureDate());
        flight.setArrivalDate(dto.getArrivalDate());
        flight.setPrice(dto.getPrice());
        flight.setCurrency(dto.getCurrency());
        flight.setDurationMinutes(dto.getDurationMinutes());
        return flightMapper.toDto(flightRepository.save(flight));
    }
}
