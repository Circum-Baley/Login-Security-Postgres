package com.userlogin.userApp.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userApp.entities.User;
import com.userlogin.userApp.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
//	@GetMapping
//	public ResponseEntity<Page<User>> getUsers(@RequestParam("page") int page,@RequestParam("size") int size){
//		return new ResponseEntity<>(userService.getUsers(page,size),HttpStatus.OK);
//	}
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
	}
	/***
	 * Devuelve todos los username de todos los registros ya predefinidos*/
	@GetMapping("/username")
	public ResponseEntity<List<String>> getUsernames(){
		return new ResponseEntity<>(userService.getUsernames(),HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId){
		return new ResponseEntity<User>(userService.getUserById(userId),HttpStatus.OK);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username")String username){
		return new ResponseEntity<User>(userService.getUserByUsername(username),HttpStatus.OK);
	}
	@GetMapping("/username/{username}/{userId}")
	public ResponseEntity<User> getUserByUsernameAndById(@PathVariable("username") String username,@PathVariable("userId") Integer userId){
		return new ResponseEntity<User>(userService.getUserByUsernameAndId(username, userId),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody User user){
		return new ResponseEntity<User>(userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()),HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		userService.deleteUserByUsername(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
//	private UserService userService;
//	
//	@GetMapping("/users")
//	public ResponseEntity<List<User>> getUsers(){
//		return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
//	}
//	
//	@PostMapping("(/users")
//	public ResponseEntity<User> createUser(@RequestBody User user){
//		return new ResponseEntity<User>(userService.createdUser(user) ,HttpStatus.CREATED);
//	}
//}
