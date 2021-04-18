package com.beer.catalogue.beercatalogue.repository;

import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.beer.catalogue.beercatalogue.domain.data.BeerFilterData;
import com.beer.catalogue.beercatalogue.domain.jpa.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> {

	public Page<Beer> findAll(Specification<Beer> user,Pageable page);

	default Page<Beer> findAll(BeerFilterData filter, Pageable pageable) {
		return findAll(search(filter), pageable);
	}

	static Specification<Beer> search(BeerFilterData filter) {
	    return (root, cq, cb) -> {

	      Predicate predicate = cb.isTrue(cb.literal(true));
	      if(Objects.nonNull(filter.getName()) && !filter.getName().isBlank()) {
	        Predicate _predicate = cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%");
	        predicate = cb.and(predicate, _predicate);
	      }

	      if(Objects.nonNull(filter.getDescription()) && !filter.getDescription().isBlank()) {
	        Predicate _predicate = cb.like(cb.lower(root.get("description")), "%" + filter.getDescription().toLowerCase() + "%");
	        predicate = cb.and(predicate, _predicate);
	      }

	      if(Objects.nonNull(filter.getGraduation())) {
	        Predicate _predicate = cb.equal(root.get("graduation"), filter.getGraduation());
	        predicate = cb.and(predicate, _predicate);
	      }

	      if(Objects.nonNull(filter.getManufacturerId())) {
	        Predicate _predicate = cb.equal(root.get("manufacturer").get("id"), filter.getManufacturerId());
	        predicate = cb.and(predicate, _predicate);
	      }

	      if(Objects.nonNull(filter.getType())) {
		        Predicate _predicate = cb.equal(root.get("type"), filter.getType());
		        predicate = cb.and(predicate, _predicate);
		      }

	      return predicate;
	    };
	  }
}
