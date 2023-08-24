package com.userlogin.userapp.controllers;

import java.util.List;
import java.util.Optional;

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
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.services.DeviceService;
import com.userlogin.userapp.services.ProfileService;

@RestController
@RequestMapping("/api-profile")
public class ProfileController {

	@Autowired
	private ProfileService profileService;
	@Autowired
	private DeviceService deviceService;

	@GetMapping("/{profileId}")
	public ResponseEntity<Profile> getProfileById(
//			@ApiParam(name = "userId", value = "Numero Identificador Del Usuario") <- hay un pequenio error en la consola
			@PathVariable Integer profileId) {
		return new ResponseEntity<Profile>(profileService.getProfileById(profileId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Profile> createProfileS(@PathVariable("userId") Integer userId,
			@RequestBody Profile profile) {
		return new ResponseEntity<Profile>(profileService.createProfileS(userId, profile), HttpStatus.CREATED);
	}

	/**
	 * vincula en Profile el Device que tiene asociado
	 * 
	 * @param profileId
	 * @param device
	 * @return
	 */
	@PostMapping("/createDevice/{profileId}")
	public ResponseEntity<Device> createDeviceProfile(@PathVariable("profileId") Integer profileId,
			@RequestBody Device device) {
		Device deviceCreateProfile = deviceService.createDeviceProfile(profileId, device);
		return new ResponseEntity<Device>(deviceCreateProfile, HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}/{profileId}")
	public ResponseEntity<Profile> getById(@PathVariable("userId") Integer userId,
			@PathVariable("profileId") Integer profileId) {
		return new ResponseEntity<Profile>(profileService.getByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<List<Profile>> getProfile() {
		return new ResponseEntity<List<Profile>>(profileService.getProfile(), HttpStatus.OK);
	}
	

//	@DeleteMapping("/{userId}")
//	public ResponseEntity<void> deleteProfile(@PathVariable("userId") Integer userId){
//		Optinal<Profile> result=profileService;
//		throw new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
}
