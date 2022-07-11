package com.brewery.wholesale.api.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.brewery.wholesale.models.Brewery;
import com.brewery.wholesale.services.BreweryService;

@RunWith(SpringRunner.class)
public class BreweryRestControllerTests {

	private MockMvc mockMvc;

	@Mock
	private BreweryService breweryService;

	@InjectMocks
	private BreweryRestController breweryRestController;

	Optional<Brewery> brewery = Optional.ofNullable(new Brewery());

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(breweryRestController).build();

		brewery.get().setId(99);
		brewery.get().setName("Brewery test");
	}

	@Test
	public void getWholesalersOk() throws Exception {
		List<Brewery> breweries = Arrays.asList(brewery.get());

		Mockito.when(breweryService.findAll()).thenReturn(breweries);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/breweries")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getWholesalersNotFound() throws Exception {
		List<Brewery> breweries = new ArrayList<>();

		Mockito.when(breweryService.findAll()).thenReturn(breweries);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/breweries"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
