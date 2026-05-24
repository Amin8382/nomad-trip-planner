package com.nomad.config;

import com.nomad.entity.*;
import com.nomad.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final AccommodationRepository accommodationRepository;
    private final CoworkingSpaceRepository coworkingSpaceRepository;
    private final FlightRepository flightRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           CityRepository cityRepository,
                           AccommodationRepository accommodationRepository,
                           CoworkingSpaceRepository coworkingSpaceRepository,
                           FlightRepository flightRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.accommodationRepository = accommodationRepository;
        this.coworkingSpaceRepository = coworkingSpaceRepository;
        this.flightRepository = flightRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;

        User admin = new User("Admin", "admin@nomad.io", passwordEncoder.encode("admin123"), Role.ADMIN);
        User user = new User("Alice", "alice@nomad.io", passwordEncoder.encode("pass123"), Role.USER);
        userRepository.save(admin);
        userRepository.save(user);

        City chiangMai = createCity("Chiang Mai", "Thailand", "Asia/Bangkok", 35.0, 18.7883, 98.9853);
        City bali = createCity("Ubud", "Indonesia", "Asia/Makassar", 40.0, -8.5069, 115.2625);
        City lisbon = createCity("Lisbon", "Portugal", "Europe/Lisbon", 65.0, 38.7223, -9.1393);
        City medellin = createCity("Medellin", "Colombia", "America/Bogota", 30.0, 6.2476, -75.5658);
        City bangkok = createCity("Bangkok", "Thailand", "Asia/Bangkok", 40.0, 13.7563, 100.5018);
        City tenerife = createCity("Tenerife", "Spain", "Atlantic/Canary", 55.0, 28.2916, -16.6291);

        accommodationRepository.save(makeAccommodation("Cozy Studio", chiangMai, AccommodationType.AIRBNB, new BigDecimal("15"), 4.5));
        accommodationRepository.save(makeAccommodation("Digital Nomad Hub", chiangMai, AccommodationType.HOSTEL, new BigDecimal("8"), 4.0));
        accommodationRepository.save(makeAccommodation("Green Villa", bali, AccommodationType.AIRBNB, new BigDecimal("22"), 4.7));
        accommodationRepository.save(makeAccommodation("Bungalow Paradise", bali, AccommodationType.HOTEL, new BigDecimal("30"), 4.3));
        accommodationRepository.save(makeAccommodation("Chiado Apartment", lisbon, AccommodationType.AIRBNB, new BigDecimal("45"), 4.6));
        accommodationRepository.save(makeAccommodation("Lisbon Hostel", lisbon, AccommodationType.HOSTEL, new BigDecimal("20"), 4.1));
        accommodationRepository.save(makeAccommodation("Poblado Studio", medellin, AccommodationType.AIRBNB, new BigDecimal("18"), 4.4));
        accommodationRepository.save(makeAccommodation("Medellin Social Hostel", medellin, AccommodationType.HOSTEL, new BigDecimal("10"), 4.2));
        accommodationRepository.save(makeAccommodation("Sukhumvit Suite", bangkok, AccommodationType.HOTEL, new BigDecimal("25"), 4.5));
        accommodationRepository.save(makeAccommodation("Tenerife Beach House", tenerife, AccommodationType.AIRBNB, new BigDecimal("35"), 4.6));

        coworkingSpaceRepository.save(makeCoworking("Punspace", chiangMai, new BigDecimal("3"), true, false, 4.5));
        coworkingSpaceRepository.save(makeCoworking("Outpost Ubud", bali, new BigDecimal("5"), true, false, 4.6));
        coworkingSpaceRepository.save(makeCoworking("Second Home", lisbon, new BigDecimal("12"), true, true, 4.7));
        coworkingSpaceRepository.save(makeCoworking("Tinkko", medellin, new BigDecimal("4"), true, true, 4.3));
        coworkingSpaceRepository.save(makeCoworking("Hubba", bangkok, new BigDecimal("6"), true, false, 4.4));
        coworkingSpaceRepository.save(makeCoworking("Coworking Tenerife", tenerife, new BigDecimal("10"), true, false, 4.2));

        flightRepository.save(makeFlight("AirAsia", bangkok, chiangMai, new BigDecimal("35"), 75));
        flightRepository.save(makeFlight("Thai Airways", bangkok, chiangMai, new BigDecimal("55"), 70));
        flightRepository.save(makeFlight("AirAsia", bangkok, bali, new BigDecimal("120"), 210));
        flightRepository.save(makeFlight("TAP Air Portugal", lisbon, tenerife, new BigDecimal("65"), 120));
        flightRepository.save(makeFlight("Ryanair", lisbon, tenerife, new BigDecimal("30"), 135));
        flightRepository.save(makeFlight("Vueling", lisbon, medellin, new BigDecimal("350"), 540));
        flightRepository.save(makeFlight("Air France", lisbon, bangkok, new BigDecimal("450"), 660));
    }

    private City createCity(String name, String country, String tz, double costIndex, double lat, double lon) {
        City city = new City();
        city.setName(name);
        city.setCountry(country);
        city.setTimezone(tz);
        city.setCostIndex(costIndex);
        city.setLatitude(lat);
        city.setLongitude(lon);
        city.setDescription("A thriving digital nomad destination in " + country);
        return cityRepository.save(city);
    }

    private Accommodation makeAccommodation(String name, City city, AccommodationType type, BigDecimal price, double rating) {
        Accommodation a = new Accommodation();
        a.setName(name);
        a.setCity(city);
        a.setType(type);
        a.setPricePerNight(price);
        a.setRating(rating);
        a.setAvailable(true);
        a.setAmenities("WiFi, AC, Kitchen");
        return a;
    }

    private CoworkingSpace makeCoworking(String name, City city, BigDecimal price, boolean internet, boolean open247, double rating) {
        CoworkingSpace c = new CoworkingSpace();
        c.setName(name);
        c.setCity(city);
        c.setPricePerDay(price);
        c.setHasHighSpeedInternet(internet);
        c.setOpen247(open247);
        c.setRating(rating);
        c.setAmenities("WiFi, Coffee, AC, Printers");
        return c;
    }

    private Flight makeFlight(String airline, City origin, City dest, BigDecimal price, int duration) {
        Flight f = new Flight();
        f.setAirline(airline);
        f.setOriginCity(origin);
        f.setDestinationCity(dest);
        f.setDepartureDate(LocalDateTime.now().plusDays(7));
        f.setArrivalDate(LocalDateTime.now().plusDays(7).plusMinutes(duration));
        f.setPrice(price);
        f.setDurationMinutes(duration);
        return f;
    }
}
