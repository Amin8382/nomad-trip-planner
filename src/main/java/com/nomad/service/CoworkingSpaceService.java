package com.nomad.service;

import com.nomad.dto.CoworkingSpaceDto;
import com.nomad.entity.City;
import com.nomad.entity.CoworkingSpace;
import com.nomad.mapper.CoworkingSpaceMapper;
import com.nomad.repository.CoworkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CoworkingSpaceService {

    private final CoworkingSpaceRepository coworkingSpaceRepository;
    private final CoworkingSpaceMapper coworkingSpaceMapper;
    private final CityService cityService;

    public CoworkingSpaceService(CoworkingSpaceRepository coworkingSpaceRepository,
                                 CoworkingSpaceMapper coworkingSpaceMapper,
                                 CityService cityService) {
        this.coworkingSpaceRepository = coworkingSpaceRepository;
        this.coworkingSpaceMapper = coworkingSpaceMapper;
        this.cityService = cityService;
    }

    public List<CoworkingSpaceDto> findByCity(Long cityId) {
        City city = cityService.getCityEntity(cityId);
        return coworkingSpaceRepository.findByCity(city).stream()
                .map(coworkingSpaceMapper::toDto).toList();
    }

    public List<CoworkingSpaceDto> findByCityAndMaxPrice(Long cityId, BigDecimal maxPrice) {
        City city = cityService.getCityEntity(cityId);
        return coworkingSpaceRepository.findByCityAndPricePerDayLessThanEqual(city, maxPrice).stream()
                .map(coworkingSpaceMapper::toDto).toList();
    }

    public CoworkingSpaceDto create(CoworkingSpaceDto dto) {
        CoworkingSpace space = new CoworkingSpace();
        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        space.setPricePerDay(dto.getPricePerDay());
        space.setCurrency(dto.getCurrency());
        space.setCity(cityService.getCityEntity(dto.getCityId()));
        space.setAmenities(dto.getAmenities());
        space.setRating(dto.getRating());
        space.setHasHighSpeedInternet(dto.isHasHighSpeedInternet());
        space.setOpen247(dto.isOpen247());
        return coworkingSpaceMapper.toDto(coworkingSpaceRepository.save(space));
    }
}
