package com.userlogin.userapp.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.userlogin.userapp.entities.Address;
import com.userlogin.userapp.entities.Profile;
import com.userlogin.userapp.repositories.AddressRepository;
import com.userlogin.userapp.repositories.ProfileRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ProfileRepository profileRepository;

	public Address createAddress(Integer userId, Integer profileId, Address address) {
		Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);
		if (result.isPresent()) {
			address.setProfile(result.get());
			return addressRepository.save(address);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Profile %d and User %d No Fueron Encontrados", profileId, userId));
		}
	}

	public List<Address> findAddressesByProfileAndUserId(Integer userId, Integer profileId) {
		return addressRepository.findByProfileId(userId, profileId);
	}

	public List<Address> getAddressList() {
		return addressRepository.findAll();
	}

	public void deleteAddress(Integer addressId) {
		Address address = addressRepository.findById(addressId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address %d Not Found :: " + addressId));
		this.addressRepository.delete(address);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
	}

	public void deleteProfileAndAddress(Integer addressId) {
		Optional<Address> resultat = addressRepository.findById(addressId);
		if (resultat.isPresent()) {
			addressRepository.findById(addressId).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address %d Profile Not Found!"));
			addressRepository.deleteById(addressId);
		} else {
			throw new ResponseStatusException(HttpStatus.OK, "Address Successfully Removed !");
		}
	}

	public void delete(Integer id) {
		try {
			addressRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Address {} %d Nao é possivel excluir entidades relacionadas.");
		}
	}

	public Address getAddressById(Integer addressId) {
		return addressRepository.findById(addressId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("Address %d not foundeichon", addressId)));
	}
}
