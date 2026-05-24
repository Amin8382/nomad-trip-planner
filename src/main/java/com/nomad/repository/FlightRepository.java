package com.nomad.repository;

import com.nomad.entity.City;
import com.nomad.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByOriginCityAndDestinationCity(City origin, City destination);
    List<Flight> findByOriginCityAndDestinationCityAndPriceLessThanEqual(City origin, City destination, BigDecimal maxPrice);
    List<Flight> findByDestinationCity(City destination);
    List<Flight> findByDepartureDateBetween(LocalDateTime from, LocalDateTime to);
}
