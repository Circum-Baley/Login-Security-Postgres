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
	private DeviceRepository deviceRepository;
	@Autowired
	private UserRepository userRepository;

	public List<Profile> getProfile() {
		return profileRepository.findAll();
	}

	public Profile getProfileById(Integer profileId) {
		return profileRepository.findById(profileId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("profile %d not found", profileId)));
	}
	public Profile createProfileS(Integer userId, Profile profile) {
		Optional<User> result = userRepository.findById(userId);
		if (result.isPresent()) {
			profile.setUser(result.get());
			return profileRepository.save(profile);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d dosen't exists", userId));
		}
	}

	public Profile getByUserIdAndProfileId(Integer userId, Integer profileId) {
		return profileRepository.findByUserIdAndProfileId(userId, profileId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("Profile %d With User  %d Not foundeichon", profileId, userId)));
	}

	public void deleteUserRole(Integer userId) {
		Optional<User> resultat = userRepository.findById(userId);
		if (resultat.isPresent()) {
			profileRepository.findById(userId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "profile {}User not found!"));
			profileRepository.deleteById(userId);
		} else {

			throw new ResponseStatusException(HttpStatus.OK, "User Role successfully Removed!");

		}

	}
}
