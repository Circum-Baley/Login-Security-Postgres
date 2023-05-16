package com.userlogin.userapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Vehicle;

@Repository
public interface VehicleRepository  extends JpaRepository<Vehicle, Integer>{

	
	@Query("SELECT c FROM Consumption c WHERE c.vehicle.id = ?1 AND c.id = ?2")
	public Optional<Vehicle> findByVehicleIdAndConsumptionId(Integer vehicleId, Integer consumptionId);
	
	public Optional<Vehicle>findByPatent(String patent);
	@Query("SELECT v FROM Vehicle v WHERE v.user.id=?1 AND v.id=?2")
	public Optional<Vehicle>findByUserIdAndVehicleId(Integer userId,Integer vehicleId);

//	public List<String> findPatent();

	
	
	
	//CustomQueries//////
	/**
	 * ESTO NO ES SQL, 
	 * ------ESTO ES JPQL------
	 * JAVA PERSISTENCE QUERY LANGUAGE
	 * */
//	// Se realiza la consulta a la base de datos devolviendo todos los registros en los cuales en el atributo username terminenn en "lins"
//	@Query("SELECT u.username FROM User u WHERE u.username like '%lins'")
//	public List<String> findUsername();
	

	

}
