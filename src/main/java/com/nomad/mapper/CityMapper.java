package com.nomad.mapper;

import com.nomad.dto.CityDto;
import com.nomad.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public CityDto toDto(City city) {
        if (city == null) return null;
        CityDto dto = new CityDto();
        dto.setId(city.getId());
        dto.setName(city.getName());
        dto.setCountry(city.getCountry());
        dto.setTimezone(city.getTimezone());
        dto.setCostIndex(city.getCostIndex());
        dto.setLatitude(city.getLatitude());
        dto.setLongitude(city.getLongitude());
        dto.setDescription(city.getDescription());
        return dto;
    }

    public City toEntity(CityDto dto) {
        if (dto == null) return null;
        City city = new City();
        city.setId(dto.getId());
        city.setName(dto.getName());
        city.setCountry(dto.getCountry());
        city.setTimezone(dto.getTimezone());
        city.setCostIndex(dto.getCostIndex());
        city.setLatitude(dto.getLatitude());
        city.setLongitude(dto.getLongitude());
        city.setDescription(dto.getDescription());
        return city;
    }
}
