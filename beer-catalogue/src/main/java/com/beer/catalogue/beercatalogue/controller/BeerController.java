package com.beer.catalogue.beercatalogue.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beer.catalogue.beercatalogue.domain.rest.response.BeerResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/beers")
public class BeerController {

	@ApiOperation(value = "Get all beers.")
	@GetMapping
	public ResponseEntity<List<BeerResponse>> getBeers() {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get beers details.")
	@GetMapping(value = "{id}")
	public ResponseEntity<BeerResponse> getBeer(@PathVariable final Long id) {
		return new ResponseEntity<>(new BeerResponse(), HttpStatus.OK);
	}
}
