package com.beer.catalogue.beercatalogue.domain.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Manufacturer")
public class Manufacturer {

	private @Id @GeneratedValue Long id;
	private String name;
	private String nationality;
	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Beer> beers = new HashSet<>();
}
