package com.userlogin.userapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.Role;
import com.userlogin.userapp.services.RoleService;

@RestController
@RequestMapping("/api-role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@GetMapping("/list")
	public ResponseEntity<List<Role>> getRoles(){
		//Obtencion de informacion acerca del usuaario que ingreso al ENDPOINT
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {}",authentication.getName());
		log.info("principa {}",authentication.getPrincipal());
		log.info("Credenciales {}",authentication.getCredentials());
		log.info("rol {}",authentication.getAuthorities().toString());
		return new ResponseEntity<List<Role>>(roleService.getRole(),HttpStatus.OK);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<Role> createRoles(@RequestBody Role role){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name {}",authentication.getName());
		log.info("principa {}",authentication.getPrincipal());
		log.info("Credenciales {}",authentication.getCredentials());
		log.info("rol {}",authentication.getAuthorities().toString());
		return new ResponseEntity<Role>(roleService.createRole(role),HttpStatus.CREATED);
	}
	
	@PutMapping("/{roleId}")
	public ResponseEntity<Role> updateRole(@PathVariable("roleId") Integer roleId,@RequestBody Role role){
		return new ResponseEntity<Role>(roleService.updateRole(roleId,role),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{roleId}")
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId")Integer roleId){
		roleService.deleteRole(roleId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

}
