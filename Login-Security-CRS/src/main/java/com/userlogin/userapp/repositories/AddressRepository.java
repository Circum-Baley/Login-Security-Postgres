package com.userlogin.userapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{


	
	@Query("SELECT a FROM Address a WHERE a.profile.user.id=?1 AND a.profile.id=?2")
	List<Address>findByProfileId(Integer userId,Integer profileId);
	
	// con la sintaxis " p.id=?1 "  le estamos indicando que queremos obtener el primer parametro
	@Query("SELECT a FROM Address a WHERE a.profile.id=?1 AND a.id=?2")
	public Optional<Address> findByProfileIdAndAddressId(Integer profileId, Integer addressId);

	
	

	
}