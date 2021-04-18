package com.beer.catalogue.beercatalogue.service.iml;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.data.BeerFilterData;
import com.beer.catalogue.beercatalogue.domain.jpa.Beer;
import com.beer.catalogue.beercatalogue.domain.mappers.BeerMapper;
import com.beer.catalogue.beercatalogue.exception.BeerNotFoundException;
import com.beer.catalogue.beercatalogue.repository.BeerRepository;
import com.beer.catalogue.beercatalogue.service.BeerService;

@Service
public class BeerServiceImpl implements BeerService {

	private final BeerRepository beerRepository;

	public BeerServiceImpl(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public Page<BeerData> getBeers(BeerFilterData filterData, Pageable paging) {
		return beerRepository.findAll(filterData, paging).map(BeerMapper::beerToBeerData);
	}

	@Override
	public BeerData getBeer(Long id) {
		Beer beer = beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
		return BeerMapper.beerToBeerData(beer);
	}
}
