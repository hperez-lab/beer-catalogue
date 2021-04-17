package com.beer.catalogue.beercatalogue.domain.data;

import com.beer.catalogue.beercatalogue.enumeration.BeerType;

import lombok.Data;

@Data
public class BeerData {

	private Long id;
	private String name;
	private Double graduation;
	private BeerType type;
	private String descritpion;
	private Long manufacturerId;
}
