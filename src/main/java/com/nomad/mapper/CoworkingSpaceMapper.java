package com.nomad.mapper;

import com.nomad.dto.CoworkingSpaceDto;
import com.nomad.entity.CoworkingSpace;
import org.springframework.stereotype.Component;

@Component
public class CoworkingSpaceMapper {

    public CoworkingSpaceDto toDto(CoworkingSpace s) {
        if (s == null) return null;
        CoworkingSpaceDto dto = new CoworkingSpaceDto();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setDescription(s.getDescription());
        dto.setPricePerDay(s.getPricePerDay());
        dto.setCurrency(s.getCurrency());
        dto.setCityId(s.getCity() != null ? s.getCity().getId() : null);
        dto.setCityName(s.getCity() != null ? s.getCity().getName() : null);
        dto.setAmenities(s.getAmenities());
        dto.setRating(s.getRating());
        dto.setHasHighSpeedInternet(s.isHasHighSpeedInternet());
        dto.setOpen247(s.isOpen247());
        return dto;
    }
}
