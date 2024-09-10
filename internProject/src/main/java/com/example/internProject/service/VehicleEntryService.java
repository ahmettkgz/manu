package com.example.internProject.service;

import com.example.internProject.model.Duration;
import com.example.internProject.model.Vehicle;
import com.example.internProject.model.VehicleEntry;
import com.example.internProject.repository.DurationRepository;
import com.example.internProject.repository.VehicleEntryRepository;
import com.example.internProject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleEntryService {

    @Autowired
    private VehicleEntryRepository vehicleEntryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DurationRepository durationRepository;

    public VehicleEntry createVehicleEntry(Integer vehicleId, LocalDateTime startTime) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = vehicleOptional.get();
            VehicleEntry entry = new VehicleEntry();
            entry.setStartedOn(startTime);
            entry.setVehicle(vehicle);

            VehicleEntry savedEntry = vehicleEntryRepository.save(entry);

            // Create a new Duration associated with this entry
            Duration duration = new Duration();
            duration.setStartedOn(startTime);
            duration.setVehicleEntry(savedEntry);
            durationRepository.save(duration);

            return savedEntry;
        }
        return null; // Handle case when vehicleId is not found
    }

    // Mark exit for a vehicle (complete working)
    public VehicleEntry completeVehicleEntry(Integer entryId, LocalDateTime completedOn) {
        Optional<VehicleEntry> entryOptional = vehicleEntryRepository.findById(entryId);
        if (entryOptional.isPresent()) {
            VehicleEntry entry = entryOptional.get();
            entry.setCompletedOn(completedOn);
            return vehicleEntryRepository.save(entry);
        }
        return null; // Handle case when entryId is not found
    }

    // Get all vehicle entries
    public List<VehicleEntry> getAllVehicleEntries() {
        return vehicleEntryRepository.findAll();
    }
    

    // Get total work duration for a vehicle
    public Long getTotalWorkDuration(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId)
            .map(vehicle -> vehicle.getTotalWorkDuration())
            .orElse(0L); // Handle case when vehicleId is not found
    }

    // Delete a vehicle entry
    public void deleteVehicleEntry(Integer entryId) {
        vehicleEntryRepository.deleteById(entryId);
    }
}
