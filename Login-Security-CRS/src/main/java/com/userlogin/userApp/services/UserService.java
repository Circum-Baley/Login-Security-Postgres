package com.userlogin.userApp.services;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userApp.entities.User;
import com.userlogin.userApp.repositories.UserRepository;
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	// se realizo para atraer datos sin indexar, versus el metodo siguiente de abajo
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
//	public Page<User> getUsers(int page,int size){
//		return userRepository.findAll(PageRequest.of(page, size));
//		
//	}
	public List<String> getUsernames(){
		return userRepository.findUsername();
	}
	
	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("user %d not found", userId)));	
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("user %d not foundeichon", username)));
	}
	
	public User getUserByUsernameAndId(String username,Integer userId) {
		return userRepository.findByUsernameAndId(username, userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("user %d , %d ", username,userId)));
		
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d no fount",username)));
		
	}
	
	public User getPieChartData() {
		
	}
	
//	@CacheEvict("users")
	public void deleteUserByUsername(String username) {
		User user = getUserByUsername(username);
		userRepository.delete(user);
	}
	
	

//	@Autowired 
//	private UserRepository userRepository;
//	
//	public List<User> getUsers(){
//		return userRepository.findAll();
//	}
//	
//	private List<User> users = new ArrayList<>();
//
//
//	
//	public List<User> getUser(){
//		return users;
//	}
//	
//	public User getUserByName(String username) {
//		return users.stream().filter(u -> u.getUsername().equals(username)).findAny()
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//						String.format("User %s not found",username)));
//	}
//	
//	public User createdUser(User user) {
//		if(users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
//			throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("user %s ya existe",user.getUsername()));
//		}
//		users.add(user);
//		return user;
//	}
}
