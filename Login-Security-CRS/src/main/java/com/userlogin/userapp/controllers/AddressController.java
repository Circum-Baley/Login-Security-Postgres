package com.userlogin.userapp.controllers;

import java.util.List;

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

import com.userlogin.userapp.entities.Address;
import com.userlogin.userapp.services.AddressService;

@RestController
@RequestMapping("/api-address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/{userId}/profiles/{profileId}/addresses")
	public ResponseEntity<List<Address>> findAddressesByProfileAndUserId(@PathVariable("userId") Integer userId,
			@PathVariable("profileId") Integer profileId) {
		return new ResponseEntity<List<Address>>(addressService.findAddressesByProfileAndUserId(userId, profileId),
				HttpStatus.OK);
	}

	@PostMapping("/{userId}/profiles/{profileId}/addresses")
	public ResponseEntity<Address> createAddresses(@PathVariable("userId") Integer userId,
			@PathVariable("profileId") Integer profileId, @RequestBody Address address) {
		return new ResponseEntity<Address>(addressService.createAddress(userId, profileId, address),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<Address> deleteAddress(@PathVariable("addressId") Integer addressId) {
		addressService.delete(addressId);
		return new ResponseEntity<Address>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Address>> getAddressList() {
		return new ResponseEntity<List<Address>>(addressService.getAddressList(), HttpStatus.OK);
	}

}

/**
 * @GetMapping public ResponseEntity<List<Role>> getRoles(){ return new
 *             ResponseEntity<List<Role>>(roleService.getRole(),HttpStatus.OK);
 *             }
 * 
 *             @PostMapping("/roles") public ResponseEntity<Role>
 *             createRoles(@RequestBody Role role){ return new
 *             ResponseEntity<Role>(roleService.createRole(role),HttpStatus.CREATED);
 *             }
 * 
 *             @PutMapping("/{roleId}") public ResponseEntity<Role>
 *             updateRole(@PathVariable("roleId") Integer roleId,@RequestBody
 *             Role role){ return new
 *             ResponseEntity<Role>(roleService.updateRole(roleId,role),HttpStatus.CREATED);
 *             }
 * 
 *             @DeleteMapping("/{addressId}") public ResponseEntity<Void>
 *             deleteAddress(@PathVariable("addreessId")Integer addressId){
 *             addressService.deleteAddress(addressId); return new
 *             ResponseEntity<Void>(HttpStatus.NO_CONTENT); }
 */