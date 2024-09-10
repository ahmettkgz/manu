package com.example.internProject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.internProject.model.Vehicle;
import com.example.internProject.model.VehicleEntry;


@SpringBootApplication
@ComponentScan
public class InternProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternProjectApplication.class, args);
		System.out.println("Application started successfully.");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Vehicle vehicle = new Vehicle();
			VehicleEntry reco = new VehicleEntry();

			vehicle.setId(1);
			vehicle.setLicensePlate("ABC123");
			vehicle.setCompany("XYZ Corp");
			vehicle.setDriver("John Doe");

			reco.setEid(1);
			reco.setStartedOn(LocalDateTime.now());
			reco.setCompletedOn(LocalDateTime.now().plus(1, ChronoUnit.HOURS));

			System.out.println("Vehicle ID: " + vehicle.getId());
			System.out.println("License Plate: " + vehicle.getLicensePlate());
			System.out.println("Company: " + vehicle.getCompany());
			System.out.println("Driver: " + vehicle.getDriver());

			System.out.println("Entry ID: " + reco.getEid());
			System.out.println("Started On: " + reco.getStartedOn());
			System.out.println("Completed On: " + reco.getCompletedOn());
		};
	}
}
