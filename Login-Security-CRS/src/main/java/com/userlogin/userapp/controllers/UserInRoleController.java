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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.UserInRole;
import com.userlogin.userapp.services.RoleService;
import com.userlogin.userapp.services.UserInRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api-usinrole")
@Tag(name = "UserInRole Controller", description = "Operaciones Relacionadas Entre Las Entidades Role Y User")
public class UserInRoleController {

//	private static final Logger log = LoggerFactory.getLogger(UserInRoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserInRoleService userInRoleService;

	@GetMapping("/{roleName}/users")
	@Operation(summary = "Obtiene Los Usuarios Dependiendo Del Rol", description = "Obtienes Usuarios Atraves De Los Roles, Dependiendo Del Role Se Entregaran Distintos Usuarios")
	@ApiResponse(responseCode = "200", description = "El Obtuvo Exitosamente La Lista de Usuarios Con Dicho Rol")
	public ResponseEntity<List<User>> getUsersByRoles(
			@Parameter(name = "roleName", description = "Nombre Del Rol El Cual Se utilizara Para Conseguir Los Usuarios", example = "SUPPORT", required = false) @PathVariable("roleName") String roleName) {
		List<User> listUserXRole = roleService.getUsersByRole(roleName);
		try {
			if (!(roleName.isBlank())) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (listUserXRole.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<List<User>>(listUserXRole, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{userInRoileId}")
	public ResponseEntity<UserInRole> getUserById(@PathVariable("userInRoileId") Integer userInRoleId) {
		UserInRole userInRole = userInRoleService.getUserInRoleById(userInRoleId);
		try {
			if (userInRoleId != null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else if (!(userInRoleId instanceof Integer) || userInRoleId == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<UserInRole>(userInRole, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
//	}@DeleteMapping(/{"id"})
}
