package com.nomad.repository;

import com.nomad.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findByCountryContainingIgnoreCase(String country);
    List<City> findByNameContainingIgnoreCase(String name);
    List<City> findByCostIndexLessThanEqual(Double maxCostIndex);
}
