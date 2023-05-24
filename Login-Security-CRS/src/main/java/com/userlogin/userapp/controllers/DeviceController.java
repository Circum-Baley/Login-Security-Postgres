package com.userlogin.userapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.Device;
import com.userlogin.userapp.entities.Profile;
import com.userlogin.userapp.services.DeviceService;
import com.userlogin.userapp.services.ProfileService;

@RestController
@RequestMapping("/api-device")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private ProfileService profileService;

	@PostMapping("/createDeviceProfile/{profileId}")
	public ResponseEntity<Device> createDeviceProfile(@PathVariable("profileId") Integer profileId,
			@RequestBody Device device) {
		Device deviceCreateProfile = deviceService.createDeviceProfile(profileId, device);
		return new ResponseEntity<Device>(deviceCreateProfile, HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<Device> createDevice(@RequestBody Device device) {
		return new ResponseEntity<Device>(deviceService.createDevice(device), HttpStatus.CREATED);
	}

	@GetMapping("/{deviceId}")
	public ResponseEntity<Device> getDeviceById(@PathVariable("deviceId") Integer deviceId) {
		return new ResponseEntity<Device>(deviceService.getDeviceById(deviceId), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Device>> getDevicesList() {

		return new ResponseEntity<List<Device>>(deviceService.getDevices(), HttpStatus.OK);
	}

	@PutMapping("/{deviceId}")
	public ResponseEntity<Device> updateDevice(@PathVariable("deviceId") Integer deviceId, @RequestBody Device device) {
		return new ResponseEntity<Device>(deviceService.updateDevice(deviceId, device), HttpStatus.CREATED);
	}

	@DeleteMapping("/{deviceId}")
	public ResponseEntity<Void> deleteDevice(@PathVariable("devideId") Integer deviceId) {
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}