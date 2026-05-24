package com.nomad.repository;

import com.nomad.entity.Trip;
import com.nomad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUser(User user);
    List<Trip> findByUserAndStatus(User user, com.nomad.entity.TripStatus status);
}
