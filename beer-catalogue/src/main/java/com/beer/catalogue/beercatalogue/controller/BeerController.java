package com.beer.catalogue.beercatalogue.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beer.catalogue.beercatalogue.controller.domain.mappers.BeerMapper;
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
	public ResponseEntity<List<BeerResponse>> getBeers() {
		List<BeerResponse> beers = beerService.getBeers().stream().map(BeerMapper::beerDataToBeerResponse).collect(Collectors.toList());
		return new ResponseEntity<>(beers, HttpStatus.OK);
	}

	@ApiOperation(value = "Get beers details.")
	@GetMapping(value = "{id}")
	public ResponseEntity<BeerResponse> getBeer(@PathVariable final Long id) {
		BeerResponse beer = BeerMapper.beerDataToBeerResponse(beerService.getBeer(id));
		return new ResponseEntity<>(beer, HttpStatus.OK);
	}
}
