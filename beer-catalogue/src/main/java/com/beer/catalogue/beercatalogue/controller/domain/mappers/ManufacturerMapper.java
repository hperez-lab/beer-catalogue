package com.beer.catalogue.beercatalogue.controller.domain.mappers;

import com.beer.catalogue.beercatalogue.domain.data.ManufacturerData;
import com.beer.catalogue.beercatalogue.domain.jpa.Manufacturer;
import com.beer.catalogue.beercatalogue.domain.rest.request.ManufacturerRequest;
import com.beer.catalogue.beercatalogue.domain.rest.response.ManufacturerResponse;

public class ManufacturerMapper {

	public static ManufacturerData manufacturerToManufacturerData(Manufacturer manufacturer) {
		ManufacturerData response = new ManufacturerData();
		response.setId(manufacturer.getId());
		response.setName(manufacturer.getName());
		response.setNationality(manufacturer.getNationality());
		return response;
	}
	
	public static ManufacturerData manufacturerRequestToManufacturerData(ManufacturerRequest manufacturer) {
		ManufacturerData response = new ManufacturerData();
		response.setName(manufacturer.getName());
		response.setNationality(manufacturer.getNationality());
		return response;
	}
	
	public static Manufacturer manufacturerDataToManufacturer(ManufacturerData manufacturer) {
		Manufacturer response = new Manufacturer();
		response.setName(manufacturer.getName());
		response.setNationality(manufacturer.getNationality());
		return response;
	}
	
	public static ManufacturerResponse manufacturerDataToManufacturerResponse(ManufacturerData manufacturer) {
		ManufacturerResponse response = new ManufacturerResponse();
		response.setId(manufacturer.getId());
		response.setName(manufacturer.getName());
		response.setNationality(manufacturer.getNationality());
		return response;
	}
}
