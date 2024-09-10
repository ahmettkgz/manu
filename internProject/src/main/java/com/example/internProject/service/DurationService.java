package com.example.internProject.service;

import com.example.internProject.model.Duration;
import com.example.internProject.model.VehicleEntry;
import com.example.internProject.repository.DurationRepository;
import com.example.internProject.repository.VehicleEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DurationService {

    @Autowired
    private DurationRepository durationRepository;

    @Autowired
    private VehicleEntryRepository vehicleEntryRepository;

    // Create a new duration when a vehicle continues working
    public Duration createNewDuration(Integer entryId) {
        Optional<VehicleEntry> vehicleEntryOptional = vehicleEntryRepository.findById(entryId);
        if (vehicleEntryOptional.isPresent()) {
            VehicleEntry vehicleEntry = vehicleEntryOptional.get();
            Duration duration = new Duration();
            duration.setStartedOn(LocalDateTime.now());
            duration.setVehicleEntry(vehicleEntry);
            return durationRepository.save(duration);
        }
        return null; // Handle case when entryId is not found
    }

    // Mark stop for a duration
    public Duration stopDuration(Integer durationId) {
        Optional<Duration> durationOptional = durationRepository.findById(durationId);
        if (durationOptional.isPresent()) {
            Duration duration = durationOptional.get();
            duration.setCompletedOn(LocalDateTime.now());
            return durationRepository.save(duration);
        }
        return null; // Handle case when durationId is not found
    }
    public List<Duration> getAllDurations() {
        return durationRepository.findAll();
    }
    public Duration getDurationById(Integer durationId) {
        return durationRepository.findById(durationId).orElse(null);
    }
    
    
    
}
