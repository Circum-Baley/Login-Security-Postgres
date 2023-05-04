package com.userlogin.userapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{


}
