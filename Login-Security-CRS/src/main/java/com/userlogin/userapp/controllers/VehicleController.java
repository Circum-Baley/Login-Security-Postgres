package com.userlogin.userapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.Vehicle;
import com.userlogin.userapp.services.VehicleService;

@RestController
@RequestMapping("/api-vehicle")//{userId}/vehicles/{vehicleId}")
public class VehicleController {
	
	
	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	private VehicleService vehicleService;
//	@GetMapping
//	public ResponseEntity<Page<User>> getUsers(@RequestParam("page") int page,@RequestParam("size") int size){
//		return new ResponseEntity<>(userService.getUsers(page,size),HttpStatus.OK);
//	}
	@GetMapping("/user/{userId}/vehicle/{vehicleId}")
	public ResponseEntity<Vehicle> getById(@PathVariable("userId") Integer userId,@PathVariable("vehicleId") Integer vehicleId){
		return new ResponseEntity<Vehicle>(vehicleService.getByUserIdAndVehicleId(userId,vehicleId),HttpStatus.OK);
	}
	
	@PostMapping("/user/{userId}")
	public ResponseEntity<Vehicle> createVehicleUser(@PathVariable("userId") Integer userId,@RequestBody Vehicle vehicle){
		return new ResponseEntity<Vehicle>(vehicleService.createVehicleUser(userId,vehicle),HttpStatus.CREATED);
	}
	
//	@PostMapping
//	public ResponseEntity<Vehicle> getById(@PathVariable("userId") Integer userId,@PathVariable)
//	@GetMapping("/patent/{patent}")
//	public ResponseEntity<Vehicle> getVehicleByPatent(@PathVariable("patent")String patent){
//		return new ResponseEntity<Vehicle>(vehicleService.getVehicleByPatent(patent),HttpStatus.OK);
//	}
//	@GetMapping("/patent/{patent}/{vehicleId}")
//	public ResponseEntity<Vehicle> getVehicleByPatentAndById(@PathVariable("patent") String patent,@PathVariable("vehicleId") Integer vehicleId){
//		return new ResponseEntity<Vehicle>(vehicleService.getVehicleByPatentAndId(patent, vehicleId),HttpStatus.OK);
//	}
	@GetMapping
	public ResponseEntity<List<Vehicle>> getVehicles(){
		return new ResponseEntity<List<Vehicle>>(vehicleService.getVehicles(),HttpStatus.OK);
	}
	/***
	 * Devuelve todos los username de todos los registros ya predefinidos*/
//	@GetMapping("/patent")
//	public ResponseEntity<List<String>> getUsernames(){
//		return new ResponseEntity<>(vehicleService.getPatents(),HttpStatus.OK);
//	}
	
	//////
	
	@PostMapping
	public ResponseEntity<Vehicle> createVehicles(@RequestBody Vehicle vehicle){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		log.info("Name {}",authentication.getName());
//		log.info("principa {}",authentication.getPrincipal());
//		log.info("Credenciales {}",authentication.getCredentials());
//		log.info("rol {}",authentication.getAuthorities().toString());
		return new ResponseEntity<Vehicle>(vehicleService.createVehicle(vehicle),HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{patent}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable("patent") String patent){
		vehicleService.deleteVehicleByPatent(patent);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	

}
