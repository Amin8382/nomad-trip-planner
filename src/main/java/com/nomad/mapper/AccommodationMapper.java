package com.nomad.mapper;

import com.nomad.dto.AccommodationDto;
import com.nomad.entity.Accommodation;
import org.springframework.stereotype.Component;

@Component
public class AccommodationMapper {

    public AccommodationDto toDto(Accommodation a) {
        if (a == null) return null;
        AccommodationDto dto = new AccommodationDto();
        dto.setId(a.getId());
        dto.setName(a.getName());
        dto.setDescription(a.getDescription());
        dto.setType(a.getType() != null ? a.getType().name() : null);
        dto.setPricePerNight(a.getPricePerNight());
        dto.setCurrency(a.getCurrency());
        dto.setCityId(a.getCity() != null ? a.getCity().getId() : null);
        dto.setCityName(a.getCity() != null ? a.getCity().getName() : null);
        dto.setAmenities(a.getAmenities());
        dto.setRating(a.getRating());
        dto.setAvailable(a.isAvailable());
        return dto;
    }
}
