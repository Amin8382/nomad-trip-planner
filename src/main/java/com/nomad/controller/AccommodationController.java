package com.nomad.controller;

import com.nomad.dto.AccommodationDto;
import com.nomad.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public ResponseEntity<List<AccommodationDto>> getByCity(@RequestParam(required = false) Long cityId,
                                                             @RequestParam(required = false) BigDecimal maxPrice) {
        if (cityId == null) {
            return ResponseEntity.ok(accommodationService.findAll());
        }
        if (maxPrice != null) {
            return ResponseEntity.ok(accommodationService.findByCityAndMaxPrice(cityId, maxPrice));
        }
        return ResponseEntity.ok(accommodationService.findByCity(cityId));
    }

    @PostMapping
    public ResponseEntity<AccommodationDto> create(@RequestBody AccommodationDto dto) {
        return ResponseEntity.ok(accommodationService.create(dto));
    }
}
