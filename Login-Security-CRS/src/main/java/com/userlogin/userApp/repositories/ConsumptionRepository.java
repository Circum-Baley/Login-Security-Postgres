package com.userlogin.userApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlogin.userApp.entities.Consumption;
import com.userlogin.userApp.entities.User;
import com.userlogin.userApp.entities.Vehicle;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Integer>{

	@Query("SELECT c FROM Consumption c WHERE c.vehicle.user.id=?1 AND c.vehicle.id=?2")
	public Optional<Consumption> findByVehicleId(Integer userId, Integer vehicleId);
	
	
	
	@Query("SELECT c FROM Consumption c WHERE c.vehicle.id=?1 AND c.id=?2")
	public Optional<Consumption> findByVehicleIdAndConsumptionId(Integer vehicleId,Integer consumptionId);
//
//	public Optional<Consumption> findByNumber(Long number);
//	
//
//	public Optional<Consumption> findById(Long consumptionId);
//
//	//QueryMethods//////
//	public List<Long> findByNumbers();
//	public Optional<Consumption>findByNumberAndId(Long number,Integer consumptionId);
//
//	public Optional<Consumption> findByUsernameAndId(Long number, Integer consumptionId);


	
}
