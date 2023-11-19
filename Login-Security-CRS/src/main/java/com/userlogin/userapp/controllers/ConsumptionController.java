package com.userlogin.userapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
import com.userlogin.userapp.services.ConsumptionService;
import com.userlogin.userapp.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-consumption")
@Tag(name = "Consumption Controller", description = "Operaciones Relacionas Con La Entidad Consumption")
public class ConsumptionController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ConsumptionService consumptionService;

	@GetMapping("/list")
	@Operation(summary = "Obtiene Listado De Consumos", description = "Entrega Recurso Del Objeto CONSUMPTION")
	@ApiResponse(responseCode = "404", description = "No Se Encuentra El Recurso Solicitado")
	@ApiResponse(responseCode = "202", description = "Listado De Consumos OK")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<List<Consumption>> getConsumptions() {
		List<Consumption> listConsumption = consumptionService.getConsumptions();
		try {
			if (listConsumption.isEmpty() || listConsumption == null) {
				return new ResponseEntity<List<Consumption>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Consumption>>(listConsumption, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Consumption>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{consumptionId}")
	@ApiResponse(responseCode = "404", description = "El Recurso Solicitado No Se Encuentra")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "200", description = "Informacion Entrega")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Consumption> getConsumptionById(
			@Parameter(name = "consumptionId", required = true) @PathVariable("consumptionId") Integer consumptionId) {
		Consumption consumptionById = consumptionService.getConsumptionById(consumptionId);
		try {
			if (consumptionById == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (!(consumptionId instanceof Integer)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Consumption>(consumptionById, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/vehicle/{vehicleId}/consumption-count")
	@ApiResponse(responseCode = "404", description = "El Recurso Solicitado No Se Encuentra")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "200", description = "Informacion Entregada Correctamente")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Integer> getConsumptionsByVehicle(@PathVariable("vehicleId") Integer vehicleId,
			@RequestBody Vehicle vehicle) {
		Consumption consumptionsByVehicleId = consumptionService.getConsumptionById(vehicleId);
		try {
			if (consumptionsByVehicleId == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (!(vehicleId instanceof Integer) || vehicleId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			int count = consumptionService.getCountConsumptionsByVehicle(vehicle);
			return new ResponseEntity<>(count, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/vehicle/{vehiclePatent}")
	@Operation(summary = "Lista Informacion De Los Vehiculos", description = "Se Encuentra Listado De Los Vehiculos Con Mas CONSUMOS")
	@ApiResponse(responseCode = "404", description = "El Recurso Solicitado No Se Encuentra")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos y 'NO INGRESO' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "200", description = "Informacion Entrega")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<List<Consumption>> getConsumptionsByVehicle(@PathVariable String vehiclePatent) {
		List<Consumption> consumptionByPatent = consumptionService.getConsumptionByPatent(vehiclePatent);
		Vehicle vehicle = vehicleService.getVehicleByPatent(vehiclePatent);
		try {
			if (vehicle == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (vehiclePatent.matches("[a-zA-Z0=9]{6}") || vehiclePatent == null || vehiclePatent.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<List<Consumption>>(consumptionByPatent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{userId}/vehicles/{vehicleId}/consumption")
	@Operation(summary = "Crea El Objeto CONSUMO", description = "Se Crea El Objeto Consumo Los Cuales "
			+ "Tambien Tienen Objetos Co-Dependientes Que Si No Existen No Se Podra Realizar La Creacion")
	@ApiResponse(responseCode = "200", description = "Informacion Entrega")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos y 'NO INGRESO' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Consumption> createConsumption(@PathVariable("userId") Integer userId,
			@PathVariable("vehicleId") Integer vehicleId, @RequestBody Consumption consumption) {
		Consumption consumptionCreated = consumptionService.createConsumption(userId, vehicleId, consumption);
		try {
			return new ResponseEntity<Consumption>(consumptionCreated, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{number}")
	@Operation(summary = "Elimina El Objeto Consumption", description = "Elimina El Objeto CONSUMO, Condicionado Por El Parametro ")
	@ApiResponse(responseCode = "404", description = "Recurso No Encontrado")
	@ApiResponse(responseCode = "204", description = "Se Ha Realizado Correcta El Proceso Sin Contenido Retornado Ya Que Solo Se Elimino Un Objeto CONSUMO")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos y 'NO INGRESO' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Internal En El Servidor")
	public ResponseEntity<Void> deleteConsumption(@PathVariable("number") Long number) {
		try {
			Long numberLong = Long.valueOf(number);
			if (number == null) {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			} else {
				consumptionService.deleteConsumptionByNumber(numberLong);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (NumberFormatException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

//@GetMapping("/consumptionChart")
//public ViewResolver getViewResolver() {
//    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//    resolver.setPrefix("/view/");
//    resolver.setSuffix(".xhtml");
//    return resolver;
//}
