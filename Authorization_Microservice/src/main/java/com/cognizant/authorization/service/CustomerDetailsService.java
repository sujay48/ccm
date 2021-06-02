package com.cognizant.authorization.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authorization.model.MyUserDetails;
import com.cognizant.authorization.model.User;
import com.cognizant.authorization.repository.UserRepository;

/**
 * 
 * 
 * service class that implements the interface UserDetailsService that includes
 * user details method definitions
 */

@Service
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName);
		var userDetails = user.map(MyUserDetails::new);
		if(userDetails.isPresent()) {
			return userDetails.get();
		}
		else {
			throw new UsernameNotFoundException("Not found: " + userName);
		}
	}
}
