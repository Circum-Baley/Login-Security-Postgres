package com.userlogin.userApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.userlogin.userApp.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{


}
