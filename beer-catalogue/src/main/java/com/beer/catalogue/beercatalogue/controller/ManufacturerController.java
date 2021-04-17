package com.beer.catalogue.beercatalogue.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.beer.catalogue.beercatalogue.controller.domain.mappers.BeerMapper;
import com.beer.catalogue.beercatalogue.controller.domain.mappers.ManufacturerMapper;
import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.data.ManufacturerData;
import com.beer.catalogue.beercatalogue.domain.rest.request.BeerRequest;
import com.beer.catalogue.beercatalogue.domain.rest.request.ManufacturerRequest;
import com.beer.catalogue.beercatalogue.domain.rest.response.BeerResponse;
import com.beer.catalogue.beercatalogue.domain.rest.response.ManufacturerResponse;
import com.beer.catalogue.beercatalogue.service.ManufacturerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/manufacturers")
public class ManufacturerController {

	private final ManufacturerService manufacturerService;

	public ManufacturerController(ManufacturerService manufacturerService) {
		this.manufacturerService = manufacturerService;
	}

	@ApiOperation(value = "Get all manufacturers.")
	@GetMapping()
	public ResponseEntity<List<ManufacturerResponse>> getManufacturers() {
		List<ManufacturerResponse> manufacturers = manufacturerService.getManufacturers().stream()
				.map(ManufacturerMapper::manufacturerDataToManufacturerResponse).collect(Collectors.toList());
		return new ResponseEntity<>(manufacturers, HttpStatus.OK);
	}

	@ApiOperation(value = "Get manufacturer details.")
	@GetMapping(value = "{id}")
	public ResponseEntity<ManufacturerResponse> getManufacturer(@PathVariable final Long id) {
		ManufacturerResponse manufacturer = ManufacturerMapper
				.manufacturerDataToManufacturerResponse(manufacturerService.getManufacturer(id));
		return new ResponseEntity<>(manufacturer, HttpStatus.OK);
	}

	@ApiOperation(value = "Create new manufacturer.")
	@PostMapping
	public ResponseEntity<ManufacturerResponse> addManufacturer(@RequestBody final ManufacturerRequest manufacturer) {
		ManufacturerData manufacturerData = manufacturerService.addManufacturer(ManufacturerMapper
				.manufacturerRequestToManufacturerData(manufacturer));
		ManufacturerResponse manufacturerResponse = ManufacturerMapper
				.manufacturerDataToManufacturerResponse(manufacturerData);
		return new ResponseEntity<>(manufacturerResponse, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update manufacturer.")
	@PutMapping("/{id}")
	public ResponseEntity<ManufacturerResponse> updateManufacturer(@RequestBody final ManufacturerRequest manufacturer,
			final @PathVariable Long id) {
		ManufacturerData manufacturerData = manufacturerService.editManufacturer(id, ManufacturerMapper
				.manufacturerRequestToManufacturerData(manufacturer));
		ManufacturerResponse manufacturerResponse = ManufacturerMapper
				.manufacturerDataToManufacturerResponse(manufacturerData);
		return new ResponseEntity<>(manufacturerResponse, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete manufacturer.")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Object> deleteManufacturer(@PathVariable final Long id) {
		manufacturerService.deleteManufacturer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Create new beer.")
	@PostMapping(value = "{manufacturerId}/beers")
	public ResponseEntity<BeerResponse> addBeer(@RequestBody final BeerRequest beer,
			@PathVariable final Long manufacturerId) {
		BeerData beerData = manufacturerService.addBeer(BeerMapper.beerRequestToBeerData(beer), manufacturerId);
		return new ResponseEntity<>(BeerMapper.beerDataToBeerResponse(beerData), HttpStatus.OK);
	}

	@ApiOperation(value = "Update beer.")
	@PutMapping(value = "{manufacturerId}/beers/{beerId}")
	public ResponseEntity<BeerResponse> editBeer(@RequestBody final BeerRequest beer,
			@PathVariable final Long manufacturerId, @PathVariable final Long beerId) {
		BeerData beerData = manufacturerService.editBeer(manufacturerId, beerId, BeerMapper.beerRequestToBeerData(beer));
		return new ResponseEntity<>(BeerMapper.beerDataToBeerResponse(beerData), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete beer.")
	@DeleteMapping(value = "{manufacturerId}/beers/{beerId}")
	public ResponseEntity<Object> deleteBeer(@PathVariable final Long manufacturerId, @PathVariable final Long beerId) {
		manufacturerService.deleteBeer(manufacturerId, beerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
