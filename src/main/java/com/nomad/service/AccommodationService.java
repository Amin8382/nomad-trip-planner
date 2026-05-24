package com.nomad.service;

import com.nomad.dto.AccommodationDto;
import com.nomad.entity.Accommodation;
import com.nomad.entity.City;
import com.nomad.mapper.AccommodationMapper;
import com.nomad.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;
    private final CityService cityService;

    public AccommodationService(AccommodationRepository accommodationRepository,
                                AccommodationMapper accommodationMapper,
                                CityService cityService) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationMapper = accommodationMapper;
        this.cityService = cityService;
    }

    public List<AccommodationDto> findByCity(Long cityId) {
        City city = cityService.getCityEntity(cityId);
        return accommodationRepository.findByCityAndAvailableTrue(city).stream()
                .map(accommodationMapper::toDto).toList();
    }

    public List<AccommodationDto> findByCityAndMaxPrice(Long cityId, BigDecimal maxPrice) {
        City city = cityService.getCityEntity(cityId);
        return accommodationRepository.findByCityAndPricePerNightLessThanEqual(city, maxPrice).stream()
                .map(accommodationMapper::toDto).toList();
    }

    public AccommodationDto create(AccommodationDto dto) {
        Accommodation accommodation = new Accommodation();
        accommodation.setName(dto.getName());
        accommodation.setDescription(dto.getDescription());
        accommodation.setPricePerNight(dto.getPricePerNight());
        accommodation.setCurrency(dto.getCurrency());
        accommodation.setCity(cityService.getCityEntity(dto.getCityId()));
        accommodation.setAmenities(dto.getAmenities());
        accommodation.setRating(dto.getRating());
        accommodation.setAvailable(dto.isAvailable());
        return accommodationMapper.toDto(accommodationRepository.save(accommodation));
    }
}
