package com.userlogin.userapp.controllers;

import java.util.List;

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

import com.userlogin.userapp.entities.Consumption;
import com.userlogin.userapp.entities.Vehicle;
import com.userlogin.userapp.repositories.VehicleRepository;
import com.userlogin.userapp.services.ConsumptionService;
import com.userlogin.userapp.services.VehicleService;

@RestController
@RequestMapping("/api-consumption")
public class ConsumptionController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ConsumptionService consumptionService;

////////////////////////////////////////////////////////
//	@GetMapping("/consumptionChart")
//	public ViewResolver getViewResolver() {
//	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	    resolver.setPrefix("/view/");
//	    resolver.setSuffix(".xhtml");
//	    return resolver;
//	}
////////////////////////////////////////////////////////

	// a traves del id de consumo obtenemos el consumo
	@GetMapping("/{consumptionId}")
	public ResponseEntity<Consumption> getConsumptionById(@PathVariable("consumptionId") Integer consumptionId) {
		return new ResponseEntity<Consumption>(consumptionService.getConsumptionById(consumptionId), HttpStatus.OK);
	}

	@GetMapping("/vehicle/{vehicleId}/consumption-count")
	public ResponseEntity<Integer> getConsumptionsByVehicleCount(@PathVariable("vehicleId") Integer vehicleId,
			@RequestBody Vehicle vehicle) {
		Consumption consumption = consumptionService.getConsumptionById(vehicleId);
		if (consumption == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		int count = consumptionService.getCountConsumptionsByVehicle(vehicle);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
//----------------------------------------------------
//	@GetMapping("/vehicles/{vehicleId}/consumptionCount")
//	public ResponseEntity<List<Consumption>> getVehicleByCountConsumption(
//			@PathVariable("vehicleId") Integer vehicleId) {
//			@RequestBody(required = false) Vehicle vehicle) {
//		Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
//		if (vehicle == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}

//		for (Consumption consumption : consumptions) {
//			consumption.getAmount();
//		}
//		List<Consumption> consumptions;
//		if (vehicle != null && vehicle.getPatent() != null) {
//			consumptions = consumptionService.getConsumptionByVehicle(vehicle);
//		}else {
//			consumptions=consumptionService.getConsumptionByVehicle(vehicle)
//		}

//		List<Vehicle> vehicles = vehicleService.getVehicles();
//		List<VehicleConsumptionCount> counts = new ArrayList<>();

//		for (Vehicle vehicle : vehicles) {
//			int consumptionCount = consumptionService.getConsumptionsByVehicle(vehicle).size();
//			VehicleConsumptionCount count = new VehicleConsumptionCount(vehicle.getId(), vehicle.getName(),
//					consumptionCount);
//			counts.add(count);
//		}
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////		return new ResponseEntity<List<VehicleConsumptionCount>>(counts, HttpStatus.OK);
//	}
//
//	// ----------------------------------------------------

	@GetMapping
	public ResponseEntity<List<Consumption>> getConsumptions() {
		return new ResponseEntity<List<Consumption>>(consumptionService.getConsumptions(), HttpStatus.OK);
	}

	@PostMapping("/{userId}/vehicles/{vehicleId}/consumption")
	public ResponseEntity<Consumption> createConsumption(@PathVariable("userId") Integer userId,
			@PathVariable("vehicleId") Integer vehicleId, @RequestBody Consumption consumption) {
		return new ResponseEntity<Consumption>(consumptionService.createConsumption(userId, vehicleId, consumption),
				HttpStatus.CREATED);
	}

	/***
	 * Devuelve todos los username de todos los registros ya predefinidos
	 */
//	@GetMapping("/number")
//	public ResponseEntity<List<Long>>getNumber(){
//		return new ResponseEntity<List<Long>>(consumptionService.getNumbers(),HttpStatus.OK);
//	}
//	@GetMapping("/{consumptionId}")
//	public ResponseEntity<Consumption> getConsumptionById(@PathVariable("consumptionId") Integer consumptionId){
//		return new ResponseEntity<Consumption>(consumptionService.getConsumptionById(consumptionId),HttpStatus.OK);
//	}
//	
//	@GetMapping("/number/{number}")
//	public ResponseEntity<Consumption> getUserByUsername(@PathVariable("number")Long number){
//		return new ResponseEntity<Consumption>(consumptionService.getConsumptionByNumber(number),HttpStatus.OK);
//	}
//	
//	@GetMapping("/number/{number}/{consumptionId}")
//	public ResponseEntity<Consumption> getConsumptionBynumberAndId(@PathVariable("number") Long number,@PathVariable("consumptionId") Integer consumptionId){
//		return new ResponseEntity<Consumption>(consumptionService.getConsumptionByNumberAndId(number, consumptionId),HttpStatus.OK);
//	}

	@DeleteMapping("/{number}")
	public ResponseEntity<Void> deleteConsumption(@PathVariable("number") Long number) {
		consumptionService.deleteConsumptionByNumber(number);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
