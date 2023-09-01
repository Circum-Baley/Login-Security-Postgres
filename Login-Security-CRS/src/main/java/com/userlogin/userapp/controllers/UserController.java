package com.userlogin.userapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api-user")
@Api(value = "User Management System", description = "Operaciones relacionadas con la gestión de usuarios")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/UserTotalCount")
	@ApiOperation(httpMethod = "GET", value = "Obtener El Total De Usuarios", notes = "Entrega El Numero Total De Ususarios Registrados")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "NOT_FOUND"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR!!") })
	public ResponseEntity<Long> getTotalObjects() {
		Long userCreated = userService.getCountTotalUser();

		try {
			if (userCreated == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Long>(userCreated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listUser")
//	@Timed("get.users")
	@ApiOperation(httpMethod = "GET", value = "Retorna Una Lista De Usuario", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "NOT_FOUND"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR!!") })
	public ResponseEntity<List<User>> getUsers() {
		try {
			List<User> userList = userService.getUsers();
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		} catch (Unauthorized e) {
			return new ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	public Object getList(HttpServletRequest request, Model model) {
		List<User> userList = userService.getUsers();
		if (isApiRequest(request)) {
			return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
		} else {
			return ResponseEntity.ok(userList);
		}
	}

	private boolean isApiRequest(HttpServletRequest request) {
		String acceptHeader = request.getHeader("Accept");
		return acceptHeader != null && acceptHeader.contains("application/json");
	}

	@GetMapping("/listMore3VehicleUser")
	@ApiOperation(httpMethod = "GET", value = "Obtiene Valores De Usuarios Con Mas De 3 Vehiculos", notes = "Entrega El O Los Usuarios Que Contengan Mas De 3 Vehiculos A Cargo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "NOT_FOUND"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR!!") })
	public ResponseEntity<List<User>> getUserWithMoreThanThreeVehicles() {
		List<User> userList = userService.getUserWithMoreThanThreeVehicles();
		try {
			if (userList.size() <= 3) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listConsumptionUser")
	public ResponseEntity<List<User>> getUserWithMostConsumption() {
		return new ResponseEntity<List<User>>(userService.getUserWithMostConsumption(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<User>> getUserPageSize( // http://localhost:8080/api-user?page=2&size=10
			@RequestParam(required = false, value = "page", defaultValue = "0") int page,
			@RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
		return new ResponseEntity<>(userService.getUserPageSize(page, size), HttpStatus.OK);
	}

	@GetMapping("/username")
	public ResponseEntity<Page<String>> getUsernamesPageSize(
			@RequestParam(required = false, value = "page", defaultValue = "0") int page,
			@RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
		return new ResponseEntity<>(userService.getUsernamePageSize(page, size), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "Retorna Como Respuesta Un Usuario En Base Al ID Entregado Por Parametro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "NOT_FOUND"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR!!") })
//	@PreAuthorize("hasRole('USER') or hasRole('ROOT')")
	public ResponseEntity<User> getUserById(
			@ApiParam(value = "Id Del Usuario", required = true, example = "2") @PathVariable Integer userId) {
		try {
			User userCreated = userService.getUserById(userId);
			if (userCreated == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(userCreated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/username/{username}")
	@ApiOperation(httpMethod = "GETS", value = "bbtiene El Username Del Usuario", notes = "Retorna Un Usuario En Base Al Username Que Se Le Proporciono")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "NOT_FOUND"),
			@ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR!!") })
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		try {
			User userCreated = userService.getUserByUsername(username);
			if (userCreated == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(userCreated, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/username/{username}/{userId}")
	public ResponseEntity<User> getUserByUsernameAndById(@PathVariable("username") String username,
			@PathVariable("userId") Integer userId) {
		return new ResponseEntity<User>(userService.getUserByUsernameAndId(username, userId), HttpStatus.OK);
	}

	// NO ES CORRECTO pero se utiuliza para poder realizar un QUERYMETHOD
	// http://localhost:8080/api-user
	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody User user) {
		return new ResponseEntity<User>(
				userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
	}

	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
		userService.deleteUserByUsername(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}