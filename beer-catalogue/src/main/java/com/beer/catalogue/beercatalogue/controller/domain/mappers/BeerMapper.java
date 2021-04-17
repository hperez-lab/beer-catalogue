package com.beer.catalogue.beercatalogue.controller.domain.mappers;

import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.jpa.Beer;
import com.beer.catalogue.beercatalogue.domain.rest.request.BeerRequest;
import com.beer.catalogue.beercatalogue.domain.rest.response.BeerResponse;

public class BeerMapper {

	public static BeerData beerToBeerData(Beer beer) {
		BeerData response = new BeerData();
		response.setDescritpion(beer.getDescritpion());
		response.setGraduation(beer.getGraduation());
		response.setId(beer.getId());
		response.setName(beer.getName());
		response.setType(beer.getType());
		response.setManufacturerId(beer.getManufacturer().getId());
		return response;
	}

	public static BeerResponse beerDataToBeerResponse(BeerData beer) {
		BeerResponse response = new BeerResponse();
		response.setDescritpion(beer.getDescritpion());
		response.setGraduation(beer.getGraduation());
		response.setId(beer.getId());
		response.setName(beer.getName());
		response.setType(beer.getType());
		response.setManufacturerId(beer.getManufacturerId());
		return response;
	}

	public static Beer beerDataToBeer(BeerData beer) {
		Beer response = new Beer();
		response.setDescritpion(beer.getDescritpion());
		response.setGraduation(beer.getGraduation());
		response.setId(beer.getId());
		response.setName(beer.getName());
		response.setType(beer.getType());
		return response;
	}
	
	public static BeerData beerRequestToBeerData(BeerRequest beer) {
		BeerData response = new BeerData();
		response.setDescritpion(beer.getDescritpion());
		response.setGraduation(beer.getGraduation());
		response.setName(beer.getName());
		response.setType(beer.getType());
		return response;
	}
}
