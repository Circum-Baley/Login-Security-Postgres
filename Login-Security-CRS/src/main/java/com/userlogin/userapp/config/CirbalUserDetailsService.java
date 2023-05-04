package com.userlogin.userapp.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.UserInRole;
import com.userlogin.userapp.repositories.UserInRoleRepository;
import com.userlogin.userapp.repositories.UserRepository;

@Service
public class CirbalUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> result = userRepository.findByUsername(username);
		if (result.isPresent()) {
			User user = result.get();
			List<UserInRole> listUserInRole = userInRoleRepository.findByUser(user);
			String[] roles = listUserInRole.stream().map(r -> r.getRole().getName()).toArray(String[]::new);
			return org.springframework.security.core.userdetails.User.withUsername(username)
					.password(passwordEncoder.encode(user.getPassword())).roles(roles).build();
		} else {
			throw new UsernameNotFoundException("El usario " + username + " No Existe");
		}
	}

}
