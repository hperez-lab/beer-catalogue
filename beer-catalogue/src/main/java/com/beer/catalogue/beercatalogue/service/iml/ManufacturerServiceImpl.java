package com.beer.catalogue.beercatalogue.service.iml;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beer.catalogue.beercatalogue.controller.domain.mappers.BeerMapper;
import com.beer.catalogue.beercatalogue.controller.domain.mappers.ManufacturerMapper;
import com.beer.catalogue.beercatalogue.domain.data.BeerData;
import com.beer.catalogue.beercatalogue.domain.data.ManufacturerData;
import com.beer.catalogue.beercatalogue.domain.jpa.Beer;
import com.beer.catalogue.beercatalogue.domain.jpa.Manufacturer;
import com.beer.catalogue.beercatalogue.exception.BeerNotFoundException;
import com.beer.catalogue.beercatalogue.exception.ManufacturerNotFoundException;
import com.beer.catalogue.beercatalogue.repository.BeerRepository;
import com.beer.catalogue.beercatalogue.repository.ManufacturerRepository;
import com.beer.catalogue.beercatalogue.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	private final ManufacturerRepository manufacturerRepository;
	private final BeerRepository beerRepository;

	public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, BeerRepository beerRepository) {
		this.manufacturerRepository = manufacturerRepository;
		this.beerRepository = beerRepository;
	}
	
	@Override
	public List<ManufacturerData> getManufacturers(Pageable paging) {
		return manufacturerRepository.findAll(paging).stream().map(ManufacturerMapper::manufacturerToManufacturerData).collect(Collectors.toList());
	}
	
	@Override
	public ManufacturerData getManufacturer(Long id) {
		Manufacturer manufacturer = getJpaManufacturer(id);
		return ManufacturerMapper.manufacturerToManufacturerData(manufacturer);
	}
	
	@Override
	public ManufacturerData addManufacturer(ManufacturerData manufacturerData) {
		Manufacturer manufacturer = ManufacturerMapper.manufacturerDataToManufacturer(manufacturerData);
		return ManufacturerMapper.manufacturerToManufacturerData(manufacturerRepository.save(manufacturer));
	}

	@Override
	@Transactional
	public ManufacturerData editManufacturer(Long id, ManufacturerData manufacturerData) {
		Manufacturer manufacturerToEdit = getJpaManufacturer(id);
		manufacturerToEdit.setName(manufacturerData.getName());
		manufacturerToEdit.setNationality(manufacturerData.getNationality());
		return ManufacturerMapper.manufacturerToManufacturerData(manufacturerRepository.save(manufacturerToEdit));
	}
	
	@Override
	public void deleteManufacturer(Long id) {
		Manufacturer manufacturer = getJpaManufacturer(id);
		manufacturerRepository.delete(manufacturer);
	}
	
	@Override
	@Transactional
	public BeerData addBeer(BeerData beerData, Long manufacturerId) {
		Manufacturer manufacturer = getJpaManufacturer(manufacturerId);
		Beer beer = BeerMapper.beerDataToBeer(beerData);
		beer.setManufacturer(manufacturer);
		return BeerMapper.beerToBeerData(beerRepository.save(beer));
	}
	
	@Override
	@Transactional
	public BeerData editBeer(Long manufacturerId, Long beerId, BeerData beer) {
		Manufacturer manufacturer = getJpaManufacturer(manufacturerId);
		Optional<Beer> beerToEditOptional = manufacturer.getBeers()
				.stream()
				.filter(beerItem -> beerItem.getId().equals(beerId))
				.findFirst();
		if (beerToEditOptional.isPresent()) {
			Beer beerToEdit = beerToEditOptional.get();
			beerToEdit.setName(beer.getName());
			beerToEdit.setDescription(beer.getDescription());
			beerToEdit.setGraduation(beer.getGraduation());
			beerToEdit.setType(beer.getType());
			return BeerMapper.beerToBeerData(beerRepository.save(beerToEdit));
		} else {
			throw new BeerNotFoundException(beerId);
		}
	}
	
	@Override
	@Transactional
	public void deleteBeer(Long manufacturerId, Long beerId) {
		Manufacturer manufacturer = getJpaManufacturer(manufacturerId);
		Optional<Beer> beerToEditOptional = manufacturer.getBeers()
				.stream()
				.filter(beerItem -> beerItem.getId().equals(beerId))
				.findFirst();
		if (beerToEditOptional.isPresent()) {
//			beerRepository.delete(beerToEditOptional.get());
			manufacturer.getBeers().remove(beerToEditOptional.get());
		} else {
			throw new BeerNotFoundException(beerId);
		}
	}
	
	private Manufacturer getJpaManufacturer(Long id) {
		return manufacturerRepository.findById(id).orElseThrow(() -> new ManufacturerNotFoundException(id));
	}

}
