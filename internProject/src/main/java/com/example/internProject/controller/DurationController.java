package com.example.internProject.controller;

import com.example.internProject.model.Duration;
import com.example.internProject.service.DurationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/durations")
public class DurationController {

    @Autowired
    private DurationService durationService;

    @GetMapping
    public ResponseEntity<List<Duration>> getAllDurations() {
        List<Duration> durations = durationService.getAllDurations();
        return ResponseEntity.ok(durations);
    }
    @GetMapping("/{durationId}")
    public ResponseEntity<Duration> getDurationById(@PathVariable Integer durationId) {
        Duration duration = durationService.getDurationById(durationId);
        return ResponseEntity.ok(duration);
    }
    // Start a new work duration for a vehicle entry
    @PostMapping("/{entryId}/start")
    public ResponseEntity<Duration> startDuration(@PathVariable Integer entryId) {
        Duration newDuration = durationService.createNewDuration(entryId);
        return ResponseEntity.ok(newDuration);
    }

    // Stop the current work duration
    @PutMapping("/{durationId}/stop")
    public ResponseEntity<Duration> stopDuration(@PathVariable Integer durationId) {
        Duration stoppedDuration = durationService.stopDuration(durationId);
        return ResponseEntity.ok(stoppedDuration);
    }
}
