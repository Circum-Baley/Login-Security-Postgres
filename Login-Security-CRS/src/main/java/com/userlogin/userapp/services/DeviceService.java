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
import com.userlogin.userapp.repositories.DeviceRepository;
import com.userlogin.userapp.repositories.ProfileRepository;

@Service
public class DeviceService {

	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private DeviceRepository deviceRepository;

	/**
	 * 
	 * @param device
	 * @return
	 */
	public Device createDevice(Device device) {
		return deviceRepository.save(device);
	}

	public Device createDeviceProfile(Integer profileId, Device device) {
		Optional<Profile> resultProfileId = profileRepository.findById(profileId);
		if (resultProfileId.isPresent()) {
			device.setProfile(resultProfileId.get());
			return deviceRepository.save(device);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Profile ID ; {} Dosen't existes ", profileId));
		}
	}

	/**
	 * Devuelve todos los dispositivos (Devices) registrados en el sistema.
	 * 
	 * @return La lista de dispositivos.
	 */
	public List<Device> getDevices() {
		return (List<Device>) deviceRepository.findAll();
	}

	@Transactional
	public List<Device> getDeviceProfileUser() {
		return (List<Device>)deviceRepository.findAllDevicesWithProfilesAndUsers();
	}

	/**
	 * Obtiene el dispositivo (Device) a través del ID (deviceId) proporcionado como
	 * parámetro de entrada.
	 * 
	 * @param deviceId El ID del dispositivo que se desea obtener.
	 * @return La información del dispositivo (Device) correspondiente al ID
	 *         especificado.
	 * @throws ResponseStatusException Si no se encuentra el dispositivo con el ID
	 *                                 especificado.
	 */
	public Device getDeviceById(Integer deviceId) {
		return deviceRepository.findById(deviceId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				String.format("Device with ID %d not found", deviceId)));
	}

	/**
	 * Actualiza un dispositivo (Device) existente.
	 * 
	 * @param deviceId El ID del dispositivo a actualizar.
	 * @param device   El objeto Device con los datos actualizados.
	 * @return El dispositivo actualizado.
	 */
	public Device updateDevice(Integer deviceId, Device device) {
		Optional<Device> result = deviceRepository.findById(deviceId);
		if (result.isPresent()) {
			return deviceRepository.save(device);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Device ID : %d Dosen't Exists", deviceId));
		}
	}

	public void deleteDeviceById(Integer deviceId) {
		Optional<Device> devideId = deviceRepository.findById(deviceId);
		if (devideId.isPresent()) {
			deviceRepository.delete(devideId.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Device ID : %d Dosen't exists", deviceId));
		}
	}

	/**
	 * 
	 * @param deviceId
	 * @param profileId
	 * @return
	 */
	public Device unlinkDeviceFromProfile(Integer deviceId, Integer profileId) {
		Optional<Profile> resultProfile = profileRepository.findById(profileId);
		Optional<Device> resultDevice = deviceRepository.findById(deviceId);
		if (resultDevice.isPresent() && resultProfile.isPresent()) {
			Profile profile = resultProfile.get();
			Device device = resultDevice.get();
			if (profile.getId().equals(device.getProfile().getId())) {
				device.setProfile(null);
				return deviceRepository.save(device);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%d %d ", deviceId, profileId));
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("%d %d", deviceId, profileId));
		}
	}
}

//public Device updateDevice(Integer deviceId, Device device) {
//Optional<Device> result = deviceRepository.findById(deviceId);
//if (result.isPresent()) {
//	return deviceRepository.save(device);
//} else {
//	throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//			String.format("Device ID : %d Dosen't Exists", deviceId));
//}
//}	

//}
//@Transactional
//public void unlinkDeviceByProfile(Integer profileId) {
//	Optional<Profile> resultadoPro = profileRepository.findById(profileId);
//	if (resultadoPro.isPresent()) {
//		Profile profile = resultadoPro.get();
//		for (Device device : profile.getDevices()) {
//			device.setProfile(null);
//		}
//		deviceRepository.deleteDeviceByProfileId(profile.getId());
//	} else {
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//				String.format("Profile ID : %d Dosen't exists", profileId));
//
//	}
//}
//--------------------
//public void deleteProfileAndAddress(Integer addressId) {
//Optional<Address> resultat = addressRepository.findById(addressId);
//if(resultat.isPresent()) {
//	addressRepository.findById(addressId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Address %d Profile Not Found!"));
//	addressRepository.deleteById(addressId);
//}else {
//	throw new ResponseStatusException(HttpStatus.OK,"Address Successfully Removed !");
//}
//}
//
////	@CacheEvict("users")
//	public void deleteUserByUsername(String username) {
//		User user = getUserByUsername(username);
//		userRepository.delete(user);
//	}
//
////	public User getUserByName(String username) {
////		return users.stream().filter(u -> u.getUsername().equals(username)).findAny()
////				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
////						String.format("User %s not found",username)));
////	}
//
//}
