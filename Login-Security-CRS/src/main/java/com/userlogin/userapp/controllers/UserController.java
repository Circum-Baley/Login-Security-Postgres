package com.userlogin.userapp.controllers;

import java.nio.file.InvalidPathException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api-user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/UserTotalCount")
	public ResponseEntity<Long> getTotalObjects() {
		return new ResponseEntity<Long>(userService.getCountTotalUser(), HttpStatus.OK);
	}

	@GetMapping("/listUser")
//	@Timed("get.users")
	@ApiOperation(httpMethod = "GET", value = "Retorna Una Lista De Usuario", responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista OK"),
			@ApiResponse(code = 401, message = "NO AUTORIZADO"),
			@ApiResponse(code = 500, message = "Error interno del servidor") })
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
	public ResponseEntity<List<User>> getUserWithMoreThanThreeVehicles() {
		List<User> userList = userService.getUserWithMoreThanThreeVehicles();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
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
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Un-Authorize"), @ApiResponse(code = 403, message = "Refused"),
			@ApiResponse(code = 404, message = "Path Wrong, Check it!!") })
	@PreAuthorize("hasRole('USER') or hasRole('ROOT')")
	public ResponseEntity<User> getUserById(
			@ApiParam(value = "Id Del Usuario", required = true, example = "123") @PathVariable Integer userId) {
		try {
			User userCreated = userService.getUserById(userId);
			if (userCreated == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
//			@ApiParam(value = "Id Del Usuario", required = true, name = "userId", example = "123") @PathVariable Integer userId) {
//		return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
//	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
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