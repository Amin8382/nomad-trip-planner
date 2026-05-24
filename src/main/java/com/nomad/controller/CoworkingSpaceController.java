package com.nomad.controller;

import com.nomad.dto.CoworkingSpaceDto;
import com.nomad.service.CoworkingSpaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/coworking")
public class CoworkingSpaceController {

    private final CoworkingSpaceService coworkingSpaceService;

    public CoworkingSpaceController(CoworkingSpaceService coworkingSpaceService) {
        this.coworkingSpaceService = coworkingSpaceService;
    }

    @GetMapping
    public ResponseEntity<List<CoworkingSpaceDto>> getByCity(@RequestParam Long cityId,
                                                              @RequestParam(required = false) BigDecimal maxPrice) {
        if (maxPrice != null) {
            return ResponseEntity.ok(coworkingSpaceService.findByCityAndMaxPrice(cityId, maxPrice));
        }
        return ResponseEntity.ok(coworkingSpaceService.findByCity(cityId));
    }

    @PostMapping
    public ResponseEntity<CoworkingSpaceDto> create(@RequestBody CoworkingSpaceDto dto) {
        return ResponseEntity.ok(coworkingSpaceService.create(dto));
    }
}
