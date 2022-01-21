package com.userlogin.userApp.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userApp.entities.User;
import com.userlogin.userApp.entities.UserInRole;
import com.userlogin.userApp.repositories.UserInRoleRepository;
@Service
public class UserInRoleService {

	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	// Con rolesAllowed habilitamos solo los roles ADMIN y ROOT
	@RolesAllowed({"ADMIN","ROOT"})
	public List<User> getUsersByRole(String roleName) {
		return userInRoleRepository.findUsersByRoleName(roleName); 
	}
	
	
	public UserInRole getUserInRoleById(Integer userInRoleId) {
		return userInRoleRepository.findById(userInRoleId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User In Role %d not found", userInRoleId)));		
	}
}
