package com.example.internProject.controller;

import com.example.internProject.model.Vehicle;
import com.example.internProject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.internProject.service.VehicleService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleRepository vehicleRepository;
	private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleRepository vehicleRepository, VehicleService vehicleService) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleService = vehicleService;
    }

    // Fetch all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

	    // Fetch vehicle by ID
	    @GetMapping("/{id}")
	    public Vehicle getVehicleById(@PathVariable Integer id) {
	        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
	        return vehicle.orElse(null);
	    }
	    
	    //post
	    @PostMapping("")
	    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
	        Vehicle savedVehicle = vehicleService.saveVehicle(vehicle);
	        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
	    }
	    
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    @PutMapping("/{id}")
	    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Integer id, @RequestBody Vehicle vehicle) {
	        Vehicle updatedVehicle = vehicleService.update(vehicle, id);
	        if (updatedVehicle != null) {
	            return ResponseEntity.ok(updatedVehicle);
	        } else {
	            return ResponseEntity.notFound().build(); // Return 404 if the vehicle is not found
	        }
	    }
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
	        vehicleService.delete(id); // Call service to delete the vehicle
	        return ResponseEntity.noContent().build(); // Return 204 No Content response
	    }	
	    
	}

	
	
	

