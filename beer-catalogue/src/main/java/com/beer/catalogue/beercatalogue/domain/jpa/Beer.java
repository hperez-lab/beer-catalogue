package com.beer.catalogue.beercatalogue.domain.jpa;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.beer.catalogue.beercatalogue.enumeration.BeerType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Beer")
public class Beer {

	private @Id @GeneratedValue Long id;
	private String name;
	private Double graduation;
	private BeerType type;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	private Manufacturer manufacturer;
}
