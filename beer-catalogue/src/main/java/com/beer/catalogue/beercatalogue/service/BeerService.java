package com.beer.catalogue.beercatalogue.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.beer.catalogue.beercatalogue.domain.data.BeerData;

public interface BeerService {

	public List<BeerData> getBeers(Pageable paging);

	public BeerData getBeer(Long id);
}
