package com.userlogin.userapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.Consumption;
import com.userlogin.userapp.entities.Vehicle;
import com.userlogin.userapp.services.VehicleService;

@RestController
@RequestMapping("/api-vehicle") // {userId}/vehicles/{vehicleId}")
public class VehicleController {

	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/user/{userId}/vehicle/{vehicleId}")
	public ResponseEntity<Vehicle> getById(@PathVariable("userId") Integer userId,
			@PathVariable("vehicleId") Integer vehicleId) {
		return new ResponseEntity<Vehicle>(vehicleService.getByUserIdAndVehicleId(userId, vehicleId), HttpStatus.OK);
	}

	@GetMapping("/{vehiclePatente}")
	public ResponseEntity<Double> getTotalConsumptionVehicle(@PathVariable("vehiclePatente") String vehiclePatente) {
		return new ResponseEntity<>(vehicleService.getTotalConsumptionVehicle(vehiclePatente), HttpStatus.OK);
	}

	@PostMapping("/user/{userId}")
	public ResponseEntity<Vehicle> createVehicleUser(@PathVariable("userId") Integer userId,
			@RequestBody Vehicle vehicle) {
		return new ResponseEntity<Vehicle>(vehicleService.createVehicleUser(userId, vehicle), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Vehicle>> listVehicle() {
		return new ResponseEntity<List<Vehicle>>(vehicleService.getVehicles(), HttpStatus.OK);
	}

	@GetMapping("/consumption")
	public ResponseEntity<List<Consumption>> getAllConsumptionsWithVehicle() {
		return new ResponseEntity<List<Consumption>>(vehicleService.getAllWithVehicle(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Vehicle> createVehicles(@RequestBody Vehicle vehicle) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {}", authentication.getName());
		log.info("principa {}", authentication.getPrincipal());
		log.info("Credenciales {}", authentication.getCredentials());
		log.info("rol {}", authentication.getAuthorities().toString());
		return new ResponseEntity<Vehicle>(vehicleService.createVehicle(vehicle), HttpStatus.CREATED);
	}

	@DeleteMapping("/{patent}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable("patent") String patent) {
		vehicleService.deleteVehicleByPatent(patent);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}