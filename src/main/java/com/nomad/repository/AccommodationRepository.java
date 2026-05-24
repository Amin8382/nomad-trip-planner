package com.nomad.repository;

import com.nomad.entity.Accommodation;
import com.nomad.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findByCity(City city);
    List<Accommodation> findByCityAndAvailableTrue(City city);
    List<Accommodation> findByCityAndPricePerNightLessThanEqual(City city, BigDecimal maxPrice);
    List<Accommodation> findByCityAndType(City city, com.nomad.entity.AccommodationType type);
}
