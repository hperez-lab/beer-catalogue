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

import com.beer.catalogue.beercatalogue.domain.rest.request.ManufacturerRequest;

public class ManufacturerControllerTest extends AbstractTest {

	private ManufacturerRequest manufacturerCreate;

	private ManufacturerRequest manufacturerUpdate;

	@BeforeEach
    public void initAll() {

    	manufacturerCreate = new ManufacturerRequest();
    	manufacturerCreate.setName("Moritz");
    	manufacturerCreate.setNationality("Spain");

    	manufacturerUpdate = new ManufacturerRequest();
    	manufacturerUpdate.setName("Heineken Updated");
    	manufacturerUpdate.setNationality("Netherlands Updated");

    }

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

	@Test
	public void addManufacturerNotAuthenticated() throws Exception {

		String inputJson = mapToJson(manufacturerCreate);

		mvc.perform(post("/manufacturers").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void addManufacturerAuthenticated() throws Exception {

		String inputJson = mapToJson(manufacturerCreate);

		mvc.perform(post("/manufacturers").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.CREATED.value())).andExpect(content().string(containsString("Moritz")));
	}

	@Test
	public void deleteManufacturerNotAuthenticated() throws Exception {

		mvc.perform(delete("/manufacturers/6")).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void deleteManufacturerAuthenticated() throws Exception {

		mvc.perform(delete("/manufacturers/6")).andExpect(status().is(HttpStatus.NO_CONTENT.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void deleteNotExistingManufacturerAuthenticated() throws Exception {

		mvc.perform(delete("/manufacturers/99")).andExpect(status().is(HttpStatus.NOT_FOUND.value()))
				.andExpect(content().string(containsString("Could not find manufacturer")));
	}

	@Test
	public void updateManufacturerNotAuthorized() throws Exception {

		String inputJson = mapToJson(manufacturerUpdate);

		mvc.perform(put("/manufacturers/4").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}

	@Test
	@WithUserDetails("admin")
	public void updateManufacturerAuthorizedByAdmin() throws Exception {

		String inputJson = mapToJson(manufacturerUpdate);

		mvc.perform(put("/manufacturers/4").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Heineken Updated")));
	}

	@Test
	@WithUserDetails("dammalias")
	public void updateManufacturerAuthorizedByManufacturerNotOwner() throws Exception {

		String inputJson = mapToJson(manufacturerUpdate);

		mvc.perform(put("/manufacturers/4").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.FORBIDDEN.value()));
	}

	@Test
	@WithUserDetails("heinekenalias")
	public void updateManufacturerAuthorizedByManufacturerOwner() throws Exception {

		String inputJson = mapToJson(manufacturerUpdate);

		mvc.perform(put("/manufacturers/4").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Heineken Updated")));
	}

	@Test
	@WithUserDetails("admin")
	public void updateNotExistingManufacturerAuthorized() throws Exception {

		String inputJson = mapToJson(manufacturerUpdate);

		mvc.perform(put("/manufacturers/99").contentType(MediaType.APPLICATION_JSON).content(inputJson))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()))
				.andExpect(content().string(containsString("Could not find manufacturer")));
	}
}
