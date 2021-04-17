package com.beer.catalogue.beercatalogue.exception;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class BeerNotFoundException extends RuntimeException {

	public BeerNotFoundException(final Long id) {
		super(MessageFormat.format("Could not find beer: {0}", id));
	}

}
