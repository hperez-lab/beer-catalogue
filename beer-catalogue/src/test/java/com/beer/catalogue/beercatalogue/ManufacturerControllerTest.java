package com.beer.catalogue.beercatalogue;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

public class ManufacturerControllerTest extends AbstractTest {

	@Test
	public void getManufacturers() throws Exception {
		mvc.perform(get("/manufacturers")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Heineken")));
	}

	@Test
	public void getManufacturer() throws Exception {
		mvc.perform(get("/manufacturers/2")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Damm")));
	}
}
