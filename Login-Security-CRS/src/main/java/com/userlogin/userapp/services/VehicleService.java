package com.userlogin.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.Vehicle;
import com.userlogin.userapp.repositories.UserRepository;
import com.userlogin.userapp.repositories.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// se realizo para atraer datos sin indexar, versus el metodo siguiente de abajo
//	public List<Vehicle> getUsers(){
//		return vehicleRepository.findAll();
//	}
//	
	public Vehicle createVehicleUser(Integer userId,Vehicle vehicle) {
		Optional<User> result = userRepository.findById(userId);
		if(result.isPresent()) {
			vehicle.setUser(result.get());
			return vehicleRepository.save(vehicle);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Vehicle %d not foundechion",userId));
		}
	}
//	public Page<User> getUsers(int page,int size){
//		return vehicleRepository.findAll(PageRequest.of(page, size));
//		
//	}
//	public List<String> getPatents(){
//		return vehicleRepository.findPatent();
//	}
	public Vehicle createVehicle(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}
	
	public Vehicle getVehicleById(Integer vehicleId) {
		return vehicleRepository.findById(vehicleId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("user %d not found", vehicleId)));	
	}
	
	public Vehicle getVehicleByPatent(String patent) {
		return vehicleRepository.findByPatent(patent).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("vehicle %d not foundeichon", patent)));
	}
	
	public Vehicle getByUserIdAndVehicleId(Integer userId,Integer vehicleId) {
		return vehicleRepository.findByUserIdAndVehicleId(userId, vehicleId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("vehicle %d , %d ", userId,vehicleId)));
	}
	
	
//	@CacheEvict("users")
	public void deleteVehicleByPatent(String patent) {
		Vehicle vehicle= getVehicleByPatent(patent);
		vehicleRepository.delete(vehicle);
	}

	public List<Vehicle> getVehicles() {
		return (List<Vehicle>) vehicleRepository.findAll();
		}
	
//	
	
}
