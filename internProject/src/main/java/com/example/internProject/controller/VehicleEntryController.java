package com.example.internProject.controller;

import com.example.internProject.model.VehicleEntry;
import com.example.internProject.service.VehicleEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vehicle-entries")
public class VehicleEntryController {

    @Autowired
    private VehicleEntryService vehicleEntryService;

    // Create a new vehicle entry (start time)
    @PostMapping("/create")
    public ResponseEntity<VehicleEntry> createVehicleEntry(@RequestParam Integer vehicleId) {
        VehicleEntry entry = vehicleEntryService.createVehicleEntry(vehicleId, LocalDateTime.now());
        return ResponseEntity.ok(entry);
    }

    // Mark vehicle exit (end time)
    @PutMapping("/{entryId}/complete")
    public ResponseEntity<VehicleEntry> completeVehicleEntry(@PathVariable Integer entryId) {
        VehicleEntry updatedEntry = vehicleEntryService.completeVehicleEntry(entryId, LocalDateTime.now());
        return ResponseEntity.ok(updatedEntry);
    }

    // Get all vehicle entries
    @GetMapping
    public ResponseEntity<List<VehicleEntry>> getAllVehicleEntries() {
        List<VehicleEntry> entries = vehicleEntryService.getAllVehicleEntries();
        return ResponseEntity.ok(entries);
    }

    // Get total work duration for a vehicle
    @GetMapping("/total-duration/{vehicleId}")
    public ResponseEntity<Long> getTotalWorkDuration(@PathVariable Integer vehicleId) {
        Long totalDuration = vehicleEntryService.getTotalWorkDuration(vehicleId);
        return ResponseEntity.ok(totalDuration);
    }

    // Delete a vehicle entry
    @DeleteMapping("/{entryId}")
    public ResponseEntity<Void> deleteVehicleEntry(@PathVariable Integer entryId) {
        vehicleEntryService.deleteVehicleEntry(entryId);
        return ResponseEntity.noContent().build();
    }
}
