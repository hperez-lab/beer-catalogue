package com.beer.catalogue.beercatalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beer.catalogue.beercatalogue.domain.jpa.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
