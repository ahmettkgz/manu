package com.example.internProject.service;

import com.example.internProject.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import com.example.internProject.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Dummy list for manual initialization
    private List<Vehicle> vehicles = new ArrayList<>();

    @PostConstruct
    private void init() {
        vehicles.add(new Vehicle(1, "ABC123", "XYZ Corp", "Ahmit"));
        vehicles.add(new Vehicle(2, "DEF456", "YDS", "YAVUZABS"));
        vehicleRepository.saveAll(vehicles);
    }
    
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);	
    }
    
    public Vehicle update(Vehicle vehicle, Integer id) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle vehicleToUpdate = existingVehicle.get();
            vehicleToUpdate.setLicensePlate(vehicle.getLicensePlate());
            vehicleToUpdate.setCompany(vehicle.getCompany());
            vehicleToUpdate.setDriver(vehicle.getDriver());
            return vehicleRepository.save(vehicleToUpdate); 
        }
        return null;
    }

    public void delete(Integer id) {
        vehicleRepository.deleteById(id);
    }


    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}