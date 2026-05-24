package com.nomad.service;

import com.nomad.dto.CityDto;
import com.nomad.entity.City;
import com.nomad.mapper.CityMapper;
import com.nomad.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public List<CityDto> findAll() {
        return cityRepository.findAll().stream().map(cityMapper::toDto).toList();
    }

    public CityDto findById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found: " + id));
        return cityMapper.toDto(city);
    }

    public CityDto create(CityDto dto) {
        City city = cityMapper.toEntity(dto);
        return cityMapper.toDto(cityRepository.save(city));
    }

    public List<CityDto> searchByName(String name) {
        return cityRepository.findByNameContainingIgnoreCase(name).stream()
                .map(cityMapper::toDto).toList();
    }

    public List<CityDto> findByMaxCostIndex(Double maxCost) {
        return cityRepository.findByCostIndexLessThanEqual(maxCost).stream()
                .map(cityMapper::toDto).toList();
    }

    public City getCityEntity(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not found: " + id));
    }
}
