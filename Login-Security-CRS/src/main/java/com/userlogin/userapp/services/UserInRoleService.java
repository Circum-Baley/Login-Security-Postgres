package com.userlogin.userapp.services;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.UserInRole;
import com.userlogin.userapp.repositories.UserInRoleRepository;
@Service
public class UserInRoleService {

	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	// Con rolesAllowed habilitamos solo los roles ADMIN y ROOT
//	@RolesAllowed({"ADMIN","ROOT"})
	public List<User> getUsersByRole(String roleName) {
		return userInRoleRepository.findUsersByRoleName(roleName); 
	}
	
	
	public UserInRole getUserInRoleById(Integer userInRoleId) {
		return userInRoleRepository.findById(userInRoleId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User In Role %d not found", userInRoleId)));		
	}
}
