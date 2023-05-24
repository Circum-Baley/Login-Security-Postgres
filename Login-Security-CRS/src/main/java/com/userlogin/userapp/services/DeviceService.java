package com.userlogin.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

	/**
	 * 
	 * @param deviceId
	 */
	public void deleteDeviceById(Integer deviceId) {
		Optional<Device> devideId = deviceRepository.findById(deviceId);
		if (devideId.isPresent()) {
			deviceRepository.delete(devideId.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Device ID : %d Dosen't exists", deviceId));
		}
	}



//	public void deleteUserByUsername(String username) {
//	User user = getUserByUsername(username);
//	userRepository.delete(user);
//}
//	public User getUserById(Integer userId) {
//	return userRepository.findById(userId).orElseThrow(
//			() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user %d not found", userId)));
//}
}

//	@Autowired
//	private UserRepository userRepository;
//
//	// se realizo para atraer datos sin indexar, versus el metodo siguiente de abajo
//	public List<User> getUsers() {
//		return userRepository.findAll();
//	}
//
//	public Page<Object> getObjectPageSize(int page, int size) {
//		return objectRepository.findAll(PageRequest.of(page, size));
//	}

//	public List<String> getUsername(){
//		return userRepository.findUsername();
//	}
//	public Page<String> getUsernamePageSize(int page,int size) {
//		return userRepository.findUsernamePageSize(PageRequest.of(page,size));
//	}
//

//
//	public User getUserByUsername(String username) {
//		return userRepository.findByUsername(username)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//						String.format("user %d not foundeichon", username)));
//	}
//
//	public User getUserByUsernameAndId(String username, Integer userId) {
//		return userRepository.findByUsernameAndId(username, userId)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//						String.format("user %d , %d ", username, userId)));
//
//	}
//
//	public User getUserByUsernameAndPassword(String username, String password) {
//		return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
//				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("The User %s dosen't EXISTS", username)));	
//	}
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
