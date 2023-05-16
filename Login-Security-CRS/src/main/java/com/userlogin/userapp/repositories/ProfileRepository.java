 package com.userlogin.userapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	
	// con la sintaxis " p.id=?1 "  le estamos indicando que queremos obtener el primer parametro
	@Query("SELECT p FROM Profile p WHERE p.user.id=?1 AND p.id=?2")
	public Optional<Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);

}
