package com.userlogin.userapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.userapp.entities.Profile;
import com.userlogin.userapp.services.ProfileService;

@RestController
@RequestMapping("/api-user/{userId}/profiles")
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/{profileId}")
	public ResponseEntity<Profile> getById(@PathVariable("userId") Integer userId,
			@PathVariable("profileId") Integer profileId) {
		return new ResponseEntity<Profile>(profileService.getByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Profile> createProfileS(@PathVariable("userId") Integer userId,
			@RequestBody Profile profile) {
		return new ResponseEntity<Profile>(profileService.createProfileS(userId, profile), HttpStatus.CREATED);
	}

//	@DeleteMapping("/{userId}")
//	public ResponseEntity<void> deleteProfile(@PathVariable("userId") Integer userId){
//		Optinal<Profile> result=profileService.f
//		throw new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
}
