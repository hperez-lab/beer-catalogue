package com.beer.catalogue.beercatalogue.domain.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder()
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "Manufacturer")
public class Manufacturer {

	private @Id @GeneratedValue Long id;
	private String name;
	private String nationality;
	@OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private Set<Beer> beers = new HashSet<>();
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
}
