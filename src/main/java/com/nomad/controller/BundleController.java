package com.nomad.controller;

import com.nomad.dto.BundleDto;
import com.nomad.service.BundleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/bundles")
public class BundleController {

    private final BundleService bundleService;

    public BundleController(BundleService bundleService) {
        this.bundleService = bundleService;
    }

    @GetMapping
    public ResponseEntity<List<BundleDto>> getAll(@RequestParam(required = false) BigDecimal maxPrice) {
        if (maxPrice != null) {
            return ResponseEntity.ok(bundleService.findAffordable(maxPrice));
        }
        return ResponseEntity.ok(bundleService.findAll());
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<BundleDto>> getByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(bundleService.findByCity(cityId));
    }

    @PostMapping
    public ResponseEntity<BundleDto> createBundle(@RequestParam String name,
                                                   @RequestParam(required = false) String description,
                                                   @RequestParam(required = false) Long flightId,
                                                   @RequestParam Long accommodationId,
                                                   @RequestParam(required = false) Long coworkingId,
                                                   @RequestParam Long cityId) {
        return ResponseEntity.ok(bundleService.createBundle(
                name, description, flightId, accommodationId, coworkingId, cityId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bundleService.deleteBundle(id);
        return ResponseEntity.noContent().build();
    }
}
