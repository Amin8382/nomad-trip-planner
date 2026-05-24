package com.nomad.repository;

import com.nomad.entity.Bundle;
import com.nomad.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface BundleRepository extends JpaRepository<Bundle, Long> {
    List<Bundle> findByCity(City city);
    List<Bundle> findByTotalPriceLessThanEqual(BigDecimal maxPrice);
}
