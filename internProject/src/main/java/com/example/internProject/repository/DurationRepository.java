package com.example.internProject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.internProject.model.Duration;

@Repository
public interface DurationRepository extends JpaRepository<Duration, Integer> {
    List<Duration> findByVehicleEntryEid(Integer vehicleEntryId);
}
