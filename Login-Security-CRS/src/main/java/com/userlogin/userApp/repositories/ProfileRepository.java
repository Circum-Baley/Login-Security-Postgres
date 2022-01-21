package com.userlogin.userApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userlogin.userApp.entities.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {

	
	// con la sintaxis " p.id=?1 "  le estamos indicando que queremos obtener el primer parametro
	@Query("SELECT p FROM Profile p WHERE p.user.id=?1 AND p.id=?2")
	public Optional<Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);

}
