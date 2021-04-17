package com.beer.catalogue.beercatalogue.domain.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.beer.catalogue.beercatalogue.enumeration.BeerType;

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
@Table(name = "Beer")
public class Beer {

	private @Id @GeneratedValue Long id;
	private String name;
	private Double graduation;
	@Enumerated(EnumType.STRING)
	private BeerType type;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	private Manufacturer manufacturer;
}
