package com.userlogin.userapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// QueryMethods//////
	public Optional<User> findByUsername(String username);

	public Optional<User> findByUsernameAndId(String username, Integer id);

	public Optional<User> findByUsernameAndPassword(String username, String password);

//	public Optional List
	// CustomQueries//////
	/**
	 * ESTO NO ES SQL, ------ESTO ES JPQL------ JAVA PERSISTENCE QUERY LANGUAGE
	 */
	// Se realiza la consulta a la base de datos devolviendo todos los registros en
	// los cuales en el atributo username terminenn en "lins"
	// u WHERE u.username like '%lins'")
	@Query("SELECT u.username FROM User u")
	public List<String> findUsername();

	@Query("SELECT u.username FROM User u")
	public Page<String> findUsernamePageSize(Pageable pageable);

	@Query("SELECT c.vehicle.user FROM Consumption c GROUP BY c.vehicle.user ORDER BY COUNT(c) DESC")
	public List<User> findUserWithMostConsumption();

	@Query("SELECT u FROM User u WHERE SIZE(u.vehicles) > 2 ORDER BY SIZE(u.vehicles) DESC")
	public List<User> findUserWithMoreThanTwoVehicles();
}
