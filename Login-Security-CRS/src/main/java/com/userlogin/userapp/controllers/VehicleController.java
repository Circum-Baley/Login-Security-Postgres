package com.userlogin.userapp.controllers;

import java.util.List;

import org.checkerframework.checker.builder.qual.ReturnsReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.Vehicle;
import com.userlogin.userapp.services.UserService;
import com.userlogin.userapp.services.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-vehicle") // {userId}/vehicles/{vehicleId}")
@Tag(name = "Vehicle Controller", description = "Operaciones Relaciones Con La Entidad Vehicle")
public class VehicleController {

	private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/user/{userId}/vehicle/{vehicleId}")
	@Operation(summary = "Obtener Vehículo por ID de Usuario e ID de Vehículo", description = "Obtiene un vehículo por su ID de usuario e ID de vehículo.")
	@ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve el vehículo solicitado")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. El ID de usuario o de vehículo es inválido.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Vehicle> getByUserIdAndVehicleId(@PathVariable("userId") Integer userId,
			@PathVariable("vehicleId") Integer vehicleId) {
		Vehicle getUserIdAndVehicleId = vehicleService.getByUserIdAndVehicleId(userId, vehicleId);
		try {
			if (!(userId instanceof Integer) && vehicleId != null
					|| userId != null && !(vehicleId instanceof Integer)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Vehicle>(getUserIdAndVehicleId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{vehiclePatente}")
	@Operation(summary = "Obtener Consumo Total de Vehículo por Patente", description = "Obtiene el consumo total de un vehículo por su patente.")
	@ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve el consumo total del vehículo.")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. La patente del vehículo es inválida.")
	@ApiResponse(responseCode = "404", description = "Recurso no encontrado. El vehículo con la patente proporcionada no existe.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Double> getTotalConsumptionVehicle(@PathVariable("vehiclePatente") String vehiclePatente) {
		Double getTotal = vehicleService.getTotalConsumptionVehicle(vehiclePatente);
		try {
			if (vehiclePatente.length() < 6 || vehiclePatente == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else if (getTotal.isNaN()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(getTotal, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/vehicle/{vehicleId}")
	@Operation(summary = "Obtener Vehículo por ID", description = "Obtiene un vehículo por su ID.")
	@ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve el vehículo solicitado.")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. El ID de vehículo es inválido.")
	@ApiResponse(responseCode = "404", description = "Recurso no encontrado. El vehículo con el ID proporcionado no existe.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable("vehicleId") Integer vehicleId) {
		Vehicle getVehicleById = vehicleService.getVehicleById(vehicleId);
		try {
			if (!(vehicleId instanceof Integer)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else if (getVehicleById == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Vehicle>(getVehicleById, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/user/{userId}")
	@Operation(summary = "Crear Vehículo para Usuario", description = "Crea un vehículo asociado a un usuario.")
	@ApiResponse(responseCode = "201", description = "Operación exitosa. Devuelve el vehículo creado.")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. El ID de usuario es inválido o negativo.")
	@ApiResponse(responseCode = "404", description = "Recurso no encontrado. El usuario con el ID proporcionado no existe.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Vehicle> createVehicleUser(@PathVariable("userId") Integer userId,
			@RequestBody Vehicle vehicle) {
		Vehicle vehicleCreate = vehicleService.createVehicleUser(userId, vehicle);
		User userExist = userService.getUserById(userId);
		try {
			if (!(userId instanceof Integer || userId < 0)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else if (userExist == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Vehicle>(vehicleCreate, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	@Operation(summary = "Obtener Lista de Vehículos", description = "Obtiene una lista de todos los vehículos.")
	@ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve la lista de vehículos.")
	@ApiResponse(responseCode = "404", description = "Recurso no encontrado. No hay vehículos registrados.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<List<Vehicle>> listVehicle() {
		List<Vehicle> listVehicle = vehicleService.getVehicles();
		if (listVehicle.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Vehicle>>(listVehicle, HttpStatus.OK);
	}

	@GetMapping("/consumption")
	@Operation(summary = "Obtener Lista de Consumos con Vehículo", description = "Obtiene una lista de todos los consumos asociados a un vehículo.")
	@ApiResponse(responseCode = "200", description = "Operación exitosa. Devuelve la lista de consumos con vehículos.")
	@ApiResponse(responseCode = "404", description = "Recurso no encontrado. No hay consumos con vehículos registrados.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<List<Consumption>> getAllConsumptionsWithVehicle() {
		List<Consumption> listVehicleWithConsumos = vehicleService.getAllConsumptionWithVehicle();
		if (listVehicleWithConsumos.isEmpty() || listVehicleWithConsumos == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Consumption>>(listVehicleWithConsumos, HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Crear Vehículo", description = "Crea un nuevo vehículo.")
	@ApiResponse(responseCode = "201", description = "Operación exitosa. Devuelve el vehículo creado.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Vehicle> createVehicles(@RequestBody Vehicle vehicle) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {}", authentication.getName());
		log.info("principa {}", authentication.getPrincipal());
		log.info("Credenciales {}", authentication.getCredentials());
		log.info("rol {}", authentication.getAuthorities().toString());
		return new ResponseEntity<Vehicle>(vehicleService.createVehicle(vehicle), HttpStatus.CREATED);
	}

	@DeleteMapping("/{patent}")
	@Operation(summary = "Eliminar Vehículo por Patente", description = "Elimina un vehículo por su patente.")
	@ApiResponse(responseCode = "204", description = "Operación exitosa. No hay contenido para devolver.")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. La patente del vehículo es inválida.")
	@ApiResponse(responseCode = "404", description = "Recurso no encontrado. El vehículo con la patente proporcionada no existe.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Void> deleteVehicle(@PathVariable("patent") String patent) {
		try {
			if (patent == null || patent.isBlank() || !(patent.matches("[a-zA-Z0-9]{6}"))) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Vehicle vehicle = vehicleService.getVehicleByPatent(patent);
			if (vehicle == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			vehicleService.deleteVehicleByPatent(patent);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
//	        log.error("Error creating vehicle", e);
//	        String errorMessage = "Error al crear el vehículo.";
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
/*
 */