package com.example.internProject.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.internProject.model.VehicleEntry;

@Repository
public interface VehicleEntryRepository extends JpaRepository<VehicleEntry, Integer> {
    List<VehicleEntry> findByStartedOn(LocalDateTime startedOn);
    Optional<VehicleEntry> findById(Integer id); // Correct method signature
}
