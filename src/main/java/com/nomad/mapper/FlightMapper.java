package com.nomad.mapper;

import com.nomad.dto.FlightDto;
import com.nomad.entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    public FlightDto toDto(Flight f) {
        if (f == null) return null;
        FlightDto dto = new FlightDto();
        dto.setId(f.getId());
        dto.setAirline(f.getAirline());
        dto.setOriginCityId(f.getOriginCity() != null ? f.getOriginCity().getId() : null);
        dto.setOriginCityName(f.getOriginCity() != null ? f.getOriginCity().getName() : null);
        dto.setDestinationCityId(f.getDestinationCity() != null ? f.getDestinationCity().getId() : null);
        dto.setDestinationCityName(f.getDestinationCity() != null ? f.getDestinationCity().getName() : null);
        dto.setDepartureDate(f.getDepartureDate());
        dto.setArrivalDate(f.getArrivalDate());
        dto.setPrice(f.getPrice());
        dto.setCurrency(f.getCurrency());
        dto.setDurationMinutes(f.getDurationMinutes());
        return dto;
    }
}
