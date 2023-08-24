package com.userlogin.userapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

	void deleteDeviceByProfileId(Integer profileId);

	@Query("SELECT DISTINCT d FROM Device d " + "JOIN FETCH d.profile p " + "JOIN FETCH p.user u")
	List<Device> findAllDevicesWithProfilesAndUsers();
}
