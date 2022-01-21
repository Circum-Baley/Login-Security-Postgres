package com.userlogin.userApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.userlogin.userApp.entities.User;
import com.userlogin.userApp.entities.UserInRole;

import io.lettuce.core.dynamic.annotation.Param;

public interface UserInRoleRepository extends CrudRepository<UserInRole,Integer>{


	
	@Query("SELECT u.user FROM UserInRole u WHERE u.role.name=?1")
	public List<User> findUsersByRoleName(String roleName);

	public List<UserInRole> findByUser(User user);
}
