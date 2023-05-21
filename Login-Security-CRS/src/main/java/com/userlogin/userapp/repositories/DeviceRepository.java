package com.userlogin.userapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userlogin.userapp.entities.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {

}
