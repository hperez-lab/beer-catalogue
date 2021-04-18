package com.beer.catalogue.beercatalogue.domain.mappers;

import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.data.BeerFilterData;
import com.beer.catalogue.beercatalogue.domain.jpa.Beer;
import com.beer.catalogue.beercatalogue.domain.rest.request.BeerFilterRequest;
import com.beer.catalogue.beercatalogue.domain.rest.request.BeerRequest;
import com.beer.catalogue.beercatalogue.domain.rest.response.BeerResponse;

public class BeerMapper {

	public static BeerData beerToBeerData(Beer beer) {
		BeerData response = new BeerData();
		response.setDescription(beer.getDescription());
		response.setGraduation(beer.getGraduation());
		response.setId(beer.getId());
		response.setName(beer.getName());
		response.setType(beer.getType());
		response.setManufacturerId(beer.getManufacturer().getId());
		return response;
	}

	public static BeerResponse beerDataToBeerResponse(BeerData beer) {
		BeerResponse response = new BeerResponse();
		response.setDescription(beer.getDescription());
		response.setGraduation(beer.getGraduation());
		response.setId(beer.getId());
		response.setName(beer.getName());
		response.setType(beer.getType());
		response.setManufacturerId(beer.getManufacturerId());
		return response;
	}

	public static Beer beerDataToBeer(BeerData beer) {
		Beer response = new Beer();
		response.setDescription(beer.getDescription());
		response.setGraduation(beer.getGraduation());
		response.setId(beer.getId());
		response.setName(beer.getName());
		response.setType(beer.getType());
		return response;
	}
	
	public static BeerData beerRequestToBeerData(BeerRequest beer) {
		BeerData response = new BeerData();
		response.setDescription(beer.getDescription());
		response.setGraduation(beer.getGraduation());
		response.setName(beer.getName());
		response.setType(beer.getType());
		return response;
	}

	public static BeerFilterData beerFilterRequestToBeerFilterData(BeerFilterRequest beer) {
		BeerFilterData response = new BeerFilterData();
		response.setDescription(beer.getDescription());
		response.setGraduation(beer.getGraduation());
		response.setName(beer.getName());
		response.setType(beer.getType());
		response.setManufacturerId(beer.getManufacturerId());
		return response;
	}
}
