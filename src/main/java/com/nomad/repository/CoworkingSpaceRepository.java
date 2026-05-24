package com.nomad.repository;

import com.nomad.entity.City;
import com.nomad.entity.CoworkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CoworkingSpaceRepository extends JpaRepository<CoworkingSpace, Long> {
    List<CoworkingSpace> findByCity(City city);
    List<CoworkingSpace> findByCityAndPricePerDayLessThanEqual(City city, BigDecimal maxPrice);
    List<CoworkingSpace> findByHasHighSpeedInternetTrue();
}
