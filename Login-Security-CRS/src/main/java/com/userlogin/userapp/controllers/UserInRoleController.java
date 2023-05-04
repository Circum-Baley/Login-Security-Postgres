package com.userlogin.userapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.UserInRole;
import com.userlogin.userapp.services.RoleService;
import com.userlogin.userapp.services.UserInRoleService;


@Controller
@RequestMapping("/api-usinro")
public class UserInRoleController {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserInRoleController.class);

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserInRoleService userInRoleService;
	
	@GetMapping("/{roleName}/users")
	public ResponseEntity<List<User>> getUsersByRoles(@PathVariable("roleName") String roleName){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {}",authentication.getName());
		log.info("principa {}",authentication.getPrincipal());
		log.info("Credenciales {}",authentication.getCredentials());
		log.info("rol {}",authentication.getAuthorities().toString());
		return new ResponseEntity<List<User>>(roleService.getUsersByRole(roleName), HttpStatus.OK);
	}
	
	@GetMapping("/{userInRoileId}")
	public ResponseEntity<UserInRole> getUserById(@PathVariable("userInRoileId") Integer userInRoleId){
		return new ResponseEntity<UserInRole>(userInRoleService.getUserInRoleById(userInRoleId),HttpStatus.OK);
	}
	  
	
	
	
//	@GetMapping
//	public Response

}
