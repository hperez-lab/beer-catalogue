package com.beer.catalogue.beercatalogue.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beer.catalogue.beercatalogue.controller.domain.mappers.BeerMapper;
import com.beer.catalogue.beercatalogue.domain.data.BeerFilterData;
import com.beer.catalogue.beercatalogue.domain.rest.request.BeerFilterRequest;
import com.beer.catalogue.beercatalogue.domain.rest.response.BeerResponse;
import com.beer.catalogue.beercatalogue.service.BeerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/beers")
public class BeerController {

	private final BeerService beerService;

	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@ApiOperation(value = "Get all beers.")
	@GetMapping
	public ResponseEntity<Page<BeerResponse>> getBeers(BeerFilterRequest filter, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "id") String sortBy) {
		Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
		BeerFilterData filterData = BeerMapper.beerFilterRequestToBeerFilterData(filter);
		List<BeerResponse> beers = beerService.getBeers(filterData, paging).stream().map(BeerMapper::beerDataToBeerResponse)
				.collect(Collectors.toList());
		return new ResponseEntity<>(new PageImpl<>(beers), HttpStatus.OK);
	}

	@ApiOperation(value = "Get beers details.")
	@GetMapping(value = "{id}")
	public ResponseEntity<BeerResponse> getBeer(@PathVariable final Long id) {
		BeerResponse beer = BeerMapper.beerDataToBeerResponse(beerService.getBeer(id));
		return new ResponseEntity<>(beer, HttpStatus.OK);
	}
}
