package com.example.internProject.model;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Table(name = "vehicle")
@Entity
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String driver;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<VehicleEntry> vehicleEntries = new ArrayList<>(); // Initialize the list here

    // Default constructor
    public Vehicle() {
        // Ensure vehicleEntries is not null
        this.vehicleEntries = new ArrayList<>();
    }

    // Parameterized constructor
    public Vehicle(Integer id, String licensePlate, String company, String driver) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.company = company;
        this.driver = driver;
        this.vehicleEntries = new ArrayList<>(); // Initialize in constructor
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public List<VehicleEntry> getVehicleEntries() {
        return vehicleEntries;
    }

    public void setVehicleEntries(List<VehicleEntry> vehicleEntries) {
        this.vehicleEntries = vehicleEntries;
    }

    public Long getTotalWorkDuration() {
        // Null check for vehicleEntries to avoid NullPointerException
        if (vehicleEntries == null || vehicleEntries.isEmpty()) {
            return 0L;
        }
        return vehicleEntries.stream()
                .map(VehicleEntry::getTotalWorkDuration)
                .reduce(0L, Long::sum);
    }
}
