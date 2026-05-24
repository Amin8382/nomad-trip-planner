package com.nomad.mapper;

import com.nomad.dto.ItineraryDayDto;
import com.nomad.entity.ItineraryDay;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDayMapper {

    public ItineraryDayDto toDto(ItineraryDay day) {
        if (day == null) return null;
        ItineraryDayDto dto = new ItineraryDayDto();
        dto.setId(day.getId());
        dto.setDayNumber(day.getDayNumber());
        dto.setDate(day.getDate());
        dto.setCityId(day.getCity() != null ? day.getCity().getId() : null);
        dto.setCityName(day.getCity() != null ? day.getCity().getName() : null);
        dto.setAccommodationId(day.getAccommodation() != null ? day.getAccommodation().getId() : null);
        dto.setAccommodationName(day.getAccommodation() != null ? day.getAccommodation().getName() : null);
        dto.setCoworkingSpaceId(day.getCoworkingSpace() != null ? day.getCoworkingSpace().getId() : null);
        dto.setCoworkingSpaceName(day.getCoworkingSpace() != null ? day.getCoworkingSpace().getName() : null);
        dto.setNotes(day.getNotes());
        return dto;
    }
}
