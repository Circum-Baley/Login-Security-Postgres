package com.userlogin.userapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.userlogin.userapp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public boolean existsById(Integer userId);

	@Query("SELECT r FROM Role r")
	public List<Role> findAllRoleData();
}
