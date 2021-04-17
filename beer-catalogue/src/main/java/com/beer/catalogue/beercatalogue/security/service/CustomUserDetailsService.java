package com.beer.catalogue.beercatalogue.security.service;

import java.util.Objects;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beer.catalogue.beercatalogue.domain.jpa.User;
import com.beer.catalogue.beercatalogue.domain.security.CustomUserPrincipal;
import com.beer.catalogue.beercatalogue.repository.UserRepository;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		CustomUserPrincipal principal = null;
	    if (user != null) {
	      principal = new CustomUserPrincipal();
	      if (Objects.nonNull(user.getManufacturer())) {
	    	  principal.setManufacturerId(user.getManufacturer().getId());
	      }
	      principal.setPassword(user.getPassword());
	      principal.setRole(user.getRole());
	      principal.setUsername(user.getUsername());
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }

	    return principal;
	}


}
