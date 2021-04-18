package com.beer.catalogue.beercatalogue.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.data.BeerFilterData;

public interface BeerService {

	public Page<BeerData> getBeers(BeerFilterData filterData, Pageable paging);

	public BeerData getBeer(Long id);
}
