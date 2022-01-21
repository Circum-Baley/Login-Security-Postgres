package com.userlogin.userApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlogin.userApp.entities.Profile;
import com.userlogin.userApp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	//QueryMethods//////
	public Optional<User>findByUsername(String username);
	public Optional<User>findByUsernameAndId(String username,Integer id);
	public Optional<User>findByUsernameAndPassword(String username, String password);

	
	
	
	//CustomQueries//////
	/**
	 * ESTO NO ES SQL, 
	 * ------ESTO ES JPQL------
	 * JAVA PERSISTENCE QUERY LANGUAGE
	 * */
	// Se realiza la consulta a la base de datos devolviendo todos los registros en los cuales en el atributo username terminenn en "lins"
	@Query("SELECT u.username FROM User u WHERE u.username like '%lins'")
	public List<String> findUsername();

}
