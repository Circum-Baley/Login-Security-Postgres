package com.userlogin.userapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.Device;
import com.userlogin.userapp.services.DeviceService;
import com.userlogin.userapp.services.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-device")
@Tag(name = "Device Controller", description = "Operaciones Relacionas Con La Entidad Device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;


	@PostMapping
	@Operation(summary = "Crea El Objeto DEVICE", description = "Realiza La Creacion Del Modelo DEVICE")
	@ApiResponse(responseCode = "201", description = "Objeto DISPOSITIVO Creado")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Device> createDevice(@RequestBody Device device) {
		Device deviceCreated = deviceService.createDevice(device);
		try {
			return new ResponseEntity<Device>(deviceCreated, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Obtener Informacion Del Objeto DISPISITIVO", description = "Obtiene Informacion Detallada En Base Del ID Entregado por Parametro")
	@ApiResponse(responseCode = "404", description = "Recurso Solicitado No Encontrado")
	@ApiResponse(responseCode = "200", description = "Recurso Solicitado Con Exito")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	@GetMapping("/{deviceId}")
	public ResponseEntity<Device> getDeviceById(@PathVariable("deviceId") Integer deviceId) {
		Device device = deviceService.getDeviceById(deviceId);
		try {
			if (device == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Device>(device, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Operation(summary = "Obtener Lista De DISPOSITIVOS", description = "Obtiene Lista De DISPOSITIVOS Y Los Enlista")
	@ApiResponse(responseCode = "404", description = "Recurso Solicitado No Encontrado")
	@ApiResponse(responseCode = "200", description = "Informacion De Recursos Exitosa - OK")
	@GetMapping("/list")
	public ResponseEntity<List<Device>> getDevicesList() {
		List<Device> deviceList = deviceService.getDevices();
		if (deviceList == null || deviceList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Device>>(deviceList, HttpStatus.OK);
	}

	@GetMapping("/listDeviceProfileAndUser")
	@Operation(summary = "Obtener Informacion De DISPOSITIVO, PERFIL Y USUARIO", description = "Obtiene Informacion Detallada De DISPOSITIVO,PERFIL Y USUARIO De Todos Los Objetos De dichos Modelos")
	@ApiResponse(responseCode = "404", description = "Recurso Solicitado No Encontrado")
	@ApiResponse(responseCode = "200", description = "Informacion De Recursos Exitosa - OK")
	public ResponseEntity<List<Device>> getDeviceProfileAndUser() {
		List<Device> listDevicePU = deviceService.getDeviceProfileUser();
		if (listDevicePU == null || listDevicePU.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Device>>(listDevicePU, HttpStatus.OK);
	}

	@GetMapping
	public String listarPersonas(Model model) {
		List<Device> devices = deviceService.getDevices();
		model.addAttribute("device", devices);
		return "index";
	}

	@PutMapping("/device/{deviceId}/{profileId}")
	@Operation(summary = "Desvincula El Objeto DISPISITIVO De PROFILE", description = "Desvincula Los Objetos Device Y Profile Entre si")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "200", description = "Informacion De Recursos Exitosa - OK")
	public ResponseEntity<Device> unlinkDeviceProfile(@PathVariable("deviceId") Integer deviceId,
			@PathVariable("profileId") Integer profileId) {
		Device unlinkDevicePro = deviceService.unlinkDeviceFromProfile(deviceId, profileId);
		if (unlinkDevicePro == null) {
			throw new IllegalArgumentException("BAD_REQUEST");
		}
		return new ResponseEntity<Device>(unlinkDevicePro, HttpStatus.OK);
	}

	@PutMapping("/{deviceId}")
	@Operation(summary = "Actualiza El Objeto DISPOSITIVO", description = "Actualiza La Informacion De La Clase DEVICE")
	@ApiResponse(responseCode = "404", description = "Recurso Solicitado No Encontrado")
	@ApiResponse(responseCode = "201", description = "Informacion De Recursos Exitosa - OK")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Device> updateDevice(@PathVariable("deviceId") Integer deviceId, @RequestBody Device device) {
		Device deviceUpdate = deviceService.updateDevice(deviceId, device);
		try {
			if (device == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Device>(deviceUpdate, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{deviceId}")
	@Operation(summary = "Elimina El Objeto DISPOSITIVO", description = "Atraves Del Id Que Se Le Entrega Por Parametro Se Elimina Dicho Objeto")
	@ApiResponse(responseCode = "204", description = "Se Ha Eliminado Con Exito")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Void> deleteDevice(@PathVariable("deviceId") Integer deviceId) {
		try {
			deviceService.deleteDeviceById(deviceId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}