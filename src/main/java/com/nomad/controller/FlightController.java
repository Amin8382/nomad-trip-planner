package com.nomad.controller;

import com.nomad.dto.FlightDto;
import com.nomad.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<FlightDto>> getFlights(@RequestParam Long originId,
                                                       @RequestParam Long destinationId,
                                                       @RequestParam(required = false) BigDecimal maxPrice) {
        if (maxPrice != null) {
            return ResponseEntity.ok(flightService.findFlightsBetweenMaxPrice(originId, destinationId, maxPrice));
        }
        return ResponseEntity.ok(flightService.findFlightsBetween(originId, destinationId));
    }

    @GetMapping("/to/{cityId}")
    public ResponseEntity<List<FlightDto>> getFlightsToCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(flightService.findFlightsToCity(cityId));
    }

    @PostMapping
    public ResponseEntity<FlightDto> create(@RequestBody FlightDto dto) {
        return ResponseEntity.ok(flightService.create(dto));
    }
}
