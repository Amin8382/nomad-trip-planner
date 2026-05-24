package com.nomad.service;

import com.nomad.dto.BundleDto;
import com.nomad.entity.*;
import com.nomad.mapper.BundleMapper;
import com.nomad.repository.BundleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class BundleService {

    private final BundleRepository bundleRepository;
    private final BundleMapper bundleMapper;
    private final CityService cityService;
    private final FlightService flightService;
    private final AccommodationService accommodationService;
    private final CoworkingSpaceService coworkingSpaceService;

    public BundleService(BundleRepository bundleRepository,
                         BundleMapper bundleMapper,
                         CityService cityService,
                         FlightService flightService,
                         AccommodationService accommodationService,
                         CoworkingSpaceService coworkingSpaceService) {
        this.bundleRepository = bundleRepository;
        this.bundleMapper = bundleMapper;
        this.cityService = cityService;
        this.flightService = flightService;
        this.accommodationService = accommodationService;
        this.coworkingSpaceService = coworkingSpaceService;
    }

    public List<BundleDto> findAll() {
        return bundleRepository.findAll().stream().map(bundleMapper::toDto).toList();
    }

    public List<BundleDto> findByCity(Long cityId) {
        City city = cityService.getCityEntity(cityId);
        return bundleRepository.findByCity(city).stream().map(bundleMapper::toDto).toList();
    }

    public BundleDto createBundle(String name, String description, Long flightId,
                                   Long accommodationId, Long coworkingId, Long cityId) {
        Flight flight = null;
        Accommodation accommodation = null;
        CoworkingSpace coworking = null;
        City city = cityService.getCityEntity(cityId);

        if (flightId != null) {
            flight = flightService.findFlightsToCity(cityId).stream()
                    .filter(f -> f.getId().equals(flightId))
                    .findFirst()
                    .map(f -> {
                        Flight fl = new Flight();
                        fl.setId(f.getId());
                        return fl;
                    })
                    .orElse(null);
        }

        BigDecimal total = BigDecimal.ZERO;
        int count = 0;

        if (accommodationId != null) {
            accommodation = new Accommodation();
            accommodation.setId(accommodationId);
            total = total.add(BigDecimal.valueOf(500));
            count++;
        }

        if (coworkingId != null) {
            coworking = new CoworkingSpace();
            coworking.setId(coworkingId);
            total = total.add(BigDecimal.valueOf(200));
            count++;
        }

        Bundle bundle = new Bundle();
        bundle.setName(name);
        bundle.setDescription(description);
        bundle.setFlight(flight);
        bundle.setAccommodation(accommodation);
        bundle.setCoworkingSpace(coworking);
        bundle.setCity(city);

        BigDecimal discount = BigDecimal.valueOf(10).multiply(BigDecimal.valueOf(count));
        BigDecimal discountedPrice = total.multiply(
                BigDecimal.valueOf(100).subtract(discount)
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_DOWN)
        );

        bundle.setTotalPrice(discountedPrice);
        bundle.setDiscount(discount);
        bundle.setCurrency("USD");

        return bundleMapper.toDto(bundleRepository.save(bundle));
    }

    public List<BundleDto> findAffordable(BigDecimal maxPrice) {
        return bundleRepository.findByTotalPriceLessThanEqual(maxPrice).stream()
                .map(bundleMapper::toDto).toList();
    }

    public void deleteBundle(Long id) {
        bundleRepository.deleteById(id);
    }
}
