package com.beer.catalogue.beercatalogue;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import com.beer.catalogue.beercatalogue.domain.rest.request.BeerRequest;
import com.beer.catalogue.beercatalogue.enumeration.BeerType;

public class BeerControllerTest extends AbstractTest {

	private BeerRequest beerCreatebyAdmin;

	private BeerRequest beerCreatebyManufacturer;

	private BeerRequest beerUpdate;

	@BeforeEach
    public void initAll() {

    	beerCreatebyAdmin = new BeerRequest();
    	beerCreatebyAdmin.setDescription("Heineken 0.0 description");
    	beerCreatebyAdmin.setGraduation(0.0);
    	beerCreatebyAdmin.setName("Heineken 0.0");
    	beerCreatebyAdmin.setType(BeerType.WHEAT);

    	beerCreatebyManufacturer = new BeerRequest();
    	beerCreatebyManufacturer.setDescription("Xibeca description");
    	beerCreatebyManufacturer.setGraduation(4.0);
    	beerCreatebyManufacturer.setName("Xibeca");
    	beerCreatebyManufacturer.setType(BeerType.WHEAT);

    	beerUpdate = new BeerRequest();
    	beerUpdate.setDescription("Voll Damm Desription Updated");
    	beerUpdate.setGraduation(4.0);
    	beerUpdate.setName("Voll Damm Updated");
    	beerUpdate.setType(BeerType.WHEAT);

    }

	@Test
	public void getBeers() throws Exception {
		mvc.perform(get("/beers")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Heineken")));
	}

	@Test
	public void getBeersWithFilter() throws Exception {
		mvc.perform(get("/beers?description=hei&graduation=4.5&manufacturerId=4&name=hei&type=ALE"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Heineken")));
	}

	@Test
	public void getBeer() throws Exception {
		mvc.perform(get("/beers/10")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Heineken")));
	}

	@Test
	public void getBeerThrowException() throws Exception {
		mvc.perform(get("/beers/99")).andExpect(status().is(HttpStatus.NOT_FOUND.value()))
				.andExpect(content().string(containsString("Could not find beer")));
	}

	@Test
	public void addBeerNotAuthenticated() throws Exception {

		String inputJson = mapToJson(beerCreatebyAdmin);

		mvc.perform(post("/manufacturers/4/beers").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void addBeerAuthenticatedByAdmin() throws Exception {

		String inputJson = mapToJson(beerCreatebyAdmin);

		mvc.perform(post("/manufacturers/4/beers").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Heineken 0.0")));;
	}

	@Test
	@WithUserDetails("heinekenalias")
	public void addBeerByManufacturerNotOwner() throws Exception {

		String inputJson = mapToJson(beerCreatebyManufacturer);

		mvc.perform(post("/manufacturers/2/beers").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.FORBIDDEN.value()));
	}

	@Test
	@WithUserDetails("dammalias")
	public void addBeerByManufacturerOwner() throws Exception {

		String inputJson = mapToJson(beerCreatebyManufacturer);

		mvc.perform(post("/manufacturers/2/beers").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Xibeca")));;
	}

	@Test
	public void updateBeerNotAuthorized() throws Exception {

		String inputJson = mapToJson(beerUpdate);

		mvc.perform(put("/manufacturers/2/beers/9").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void updateBeerAuthorizedByAdmin() throws Exception {

		String inputJson = mapToJson(beerUpdate);

		mvc.perform(put("/manufacturers/2/beers/9").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Voll Damm Updated")));
	}

	@Test
	@WithUserDetails("heinekenalias")
	public void updateBeerByManufacturerNotOwner() throws Exception {

		String inputJson = mapToJson(beerUpdate);

		mvc.perform(put("/manufacturers/2/beers/9").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.FORBIDDEN.value()));
	}

	@Test
	@WithUserDetails("dammalias")
	public void updateBeerByManufacturerOwner() throws Exception {

		String inputJson = mapToJson(beerUpdate);

		mvc.perform(put("/manufacturers/2/beers/9").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Voll Damm Updated")));
	}

	@Test
	@WithUserDetails("admin")
	public void updateNotExistingBeerAuthorized() throws Exception {

		String inputJson = mapToJson(beerUpdate);

		mvc.perform(put("/manufacturers/2/beers/99").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()))
				.andExpect(content().string(containsString("Could not find beer")));
	}

	@Test
	public void deleteBeerNotAuthorized() throws Exception {

		mvc.perform(delete("/manufacturers/2/beers/8")).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void deleteBeerAuthorizedByAdmin() throws Exception {

		mvc.perform(delete("/manufacturers/2/beers/8")).andExpect(status().is(HttpStatus.NO_CONTENT.value()));
	}

	@Test
	@WithUserDetails("heinekenalias")
	public void deleteBeerByManufacturerNotOwner() throws Exception {

		mvc.perform(delete("/manufacturers/2/beers/11")).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
	}

	@Test
	@WithUserDetails("dammalias")
	public void deleteBeerByManufacturerOwner() throws Exception {

		mvc.perform(delete("/manufacturers/2/beers/11")).andExpect(status().is(HttpStatus.NO_CONTENT.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void deleteNotExistingBeerAuthorized() throws Exception {

		mvc.perform(delete("/manufacturers/2/beers/99")).andExpect(status().is(HttpStatus.NOT_FOUND.value()))
				.andExpect(content().string(containsString("Could not find beer")));
	}

}
