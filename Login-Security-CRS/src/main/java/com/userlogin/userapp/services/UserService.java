package com.userlogin.userapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// se realizo para atraer datos sin indexar, versus el metodo siguiente de abajo
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Long getCountTotalUser() {
		return userRepository.count();
	}
	public Page<User> getUserPageSize(int page, int size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}

	public List<User> getUserWithMoreThanThreeVehicles() {
		return userRepository.findUserWithMoreThanThreeVehicles();
	}

//	public Page<User> getUserPageSize(int page,int size){
//		return userRepository.findAll(PageRequest.of(page,size));
//	}
	public List<User> getUserWithMostConsumption() {
		return userRepository.findUserWithMostConsumption();
	}

	public List<String> getUsername() {
		return userRepository.findUsername();
	}

	public Page<String> getUsernamePageSize(int page, int size) {
		return userRepository.findUsernamePageSize(PageRequest.of(page, size));
	}

	public User getUserById(Integer userId) {
		return userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found", userId)));
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("user %d not foundeichon", username)));
	}

	public User getUserByUsernameAndId(String username, Integer userId) {
		return userRepository.findByUsernameAndId(username, userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("user %d , %d ", username, userId)));

	}

	public User getUserByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("The User %s dosen't EXISTS", username)));
	}

//	@CacheEvict("users")
	public void deleteUserByUsername(String username) {
		User user = getUserByUsername(username);
		userRepository.delete(user);
	}

//	public User getUserByName(String username) {
//		return users.stream().filter(u -> u.getUsername().equals(username)).findAny()
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//						String.format("User %s not found",username)));
//	}

}
