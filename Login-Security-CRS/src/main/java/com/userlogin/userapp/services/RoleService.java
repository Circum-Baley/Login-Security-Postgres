
package com.userlogin.userapp.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.Role;
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.repositories.RoleRepository;


@Service
//@LoginSecurityCRSSecurityRules
public class RoleService {
	
	
	private static final Logger log = LoggerFactory.getLogger(RoleService.class);


	@Autowired
	private RoleRepository roleRepository; //JpaRepository
	
	//user in role --
	@Autowired
	private UserInRoleService userInRoleRepository;
	


	public List<User> getUsersByRole(String roleName){
		log.info("Getting User By Role {}",roleName);
		return userInRoleRepository.getUsersByRole(roleName);
	}
	
//	@Cacheable("roles")
	// Listara el recurso de los roles
	public List<Role> getRole(){ 
		return roleRepository.findAll();
	}
	
	// Creara el recurso de role
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}
	
	//Servicio que actualizara Los Roles que se deseen modificar
	public Role updateRole(Integer roleId, Role role) {
		Optional<Role> result = roleRepository.findById(roleId);
		if(result.isPresent()) {
			return roleRepository.save(role);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("RoleId $d Not Found, Not Exists",roleId));
		}
	}
//	Servicio que eliminara a traves del ID el recurso seleccionado
	public void deleteRole(Integer roleId) {
		Optional<Role> result = roleRepository.findById(roleId);
		if(result.isPresent()) {
			roleRepository.delete(result.get());
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("RoleId %d doesn't exists",roleId));
		}
	}
	
	/////////////////////////////////////////////
//	private UserRepository userRepository;
//	public Role createRole(Integer userId,Role role) {
//		Optional<User> result = userRepository.findById(userId);
//		if(result.isPresent()) {
//			profile.setUser(result.get());
//			return profileRepository.save(profile);
//		}else {
//	 		throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d not foundechion",userId));
//		}
//	}
}
