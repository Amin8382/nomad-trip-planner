package com.nomad.controller;

import com.nomad.dto.CreateTripRequest;
import com.nomad.dto.TripDto;
import com.nomad.entity.TripStatus;
import com.nomad.entity.User;
import com.nomad.repository.UserRepository;
import com.nomad.service.TripService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;
    private final UserRepository userRepository;

    public TripController(TripService tripService, UserRepository userRepository) {
        this.tripService = tripService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getMyTrips(@AuthenticationPrincipal org.springframework.security.core.userdetails.User principal) {
        return ResponseEntity.ok(tripService.findByUser(extractUser(principal)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@AuthenticationPrincipal org.springframework.security.core.userdetails.User principal,
                                               @Valid @RequestBody CreateTripRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tripService.createTrip(extractUser(principal), request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TripDto> updateStatus(@PathVariable Long id, @RequestParam String status) {
        TripStatus tripStatus;
        try {
            tripStatus = TripStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tripService.updateStatus(id, tripStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    private User extractUser(org.springframework.security.core.userdetails.User principal) {
        return userRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + principal.getUsername()));
    }
}
