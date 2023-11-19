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

import com.userlogin.userapp.entities.Device;
import com.userlogin.userapp.entities.Profile;
import com.userlogin.userapp.services.DeviceService;
import com.userlogin.userapp.services.ProfileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api-profile")
@Tag(name = "Profile Controller", description = "Operaciones Relacionadas Con La Entidad Profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	@Autowired
	private DeviceService deviceService;

	@GetMapping("/{profileId}")
	@Operation(summary = "Obtiene un perfil por su ID", description = "Obtiene un perfil basado en su ID.")
	@ApiResponse(responseCode = "200", description = "Perfil Encontrado con Exito")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Profile> getProfileById(@Parameter(name = "profileId") @PathVariable Integer profileId) {
		Profile getProfile = profileService.getProfileById(profileId);
		try {
			return new ResponseEntity<Profile>(getProfile, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	@Operation(summary = "Crea Un Nuevo Perfil", description = "Creacion De Un Perfil En Un Usuario Existente")
	@ApiResponse(responseCode = "404", description = "No Se Encuentra El Recurso Solicitado")
	@ApiResponse(responseCode = "201", description = "PERFIL Creado Con Exito")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Profile> createProfileS(@PathVariable("userId") Integer userId,
			@RequestBody Profile profile) {
		Profile profileCreated = profileService.createProfileS(userId, profile);
		try {
			return new ResponseEntity<Profile>(profileCreated, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/createDevice/{profileId}")
	@Operation(summary = "Crea un dispositivo en un perfil", description = "Creacion un dispositivo en un perfil existente.")
	@ApiResponse(responseCode = "201", description = "DISPOSITIVO Creado Con Exitosamente")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Device> createDeviceProfile(@PathVariable("profileId") Integer profileId,
			@RequestBody Device device) {
		Device deviceCreateProfile = deviceService.createDeviceProfile(profileId, device);
		try {
			return new ResponseEntity<Device>(deviceCreateProfile, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userId}/{profileId}")
	@Operation(summary = "Obtener Informacion De USUARIO Y PERFIL", description = "Obtiene Informacion Acerca De ")
	@ApiResponse(responseCode = "200", description = "Recurso Solicitado Exitosamente")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Profile> getById(@PathVariable("userId") Integer userId,
			@PathVariable("profileId") Integer profileId) {
		Profile getUserAndProfile = profileService.getByUserIdAndProfileId(userId, profileId);
		try {
			return new ResponseEntity<Profile>(getUserAndProfile, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	@Operation(summary = "Obtener Listado De PERFILES", description = "Obtiene Un Listado De Todos Los Perfiles Existentes")
	@ApiResponse(responseCode = "200", description = "Recurso Solicitado Exitosamente")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	public ResponseEntity<List<Profile>> getProfile() {
		List<Profile> listProfile = profileService.getProfile();
		if (listProfile.isEmpty() || listProfile.size() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Profile>>(listProfile, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	@Operation(summary = "Elimina El Objeto PERFIL", description = "Elimina El Objeto PERFIL De Un USUARIO Existente")
	@ApiResponse(responseCode = "204", description = "Eliminacion De Recurso Con Exito")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Void> deleteProfile(@PathVariable("userId") Integer userId) {
		try {
			profileService.deleteUserRole(userId);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
