package com.userlogin.userApp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userApp.entities.Profile;
import com.userlogin.userApp.entities.User;
import com.userlogin.userApp.repositories.ProfileRepository;
import com.userlogin.userApp.repositories.UserRepository;
@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	
	@Autowired
	private UserRepository userRepository;
	
	
	

	public Profile createProfiles(Integer userId,Profile profile) {
		Optional<User> result = userRepository.findById(userId);
		if(result.isPresent()) {
			profile.setUser(result.get());
			return profileRepository.save(profile);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %d not foundechion",userId));
		}
	}
	
	public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
		return profileRepository.findByUserIdAndProfileId(userId,profileId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, 
				String.format("Profile %d With User  %d Not foundeichon", profileId,userId)));
	}
	
	public void deleteUserRole(Integer userId) {
		Optional<User> resultat = userRepository.findById(userId);
		if(resultat.isPresent()) {
		profileRepository.findById(userId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "profile {}User not found!"));
		profileRepository.deleteById(userId);
		} else {
			
			throw new ResponseStatusException(HttpStatus.OK, "User Role successfully Removed!");
			
		}

	}
}