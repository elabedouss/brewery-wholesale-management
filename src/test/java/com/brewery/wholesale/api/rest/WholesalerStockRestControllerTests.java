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
import com.brewery.wholesale.models.Wholesaler;
import com.brewery.wholesale.models.Wholesalerstock;
import com.brewery.wholesale.models.WholesalerstockId;
import com.brewery.wholesale.services.BeerService;
import com.brewery.wholesale.services.WholesalerService;
import com.brewery.wholesale.services.WholesalerStockService;

@RunWith(SpringRunner.class)
public class WholesalerStockRestControllerTests {

	private MockMvc mockMvc;

	@Mock
	private WholesalerService wholesalerService;

	@Mock
	private BeerService beerService;

	@Mock
	private WholesalerStockService wholesalerStockService;

	@InjectMocks
	private WholesalerStockRestController WholesalerStockRestController;

	Optional<Wholesalerstock> wholesalerStock = Optional.ofNullable(new Wholesalerstock());
	Optional<Beer> beer = Optional.ofNullable(new Beer());
	Optional<Brewery> brewery = Optional.ofNullable(new Brewery());
	Optional<Wholesaler> wholesaler = Optional.ofNullable(new Wholesaler());

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(WholesalerStockRestController).build();

		brewery.get().setId(99);
		brewery.get().setName("Brewery test");

		beer.get().setId(999);
		beer.get().setName("Beer test");
		beer.get().setAlcoholContent((float) 6.6);
		beer.get().setPrice((float) 5.5);
		beer.get().setBrewery(brewery.get());

		wholesaler.get().setId(9);

		wholesalerStock.get().setId(new WholesalerstockId(9, 999));
		wholesalerStock.get().setBeer(beer.get());
		wholesalerStock.get().setQuantity(50);
		wholesalerStock.get().setWholesaler(wholesaler.get());
	}

	@Test
	public void getWholesalerStocksOk() throws Exception {
		List<Wholesalerstock> WholesalerStocks = Arrays.asList(wholesalerStock.get());

		Mockito.when(wholesalerStockService.findAll()).thenReturn(WholesalerStocks);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wholesalerstock"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getWholesalerStocksNotFound() throws Exception {
		List<Wholesalerstock> WholesalerStocks = new ArrayList<>();

		Mockito.when(wholesalerStockService.findAll()).thenReturn(WholesalerStocks);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wholesalerstock"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void saveWholesalerStockCreated() throws Exception {
		String jsonString = "{\r\n" + "    \"wholesalerId\": 9,\r\n" + "    \"beerId\": 999,\r\n"
				+ "    \"quantity\": 50\r\n" + "}";

		Mockito.when(wholesalerStockService.save(wholesalerStock.get())).thenReturn(wholesalerStock.get());
		Mockito.when(wholesalerService.findById(9)).thenReturn(wholesaler);
		Mockito.when(beerService.findById(999)).thenReturn(beer);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/wholesalerstock/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());

	}

	@Test
	public void saveWholesalerStockWholeSalerNotFound() throws Exception {
		String jsonString = "{\r\n" + "    \"wholesalerId\": 9,\r\n" + "    \"beerId\": 999,\r\n"
				+ "    \"quantity\": 50\r\n" + "}";

		Mockito.when(wholesalerStockService.save(wholesalerStock.get())).thenReturn(wholesalerStock.get());
		Mockito.when(beerService.findById(999)).thenReturn(beer);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/wholesalerstock/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();

		assertEquals(404, mvcResult.getResponse().getStatus());

	}

	@Test
	public void saveWholesalerStockBeerNotFound() throws Exception {
		String jsonString = "{\r\n" + "    \"wholesalerId\": 9,\r\n" + "    \"beerId\": 999,\r\n"
				+ "    \"quantity\": 50\r\n" + "}";

		Mockito.when(wholesalerStockService.save(wholesalerStock.get())).thenReturn(wholesalerStock.get());
		Mockito.when(wholesalerService.findById(9)).thenReturn(wholesaler);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/wholesalerstock/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();

		assertEquals(404, mvcResult.getResponse().getStatus());

	}

	@Test
	public void updateWholesalerStockCreated() throws Exception {
		String jsonString = "{\r\n" + "    \"wholesalerId\": 9,\r\n" + "    \"beerId\": 999,\r\n"
				+ "    \"quantity\": 500\r\n" + "}";

		Mockito.when(wholesalerStockService.save(wholesalerStock.get())).thenReturn(wholesalerStock.get());
		Mockito.when(wholesalerStockService.findById(wholesalerStock.get().getId())).thenReturn(wholesalerStock);

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/wholesalerstock/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();

		assertEquals(201, mvcResult.getResponse().getStatus());

	}
	
	@Test
	public void updateWholesalerStockNotFound() throws Exception {
		String jsonString = "{\r\n" + "    \"wholesalerId\": 9,\r\n" + "    \"beerId\": 999,\r\n"
				+ "    \"quantity\": 500\r\n" + "}";

		Mockito.when(wholesalerStockService.save(wholesalerStock.get())).thenReturn(wholesalerStock.get());

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/wholesalerstock/save")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString)).andReturn();

		assertEquals(404, mvcResult.getResponse().getStatus());

	}

}
