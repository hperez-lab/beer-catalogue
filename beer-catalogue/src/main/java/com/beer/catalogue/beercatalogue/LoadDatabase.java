package com.beer.catalogue.beercatalogue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.beer.catalogue.beercatalogue.domain.jpa.Beer;
import com.beer.catalogue.beercatalogue.domain.jpa.Manufacturer;
import com.beer.catalogue.beercatalogue.domain.jpa.User;
import com.beer.catalogue.beercatalogue.enumeration.BeerType;
import com.beer.catalogue.beercatalogue.repository.BeerRepository;
import com.beer.catalogue.beercatalogue.repository.ManufacturerRepository;
import com.beer.catalogue.beercatalogue.repository.UserRepository;

@Configuration
public class LoadDatabase {
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(ManufacturerRepository manufacturerRepository, BeerRepository beerRepository, UserRepository userRepository) {

		User admin = User.builder().username("admin").password(new BCryptPasswordEncoder().encode("1234")).role("ROLE_ADMIN").build();
		User user1 = User.builder().username("dammalias").password(new BCryptPasswordEncoder().encode("1234")).role("ROLE_MANUFACTURER").build();
		User user2 = User.builder().username("heinekenalias").password(new BCryptPasswordEncoder().encode("1234")).role("ROLE_MANUFACTURER").build();
		User user3 = User.builder().username("mahoualias").password(new BCryptPasswordEncoder().encode("1234")).role("ROLE_MANUFACTURER").build();
		
		Manufacturer man1 = Manufacturer.builder().name("Damm").nationality("Spain").user(user1).build();
		Manufacturer man2 = Manufacturer.builder().name("Heineken").nationality("Netherlands").user(user2).build();
		Manufacturer man3 = Manufacturer.builder().name("Mahou").nationality("Mahou 5 estrellas").user(user3).build();

		return args -> {
			log.info("Preloading " + userRepository.save(admin));
			log.info("Preloading " + manufacturerRepository.save(man1));
			log.info("Preloading " + manufacturerRepository.save(man2));
			log.info("Preloading " + manufacturerRepository.save(man3));
			log.info("Preloading " + beerRepository.save(
					Beer.builder()
					.name("Estrella Damm")
					.description("Desc Estrella Damm")
					.graduation(4.5)
					.type(BeerType.LAGER)
					.manufacturer(man1)
					.build()));
			log.info("Preloading " + beerRepository.save(
					Beer.builder()
					.name("Voll Damm")
					.description("Desc Voll Damm")
					.graduation(7.5)
					.type(BeerType.BLOND_ALE)
					.manufacturer(man1)
					.build()));
			log.info("Preloading " + beerRepository.save(
					Beer.builder()
					.name("Heineken").description("Desc Heineken")
					.graduation(4.5)
					.type(BeerType.ALE)
					.manufacturer(man2)
					.build()));
			log.info("Preloading " + beerRepository.save(
					Beer.builder()
					.name("Inedit")
					.description("Desc Inedit")
					.graduation(7.0)
					.type(BeerType.ALE)
					.manufacturer(man1).build()));
		};
	}

}
