package com.userlogin.userapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Device;
import com.userlogin.userapp.entities.Profile;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {

	
	void deleteDeviceByProfileId(Integer profileId);
	
}
