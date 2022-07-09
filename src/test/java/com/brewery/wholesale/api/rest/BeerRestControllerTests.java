package com.brewery.wholesale.api.rest;

import static org.junit.Assert.assertEquals;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.brewery.wholesale.models.Beer;
import com.brewery.wholesale.models.Brewery;
import com.brewery.wholesale.services.BeerService;
import com.brewery.wholesale.services.BreweryService;

@RunWith(SpringRunner.class)
public class BeerRestControllerTests {

	private MockMvc mockMvc;

	@Mock
	private BeerService beerService;

	@Mock
	private BreweryService breweryService;

	@InjectMocks
	private BeerRestController beerRestController;

	Optional<Beer> beer = Optional.ofNullable(new Beer());
	Brewery brewery = new Brewery();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(beerRestController).build();

		brewery.setId(99);
		brewery.setName("Brewery test");

		beer.get().setId(999);
		beer.get().setName("Beer test");
		beer.get().setAlcoholContent((float) 6.6);
		beer.get().setPrice((float) 5.5);
		beer.get().setBrewery(brewery);

	}

	@Test
	public void getBeersByBreweryOk() throws Exception {
		List<Beer> beers = Arrays.asList(beer.get());

		Mockito.when(beerService.findAllbyBrewery(99)).thenReturn(beers);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/99")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getBeersByBreweryNotFound() throws Exception {
		List<Beer> beers = new ArrayList<>();

		Mockito.when(beerService.findAllbyBrewery(99)).thenReturn(beers);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/99"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void saveBeerByBrewerCreated() throws Exception {
		String jsonString = "{\r\n" + "    \"name\":\"Beer test\",\r\n" + "    \"alcoholContent\":6.6,\r\n"
				+ "    \"price\":2.20,\r\n" + "    \"breweryId\":99\r\n" + "}";

		Mockito.when(breweryService.exist(99)).thenReturn(true);
		Mockito.when(beerService.save(beer.get())).thenReturn(beer.get());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/beers/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());
	}

	@Test
	public void saveBeerByBrewerNotFound() throws Exception {
		String jsonString = "{\r\n" + "    \"name\":\"Beer test\",\r\n" + "    \"alcoholContent\":6.6,\r\n"
				+ "    \"price\":2.20,\r\n" + "    \"breweryId\":99\r\n" + "}";

		Mockito.when(breweryService.exist(99)).thenReturn(false);
		Mockito.when(beerService.save(beer.get())).thenReturn(beer.get());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/beers/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();

		assertEquals(404, mvcResult.getResponse().getStatus());
	}

	@Test
	public void deleteBeerByBrewerAccepted() throws Exception {
		Mockito.when(beerService.findById(99)).thenReturn(beer);
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/beers/99").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isAccepted());
	}

	@Test
	public void deleteBeerByBrewerNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/beers/99").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
