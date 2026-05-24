package com.nomad.mapper;

import com.nomad.dto.ItineraryDayDto;
import com.nomad.dto.TripDto;
import com.nomad.entity.Trip;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TripMapper {

    private final ItineraryDayMapper itineraryDayMapper;

    public TripMapper(ItineraryDayMapper itineraryDayMapper) {
        this.itineraryDayMapper = itineraryDayMapper;
    }

    public TripDto toDto(Trip trip) {
        if (trip == null) return null;
        TripDto dto = new TripDto();
        dto.setId(trip.getId());
        dto.setName(trip.getName());
        dto.setVibe(trip.getVibe() != null ? trip.getVibe().name() : null);
        dto.setTotalBudget(trip.getTotalBudget());
        dto.setCurrency(trip.getCurrency());
        dto.setStartDate(trip.getStartDate());
        dto.setEndDate(trip.getEndDate());
        dto.setStatus(trip.getStatus() != null ? trip.getStatus().name() : null);
        dto.setUserId(trip.getUser() != null ? trip.getUser().getId() : null);

        List<ItineraryDayDto> dayDtos = trip.getItineraryDays() != null
                ? trip.getItineraryDays().stream().map(itineraryDayMapper::toDto).toList()
                : Collections.emptyList();
        dto.setItineraryDays(dayDtos);

        return dto;
    }
}
