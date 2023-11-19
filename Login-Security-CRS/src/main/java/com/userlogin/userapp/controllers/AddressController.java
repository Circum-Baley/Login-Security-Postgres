package com.userlogin.userapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.Address;
import com.userlogin.userapp.entities.Profile;
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.services.AddressService;
import com.userlogin.userapp.services.ProfileService;
import com.userlogin.userapp.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@RestController
@RequestMapping("/api-address")

@Tag(name = "Address Controller", description = "Operaciones Relacionadas Con La Entidad Address")
public class AddressController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private AddressService addressService;

	@GetMapping("/{userId}/profiles/{profileId}/addresses")
	@Operation(summary = "Buscar direcciones por usuario y perfil", description = "Recupera una lista de direcciones por ID de usuario y perfil.")
	@ApiResponse(responseCode = "404", description = "Objetos No Encontrados")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Asegúrese de proporcionar Informacion Valida Para Obtener Recursos.")
	@ApiResponse(responseCode = "200", description = "Lista De Direccion Por Perfil Y Usuario Correcta")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<List<Address>> findAddressesByProfileAndUserId(
			@Parameter(name = "userId", description = "ID de Usuario", required = true) @PathVariable("userId") Integer userId,
			@Parameter(name = "profileId", description = "ID de Perfil", required = true) @PathVariable("profileId") Integer profileId) {
		List<Address> getAddressProfileUser = addressService.findAddressesByProfileAndUserId(userId, profileId);
		User existUser = userService.getUserById(userId);
		Profile existProfile = profileService.getProfileById(profileId);
		try {
			if (existProfile == null || existUser == null) {
				return new ResponseEntity<List<Address>>(HttpStatus.NOT_FOUND);
			} else if (!(userId instanceof Integer) || !(profileId instanceof Integer)) {
				return new ResponseEntity<List<Address>>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<List<Address>>(getAddressProfileUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Address>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/{userId}/profiles/{profileId}/addresses")
	@Operation(summary = "Crear dirección", description = "Crea una nueva dirección para un usuario y perfil específicos.")
	@ApiResponse(responseCode = "404", description = "Recurso No Encontrados")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Asegúrese de proporcionar Informacion Valida Para Obtener Recursos Solicitado.")
	@ApiResponse(responseCode = "201", description = "Objeto Address Creado")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Address> createAddresses(
			@Parameter(name = "userId", description = "ID de usuario", required = true) @PathVariable("userId") Integer userId,
			@Parameter(name = "profileId", description = "ID de perfil", required = true) @PathVariable("profileId") Integer profileId,
			@RequestBody Address address) {
		Address createdAddress = addressService.createAddress(userId, profileId, address);
		Profile existProfile = profileService.getProfileById(profileId);
		User existUser = userService.getUserById(userId);
		try {
			if (existProfile == null || existUser == null) {
				return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
			} else if (!(userId instanceof Integer) && !(profileId instanceof Integer) && address == null) {
				return new ResponseEntity<Address>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Address>(createdAddress, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/{addressId}")
	@Operation(summary = "Eliminar dirección", description = "Elimina una dirección por su ID.")
	@ApiResponse(responseCode = "404", description = "Direccion No Encontrada")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Asegúrese de proporcionar un ID de dirección válido.")
	@ApiResponse(responseCode = "204", description = "Se Procede A La Eliminacion De La Direccion Sin Retorno De Contenido")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Address> deleteAddress(
			@Parameter(name = "addressId", description = "ID de dirección", required = true) @PathVariable("addressId") Integer addressId) {
		Address existigAddress = addressService.getAddressById(addressId);
		try {
			// Verifica si la dirección existe antes de eliminarla
			if (existigAddress == null) {
				// La Dirección No Existe, Se Devuelve Un Error 404
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (!(addressId instanceof Integer)) {
				return new ResponseEntity<Address>(HttpStatus.BAD_REQUEST);
			}
			// La Direccion Existe Se Procede A La Eliminacion Sin Retorno De Contenido
			addressService.delete(addressId);
			return new ResponseEntity<Address>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// Error Interno En El Servidor
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	@Operation(summary = "Obtener lista de direcciones", description = "Recupera Una Lista De Todas Las Direcciones")
	@ApiResponse(responseCode = "404", description = "Recurso Solicitado No Encontrado")
	@ApiResponse(responseCode = "200", description = "Lista De Usuarios OK")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<List<Address>> getAddressList() {
		List<Address> addressList = addressService.getAddressList();
		try {
			if (addressList == null || addressList.isEmpty()) {
				return new ResponseEntity<List<Address>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Address>>(addressList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

/**
 * @GetMapping public ResponseEntity<List<Role>> getRoles(){ return new
 *             ResponseEntity<List<Role>>(roleService.getRole(),HttpStatus.OK);
 *             }
 * 
 *             @PostMapping("/roles") public ResponseEntity<Role>
 *             createRoles(@RequestBody Role role){ return new
 *             ResponseEntity<Role>(roleService.createRole(role),HttpStatus.CREATED);
 *             }
 * 
 *             @PutMapping("/{roleId}") public ResponseEntity<Role>
 *             updateRole(@PathVariable("roleId") Integer roleId,@RequestBody
 *             Role role){ return new
 *             ResponseEntity<Role>(roleService.updateRole(roleId,role),HttpStatus.CREATED);
 *             }
 * 
 *             @DeleteMapping("/{addressId}") public ResponseEntity<Void>
 *             deleteAddress(@PathVariable("addreessId")Integer addressId){
          addressService.deleteAddress(addressId); return new
 *             ResponseEntity<Void>(HttpStatus.NO_CONTENT); }
 */