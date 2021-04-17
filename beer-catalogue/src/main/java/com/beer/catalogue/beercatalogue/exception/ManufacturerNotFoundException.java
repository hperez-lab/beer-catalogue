package com.beer.catalogue.beercatalogue.exception;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class ManufacturerNotFoundException extends RuntimeException {

	public ManufacturerNotFoundException(final Long id) {
		super(MessageFormat.format("Could not find manufacturer: {0}", id));
	}
}
