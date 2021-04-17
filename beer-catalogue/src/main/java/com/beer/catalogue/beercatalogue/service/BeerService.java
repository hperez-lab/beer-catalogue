package com.beer.catalogue.beercatalogue.service;

import java.util.List;

import com.beer.catalogue.beercatalogue.domain.data.BeerData;

public interface BeerService {

	public List<BeerData> getBeers();

	public BeerData getBeer(Long id);
}
