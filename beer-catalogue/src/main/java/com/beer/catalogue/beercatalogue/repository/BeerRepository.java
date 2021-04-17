package com.beer.catalogue.beercatalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beer.catalogue.beercatalogue.domain.jpa.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> {

}
