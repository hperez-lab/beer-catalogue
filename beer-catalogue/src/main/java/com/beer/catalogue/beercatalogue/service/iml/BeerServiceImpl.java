package com.beer.catalogue.beercatalogue.service.iml;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.beer.catalogue.beercatalogue.controller.domain.mappers.BeerMapper;
import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.jpa.Beer;
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
	public List<BeerData> getBeers() {
		return beerRepository.findAll().stream().map(BeerMapper::beerToBeerData).collect(Collectors.toList());
	}
	
	@Override
	public BeerData getBeer(Long id) {
		Beer beer = beerRepository.findById(id).orElseThrow(() -> new BeerNotFoundException(id));
		return BeerMapper.beerToBeerData(beer);
	}
}
