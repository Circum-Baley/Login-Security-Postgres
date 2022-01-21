//package com.userlogin.userApp.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.userlogin.userApp.entities.User;
//import com.userlogin.userApp.services.UserService;
//
//@RestController
///Definicion Del Recurso
//@RequestMapping("/v1users")
//public class UserControllerV1 {
//	
//	@Autowired
//	private UserService userService;
//	
//	@GetMapping("/v1")
//	public ResponseEntity<List<User>> getUsers(){
//		return new ResponseEntity<List<User>>(userService.getUsers(),HttpStatus.OK);
//	}
//	@GetMapping("/{username}")
//	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
//		return new ResponseEntity<User>(userService.getUserByName(username),HttpStatus.OK);
//	}
//	
//	@PostMapping
//	public ResponseEntity<User> createUser(@RequestBody User user){
//		return new ResponseEntity<User>(userService.createdUser(user) ,HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/{username}")
//	public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user){
//		return new ResponseEntity<User>(userService.updateUser(user,username),HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{username}")
//	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
//		userService.deleteUser(username);
//	}                   INCOMPLETO
//	
//	
//}