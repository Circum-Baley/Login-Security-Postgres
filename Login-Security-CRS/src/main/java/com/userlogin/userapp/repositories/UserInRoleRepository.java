package com.userlogin.userapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.UserInRole;

public interface UserInRoleRepository extends CrudRepository<UserInRole,Integer>{


	
	@Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
	public List<User> findUsersByRoleName(String roleName);

	/*
	 * me devolvera todos los mapping que tenga para ese usuario
	 * */
	public List<UserInRole> findByUser(User user);
}
