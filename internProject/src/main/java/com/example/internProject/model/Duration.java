package com.example.internProject.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Table(name = "duration")
@Entity
public class Duration {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer did;

    @Column(nullable = false)
    private LocalDateTime startedOn;

    @Column(nullable = true)
    private LocalDateTime completedOn;

    @ManyToOne
    @JoinColumn(name = "vehicle_entry_id", nullable = false)
    @JsonIgnore
    private VehicleEntry vehicleEntry;

    public Duration() {
        this.startedOn = LocalDateTime.now(); // Automatically set the start time when creating a new instance
    }

    public Duration(LocalDateTime startedOn, LocalDateTime completedOn) {
        this.startedOn = startedOn;
        this.completedOn = completedOn;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public LocalDateTime getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDateTime startedOn) {
        this.startedOn = startedOn;
    }

    public LocalDateTime getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(LocalDateTime completedOn) {
        this.completedOn = completedOn;
    }

    public VehicleEntry getVehicleEntry() {
        return vehicleEntry;
    }

    public void setVehicleEntry(VehicleEntry vehicleEntry) {
        this.vehicleEntry = vehicleEntry;
    }

    public Long getWorkDuration() {
        if (startedOn != null && completedOn != null) {
            return java.time.Duration.between(startedOn, completedOn).toMinutes();
        }
        return 0L;
    }
}
