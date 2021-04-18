package com.beer.catalogue.beercatalogue.config;

import java.util.Arrays;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.beer.catalogue.beercatalogue.domain.security.CustomUserPrincipal;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

	@Bean
    @Primary
    public UserDetailsService userDetailsService() {
		CustomUserPrincipal admin = new CustomUserPrincipal();
		admin.setPassword(new BCryptPasswordEncoder().encode("1234"));
		admin.setRole("ROLE_ADMIN");
		admin.setUsername("admin");

		CustomUserPrincipal manufacturerDamm = new CustomUserPrincipal();
		manufacturerDamm.setPassword(new BCryptPasswordEncoder().encode("1234"));
		manufacturerDamm.setRole("ROLE_MANUFACTURER");
		manufacturerDamm.setUsername("dammalias");

		CustomUserPrincipal manufactuerHeineken = new CustomUserPrincipal();
		manufactuerHeineken.setPassword(new BCryptPasswordEncoder().encode("1234"));
		manufactuerHeineken.setRole("ROLE_MANUFACTURER");
		manufactuerHeineken.setUsername("henikenalias");

        return new InMemoryUserDetailsManager(Arrays.asList(
        		admin, manufacturerDamm, manufactuerHeineken
        ));
    }
}
