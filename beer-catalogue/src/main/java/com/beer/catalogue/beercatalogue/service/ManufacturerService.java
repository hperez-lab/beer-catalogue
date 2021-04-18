package com.beer.catalogue.beercatalogue.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.data.ManufacturerData;

public interface ManufacturerService {

	public Page<ManufacturerData> getManufacturers(Pageable paging);

	public ManufacturerData getManufacturer(Long id);

	public ManufacturerData addManufacturer(ManufacturerData manufacturerData);

	public ManufacturerData editManufacturer(Long id, ManufacturerData manufacturer);

	public void deleteManufacturer(Long id);

	public BeerData addBeer(BeerData beer, Long manufacturerId);

	public BeerData editBeer(Long manufacturerId, Long beerId, BeerData beer);

	public void deleteBeer(Long manufacturerId, Long beerId);

}
