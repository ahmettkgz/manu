package com.example.internProject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "entry")
@Entity
public class VehicleEntry {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer eid;

    @Column(nullable = false)
    private LocalDateTime startedOn;

    @Column(nullable = true)
    private LocalDateTime completedOn;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    @JsonIgnore
    private Vehicle vehicle;

    @OneToMany(mappedBy = "vehicleEntry", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore

    private List<Duration> durations = new ArrayList<>();
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public VehicleEntry() {}
    public VehicleEntry(LocalDateTime startedOn, Vehicle vehicle) {
        this.startedOn = startedOn;
        this.vehicle = vehicle;
        this.completedOn = null; // Initialize to null
    }
    
    

    public List<Duration> getDurations() {
        return durations;
    }

    public void setDurations(List<Duration> durations) {
        this.durations = durations;
    }
    

    public Long getTotalWorkDuration() {
        return durations.stream()
                .map(Duration::getWorkDuration)
                .reduce(0L, Long::sum);
    }
}
