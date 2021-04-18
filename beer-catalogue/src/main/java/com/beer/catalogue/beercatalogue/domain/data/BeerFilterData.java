package com.beer.catalogue.beercatalogue.domain.data;

import com.beer.catalogue.beercatalogue.enumeration.BeerType;

import lombok.Data;

@Data
public class BeerFilterData {

	private String name;
	private Double graduation;
	private BeerType type;
	private String description;
	private Long manufacturerId;
}
