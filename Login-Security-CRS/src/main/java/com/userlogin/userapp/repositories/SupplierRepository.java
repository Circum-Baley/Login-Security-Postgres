package com.userlogin.userapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
