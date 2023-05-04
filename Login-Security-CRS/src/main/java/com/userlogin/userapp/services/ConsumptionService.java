package com.userlogin.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.Consumption;
import com.userlogin.userapp.entities.Vehicle;
import com.userlogin.userapp.repositories.ConsumptionRepository;
import com.userlogin.userapp.repositories.VehicleRepository;

@Service
public class ConsumptionService {
	@Autowired
	private ConsumptionRepository consumptionRepository;

	@Autowired
	private VehicleRepository vehicleRepository;
	
	public Consumption getConsumptionById(Integer consumptionId) {
		return consumptionRepository.findById(consumptionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Consumption N° = %d No Fue Encontrado", consumptionId)));
	}
	public Consumption createConsumption(Integer userId,Integer vehicleId, Consumption consumption) {
		Optional<Vehicle> resultat = vehicleRepository.findByUserIdAndVehicleId(userId,vehicleId);
		if(resultat.isPresent()) {
			consumption.setVehicle(resultat.get());
			return consumptionRepository.save(consumption);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Vehiculo %d Y Usuario %d No Fuieron Encontrados NOT FOUND  ", vehicleId,userId));
		}
	}
	public Optional<Consumption> findConsumptionByVehicleAndUserId(Integer userId, Integer vehicleId) {
		return consumptionRepository.findByVehicleId(userId,vehicleId);
	}


	public List<Consumption> getConsumptions() {
		return consumptionRepository.findAll();
	}

	
//	public List<Long> getNumbers() {
//		return consumptionRepository.findByNumbers();
//	}
//
//	public Consumption getConsumptionById(Integer consumptionId) {
//		return consumptionRepository.findById(consumptionId)
//				.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Consumption d% Not Found", consumptionId)));
//	}
//
//	public Consumption getConsumptionByNumber(Long number) {
//		return consumptionRepository.findByNumbers(number).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("consumption %d NOT FOUND", number)));
//	}
//
//	public Consumption getConsumptionByNumberAndId(Long number, Integer consumptionId) {
//		return consumptionRepository.findByUsernameAndId(number, consumptionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("consumption %d , %d ", number,consumptionId)));
//
//	}

	public void deleteConsumptionByNumber(Long number) {
		// TODO Auto-generated method stub
		
	}
	
	
	
//	public Consumption getAddressById(Integer consumptionId) {
//		return consumptionRepository.findById(consumptionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Consumption %d not foundeichon", consumptionId)));
//	}
//	
//	// se realizo para atraer recursos del objeto "Consumption" sin indexar, versus el metodo siguiente de abajo
//	public List<Consumption> getConsumptions(){
//		return consumptionRepository.findAll();
//	}
//	
////	public Page<User> getUsers(int page,int size){
////		return userRepository.findAll(PageRequest.of(page, size));
////		
////	}
//	public List<Long> getNumbers(){
//		return consumptionRepository.findNumbers();
//	}
//	
//	public Consumption getConsumptionById(Long consumptionId) {
//		return consumptionRepository.findById(consumptionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Consumption %d not found", consumptionId)));	
//	}
//	
//	public Consumption getConsumptionByNumber(Long number) {
//		return consumptionRepository.findByNumber(number).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Consumption %d not foundeichon", consumption)));
//	}
//	
//	public Consumption getConsumptionByNumberAndId(Long number,Integer consumptionId) {
//		return consumptionRepository.findByNumberAndId(number, consumptionId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("user %d , %d ", number,consumptionId)));
//		
//	}
//	
//	
////	@CacheEvict("users")
//	public void deleteConsumptionsByNumber(Long Number) {
//		Consumption consumption= getConsumptionByNumber(Number);
//		consumptionRepository.delete(consumption);
//	}

}
/**

	public Address createAddress(Integer userId, Integer profileId, Address address) {
		Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
		if(result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Profile %d and User %d No Fueron Encontrados", profileId,userId));
		}
	}
	
	public List<Address> findAddressesByProfileAndUserId(Integer userId, Integer profileId) {
		return addressRepository.findByProfileId(userId,profileId);
	}

	public void deleteAddress(Integer addressId) {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Address %d Not Found :: " + addressId));
		this.addressRepository.delete(address);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted",Boolean.TRUE);
	} 

	public void deleteProfileAndAddress(Integer addressId) {
		Optional<Address> resultat = addressRepository.findById(addressId);
		if(resultat.isPresent()) {
			addressRepository.findById(addressId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Address %d Profile Not Found!"));
			addressRepository.deleteById(addressId);
		}else {
			throw new ResponseStatusException(HttpStatus.OK,"Address Successfully Removed !");
		}
	}
	

	 public void delete(Integer id){	        
		 try{	     
			 addressRepository.deleteById(id);	        
		 }catch (DataIntegrityViolationException e){
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Address {} %d Nao é possivel excluir entidades relacionadas.");  
		 }
	 }

	
	
	
	public Address getAddressById(Integer addressId) {
		return addressRepository.findById(addressId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Address %d not foundeichon", addressId)));
	}


	
	

	
}

 * */
 