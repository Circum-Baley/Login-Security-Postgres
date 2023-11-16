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
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.Role;
import com.userlogin.userapp.services.RoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api-role")
@Tag(name = "Role Controller", description = "Operaciones Relacionadas Con La Entidad Role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@GetMapping("/list")
	@Operation(summary = "Enlista Todos Los Roles", description = "Realiza Un Lista De Todos Los Roles Que Se Encuentran En La Base De Datos")
	@ApiResponse(responseCode = "200", description = "Roles encontrados y devueltos exitosamente.")
	@ApiResponse(responseCode = "204", description = "No hay roles disponibles (sin contenido).")
	@ApiResponse(responseCode = "404", description = "No se encontraron roles (recurso no encontrado).")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<List<Role>> getRoles() {
		List<Role> roleList = roleService.getAllRoles();
		try {
			if (!roleList.isEmpty() && roleList != null) {
//				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//				if (!(authentication == null)) {
//					log.info("Name {}", authentication.getName());
//					log.info("principa {}", authentication.getPrincipal());
//					log.info("Credenciales {}", authentication.getCredentials());
//					log.info("rol {}", authentication.getAuthorities().toString());
//				}
				return new ResponseEntity<>(roleList, HttpStatus.OK);

			} else if (roleList != null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(roleList, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/roles")
	@Operation(summary = "Crea El Objeto ROLE", description = "Realiza La Creacion De El Objeto ROLE")
	@ApiResponse(responseCode = "400", description = "Verificar El Ingresos Y 'No Ingresos' De Digitos Y Caracteres")
	@ApiResponse(responseCode = "201", description = "Objeto ROLE Creado Exitosamente")
	@ApiResponse(responseCode = "500", description = "Error Interno En El Servidor")
	public ResponseEntity<Role> createRoles(@RequestBody Role role) {
		Role createRole = roleService.createRole(role);
		try {
			if (roleService.roleExists(role.getId())) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (!(authentication == null)) {
					log.info("Name {}", authentication.getName());
					log.info("principa {}", authentication.getPrincipal());
					log.info("Credenciales {}", authentication.getCredentials());
					log.info("rol {}", authentication.getAuthorities().toString());
				}
				return new ResponseEntity<Role>(createRole, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{roleId}")
	@Operation(summary = "Actualizar Rol", description = "Actualiza un rol existente en la base de datos.")
	@ApiResponse(responseCode = "201", description = "Rol actualizado exitosamente.")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta, por ejemplo, campos obligatorios faltantes o inválidos.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Role> updateRole(@PathVariable("roleId") Integer roleId, @RequestBody Role role) {
		try {
			Role roleUpdate = roleService.updateRole(roleId, role);
			return new ResponseEntity<>(roleUpdate, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			// En caso de argumentos no válidos, por ejemplo, si el ID no es válido
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// En caso de otros errores internos
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/delete/{roleId}", produces = "application/json")
	@Operation(summary = "Eliminar un Rol", description = "Elimina un rol por su ID.")
	@ApiResponse(responseCode = "204", description = "Rol eliminado con éxito.")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta, por ejemplo, ID no válido.")
	@ApiResponse(responseCode = "404", description = "Rol no encontrado.")
	@ApiResponse(responseCode = "500", description = "Error interno del servidor.")
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer roleId) {
		try {
			roleService.delete(roleId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			// Manejar el caso de ID no válido u otros problemas en la solicitud
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitud incorrecta", e);
		} catch (EntityNotFoundException e) {
			// Manejar el caso de que el rol no se encuentre
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado", e);
		} catch (Exception e) {
			// Manejar otros errores internos del servidor
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor", e);
		}
	}
}
