package com.userlogin.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.Device;
import com.userlogin.userapp.entities.Profile;
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.repositories.DeviceRepository;
import com.userlogin.userapp.repositories.ProfileRepository;
import com.userlogin.userapp.repositories.UserRepository;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserRepository userRepository;

	public List<Profile> getProfile() {
		return profileRepository.findAll();
	}

	public Profile getProfileById(Integer profileId) {
		return profileRepository.findById(profileId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("PROFILE %d NOT FOUND", profileId)));
	}
	public Profile createProfileS(Integer userId, Profile profile) {
		Optional<User> result = userRepository.findById(userId);
		if (result.isPresent()) {
			profile.setUser(result.get());
			return profileRepository.save(profile);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("USER %d NOT FOUND", userId));
		}
	}
   
	public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
		return profileRepository.findByUserIdAndProfileId(userId, profileId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("PROFILE %d WITH USER %d Not FOUND", profileId, userId)));
	}

	public void deleteUserRole(Integer userId) {
		Optional<User> resultat = userRepository.findById(userId);
		if (resultat.isPresent()) {
			profileRepository.findById(userId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PROFILE {} USER NOT FOUND!"));
			profileRepository.deleteById(userId);
		} else {
			throw new ResponseStatusException(HttpStatus.OK, "USER ROLE SUCCESSFULLY REMOVED!");
		}
	}
}
