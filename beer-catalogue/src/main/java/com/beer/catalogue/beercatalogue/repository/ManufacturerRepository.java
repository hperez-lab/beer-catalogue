package com.beer.catalogue.beercatalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beer.catalogue.beercatalogue.domain.jpa.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

}
