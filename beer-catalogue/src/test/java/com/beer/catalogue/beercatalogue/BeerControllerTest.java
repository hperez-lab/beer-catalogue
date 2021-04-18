package com.beer.catalogue.beercatalogue;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

public class BeerControllerTest extends AbstractTest {

	@Test
	public void getBeers() throws Exception {
		mvc.perform(get("/beers")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Estrella Damm")));
	}

	@Test
	public void getBeer() throws Exception {
		mvc.perform(get("/beers/8")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Estrella Damm")));
	}

	@Test
	public void getBeerThrowException() throws Exception {
		mvc.perform(get("/beers/99")).andExpect(status().is4xxClientError())
				.andExpect(content().string(containsString("Could not find beer")));
	}

}
