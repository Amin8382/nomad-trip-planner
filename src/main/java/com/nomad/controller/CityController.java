package com.nomad.controller;

import com.nomad.dto.CityDto;
import com.nomad.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CityDto>> search(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Double maxCost) {
        if (name != null) return ResponseEntity.ok(cityService.searchByName(name));
        if (maxCost != null) return ResponseEntity.ok(cityService.findByMaxCostIndex(maxCost));
        return ResponseEntity.ok(cityService.findAll());
    }

    @PostMapping
    public ResponseEntity<CityDto> create(@RequestBody CityDto dto) {
        return ResponseEntity.ok(cityService.create(dto));
    }
}
