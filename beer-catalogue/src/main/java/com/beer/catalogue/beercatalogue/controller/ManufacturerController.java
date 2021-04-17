package com.beer.catalogue.beercatalogue.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beer.catalogue.beercatalogue.domain.rest.request.BeerRequest;
import com.beer.catalogue.beercatalogue.domain.rest.request.ManufacturerRequest;
import com.beer.catalogue.beercatalogue.domain.rest.response.BeerResponse;
import com.beer.catalogue.beercatalogue.domain.rest.response.ManufacturerResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

	@ApiOperation(value = "Get all manufacturers.")
	@GetMapping()
	public ResponseEntity<List<ManufacturerResponse>> getManufacturers() {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get manufacturer details.")
	@GetMapping(value = "{id}")
	public ResponseEntity<ManufacturerResponse> getManufacturer(@PathVariable final Long id) {
		return new ResponseEntity<>(new ManufacturerResponse(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Create new manufacturer.")
	@PostMapping
	public ResponseEntity<ManufacturerResponse> addManufacturer(@RequestBody final ManufacturerRequest manufacturer) {
		return new ResponseEntity<>(new ManufacturerResponse(), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update manufacturer.")
	@PutMapping("/{id}")
	public ResponseEntity<ManufacturerResponse> updateManufacturer(@RequestBody final ManufacturerRequest manufacturer,
			final @PathVariable Long id) {
		return new ResponseEntity<>(new ManufacturerResponse(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete manufacturer.")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Object> deleteManufacturer(@PathVariable final Long id) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Create new beer.")
	@PostMapping(value = "{manufacturerId}/beers")
	public ResponseEntity<BeerResponse> addBeer(@RequestBody final BeerRequest beer,
			@PathVariable final Long manufacturerId) {
		return new ResponseEntity<>(new BeerResponse(), HttpStatus.OK);
	}

	@ApiOperation(value = "Update beer.")
	@PutMapping(value = "{manufacturerId}/beers/{beerId}")
	public ResponseEntity<BeerResponse> editBeer(@RequestBody final BeerRequest beer, @PathVariable final Long manufacturerId,
			@PathVariable final Long beerId) {
		return new ResponseEntity<>(new BeerResponse(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete beer.")
	@DeleteMapping(value = "{manufacturerId}/beers/{beerId}")
	public ResponseEntity<Object> deleteBeer(@PathVariable final Long manufacturerId, @PathVariable final Long beerId) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
