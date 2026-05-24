package com.nomad.mapper;

import com.nomad.dto.BundleDto;
import com.nomad.entity.Bundle;
import org.springframework.stereotype.Component;

@Component
public class BundleMapper {

    public BundleDto toDto(Bundle b) {
        if (b == null) return null;
        BundleDto dto = new BundleDto();
        dto.setId(b.getId());
        dto.setName(b.getName());
        dto.setDescription(b.getDescription());
        dto.setFlightId(b.getFlight() != null ? b.getFlight().getId() : null);
        dto.setFlightAirline(b.getFlight() != null ? b.getFlight().getAirline() : null);
        dto.setAccommodationId(b.getAccommodation() != null ? b.getAccommodation().getId() : null);
        dto.setAccommodationName(b.getAccommodation() != null ? b.getAccommodation().getName() : null);
        dto.setCoworkingSpaceId(b.getCoworkingSpace() != null ? b.getCoworkingSpace().getId() : null);
        dto.setCoworkingSpaceName(b.getCoworkingSpace() != null ? b.getCoworkingSpace().getName() : null);
        dto.setCityId(b.getCity() != null ? b.getCity().getId() : null);
        dto.setCityName(b.getCity() != null ? b.getCity().getName() : null);
        dto.setTotalPrice(b.getTotalPrice());
        dto.setDiscount(b.getDiscount());
        dto.setCurrency(b.getCurrency());
        return dto;
    }
}
