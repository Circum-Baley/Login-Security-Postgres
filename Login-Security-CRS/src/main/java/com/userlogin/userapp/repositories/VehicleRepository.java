package com.userlogin.userapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Consumption;
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Query("SELECT c FROM Consumption c WHERE c.vehicle.id = ?1 AND c.id = ?2")
	public Optional<Vehicle> findByVehicleIdAndConsumptionId(Integer vehicleId, Integer consumptionId);

	public Optional<Vehicle> findByPatent(String patent);

	@Query("SELECT u FROM Vehicle v JOIN v.user u WHERE v.id = ?1")
	public List<User> findUsersByVehicleId(Integer vehicleId);


	@Query("SELECT v FROM Vehicle v WHERE v.user.id=?1 AND v.id=?2")
	public Optional<Vehicle> findByUserIdAndVehicleId(Integer userId, Integer vehicleId);

//	public List<String> findPatent();

	//
	@Query("SELECT v FROM Consumption v JOIN FETCH v.vehicle")
	List<Consumption> findAllWithVehicle();

	// CustomQueries//////
	/**
	 * ESTO NO ES SQL, ------ESTO ES JPQL------ JAVA PERSISTENCE QUERY LANGUAGE
	 */
//	// Se realiza la consulta a la base de datos devolviendo todos los registros en los cuales en el atributo username terminenn en "lins"
//	@Query("SELECT u.username FROM User u WHERE u.username like '%lins'")
//	public List<String> findUsername();

}
