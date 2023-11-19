
package com.userlogin.userapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.jws.soap.SOAPBinding.Use;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api-user")
@Tag(name = "User Controller", description = "Operaciones Relacionas Con La Entidad User")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/UserTotalCount")
	@Operation(summary = "Obtener el número total de usuarios", description = "Obtiene el número total de usuarios en el sistema.")
	@ApiResponse(responseCode = "204", description = "Número Total De Usuarios Sin Contenido")
	@ApiResponse(responseCode = "200", description = "Total De Usuarios Obtenida Con Exito")
	@ApiResponse(responseCode = "404", description = "Usuarios No Encontrados")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Long> getTotalObjects() {
		Long userCreated = userService.getCountTotalUser();
		try {
			if (userCreated == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Long>(userCreated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listUser")
//	@Timed("get.users")
	@Operation(summary = "Obtener la lista de usuarios", description = "Obtiene la lista de usuarios en el sistema.")
	@ApiResponse(responseCode = "204", description = "Lista Sin Contenido")
	@ApiResponse(responseCode = "200", description = "Lista De USuarios ")
	@ApiResponse(responseCode = "404", description = "Lista De Usuarios No Encontrados")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	public ResponseEntity<List<User>> getUsers() {
		List<User> userList = userService.getUsers();
		try {
			if (userList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else if (userList.listIterator() == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	@Operation(summary = "Obtener la lista de usuarios", description = "Obtiene la lista de usuarios en el sistema.")
	@ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida")
	@ApiResponse(responseCode = "404", description = "Usuarios no encontrados")
	@ApiResponse(responseCode = "500", description = "Error Interno En El  Servidor")
	public Object getList(HttpServletRequest request, Model model) {
		List<User> userList = userService.getUsers();
		try {
			if (isApiRequest(request)) {
				return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
			} else {
				return ResponseEntity.ok(userList);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean isApiRequest(HttpServletRequest request) {
		String acceptHeader = request.getHeader("Accept");
		return acceptHeader != null && acceptHeader.contains("application/json");
	}

	@GetMapping("/listMore3VehicleUser")
	@Operation(summary = "Obtener usuarios con más de tres vehículos", description = "Obtiene la lista de usuarios que tienen más de tres vehículos.")
	@ApiResponse(responseCode = "404", description = "Usuarios No Encontrados")
	@ApiResponse(responseCode = "204", description = "Listado De Usuarios Con Exito Sin Informacion Apropiada")
	@ApiResponse(responseCode = "200", description = "Lista De Usuarios Con Más De Tres Vehículos Obtenida Con Exito")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor")
	public ResponseEntity<List<User>> getUserWithMoreThanThreeVehicles() {
		List<User> userList = userService.getUserWithMoreThanThreeVehicles();
		try {
			if (userList.size() < 3) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (userList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getMostConsumptionUser")
	@Operation(summary = "Obtiene El Usuario Con Mas Consumos",description = "Estable Como Condicion Solo El Traer El Usuario Con Mas")
	@ApiResponse(responseCode = "204", description = "Sin Datos")
	@ApiResponse(responseCode = "200", description = "Usuario Con Mas Consumo Se Obtuvo Con Exito!")
	@ApiResponse(responseCode = "500", description = "Servidor No Disponible")
	public ResponseEntity<List<User>> getUserWithMostConsumption() {
		List<User> getUserConsumption = userService.getUserWithMostConsumption();
		try {
			if (getUserConsumption.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(getUserConsumption, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	// Siguientes 2 Metodos Estan Incompleto!!!
//	@GetMapping
//	@Operation(summary = "Muestra Los Usuarios Por Paginas", description = "Obtiene Los Usuarios Atraves Paginas Enumeradas Las Cuales Dependiendo Del Tamaño Crecera El Numero De Pagina")
//	@ApiResponse(responseCode = "200", description = "Los Datos Se Han Mostrado Con Exito!!")
//	@ApiResponse(responseCode = "204", description = "Sin Datos En La Base")
//	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
//	public ResponseEntity<Page<User>> getUserPageSize( // http://localhost:8080/api-user?page=2&size=10
//			@RequestParam(required = false, value = "page", defaultValue = "0") int page,
//			@RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
//		return new ResponseEntity<>(userService.getUserPageSize(page, size), HttpStatus.OK);
//	}
//
//	@GetMapping("/username")
//	@Operation(summary = "Obtiene Los Username De Los Usuarios Por Pagina", description = "Obtiene Los USERNAME De Los Usuarios Atraves Paginas Enumeradas Las Cuales Dependiendo Del Tamaño Crecera El Numero De Pagina")
//	@ApiResponse(responseCode = "200", description = "El Listado De USERNAME De Usuarios Se Ha Recuperado Con Éxito.")
//	@ApiResponse(responseCode = "500", description = "Error interno del servidor")
//	public ResponseEntity<Page<String>> getUsernamesPageSize(
//			@Parameter(name = "page", description = "Número de página", example = "1", required = false) @RequestParam(required = false, value = "page", defaultValue = "0") int page,
//			@Parameter(name = "size", description = "Número de página", example = "2", required = false) @RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
//		return new ResponseEntity<>(userService.getUsernamePageSize(page, size), HttpStatus.OK);
//	}

// -- fin 
	@GetMapping("/{userId}")
	// @PreAuthorize("hasRole('USER') or hasRole('ROOT')")
	@Operation(summary = "Obtiene El Usuario Atraves Del ID", description = "Con El ID(identificador) Podremos Obtener El ID Requerido")
	@ApiResponse(responseCode = "404", description = "No Se Encuentra El Usuario Con El ID Especificado")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Asegúrese de proporcionar un ID de Usuario válido.")
	@ApiResponse(responseCode = "200", description = "Informacion Del Usuario Con Exito")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<User> getUserById(
			@Parameter(name = "userId", description = "ID Del Usuario", required = true) @PathVariable Integer userId) {
		User userCreated = userService.getUserById(userId);
		try {
			if (userId <= 0 || userId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<User>(userCreated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/username/{username}")
	@Operation(summary = "Obtiene El Usuario Atraves Del USERNAME", description = "Con El USERNAME Podremos Obtener El USERNAME requerido")
	@ApiResponse(responseCode = "404", description = "No Se Encuentra El Usuario Con El USERNAME Especificado")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Asegúrese de proporcionar un USERNAME de user válido.")
	@ApiResponse(responseCode = "200", description = "Informacion Del Usuario Con Exito")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<User> getUserByUsername(
			@Parameter(name = "username", description = "", required = true) @PathVariable("username") String username) {
		User userCreated = userService.getUserByUsername(username);
		try {
			if (username.length() <= 1 || username == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<User>(userCreated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/username/{username}/{userId}")
	@Operation(summary = "Obtiene El Usuario Atraves Del USERNAME y USERID", description = "Obtiene Nombre De Usuario Y El ID Sobre Usuario")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Asegúrese de proporcionar un ID & USERNAME de USER válido.")
	@ApiResponse(responseCode = "204", description = "Sin Datos En La Base")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<User> getUserByUsernameAndById(
			@Parameter(name = "username") @PathVariable("username") String username,
			@Parameter(name = "userId") @PathVariable("userId") Integer userId) {
		User UserNameId = userService.getUserByUsernameAndById(username, userId);
		try {
			if (username == null || username.isEmpty() || userId.toString().isEmpty() || userId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(UserNameId, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	@Operation(summary = "Crea Al Usuario", description = "Permite crear un nuevo usuario en el sistema.")
	@ApiResponse(responseCode = "200", description = "Usuario Creado")
	@ApiResponse(responseCode = "401", description = "Usuario No Autorizado")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<User> authenticate(@RequestBody User user) {
		try {
			User userAuthenticated = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
			if (userAuthenticated != null) {
				return new ResponseEntity<User>(userAuthenticated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{username}")
	@Operation(summary = "Elimina El Objeto USER", description = "Elimina a traves del USERNAME al usuario, asociado al Objeto USER")
	@ApiResponse(responseCode = "404", description = "Objeto No Encontrado")
	@ApiResponse(responseCode = "204", description = "Sin Contenido")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		User userCreated = userService.getUserByUsername(username);
		try {
			if (userCreated == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			userService.deleteUserByUsername(username);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
