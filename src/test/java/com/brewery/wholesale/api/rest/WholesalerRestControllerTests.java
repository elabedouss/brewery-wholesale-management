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

import com.brewery.wholesale.models.Wholesaler;
import com.brewery.wholesale.services.WholesalerService;

@RunWith(SpringRunner.class)
public class WholesalerRestControllerTests {

	private MockMvc mockMvc;

	@Mock
	private WholesalerService wholesalerService;

	@InjectMocks
	private WholesalerRestController wholesalerRestController;

	Optional<Wholesaler> wholesaler = Optional.ofNullable(new Wholesaler());

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(wholesalerRestController).build();

		wholesaler.get().setId(99);
		wholesaler.get().setName("Wholesaler test");
	}

	@Test
	public void getWholesalersOk() throws Exception {
		List<Wholesaler> wholesalers = Arrays.asList(wholesaler.get());

		Mockito.when(wholesalerService.findAll()).thenReturn(wholesalers);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wholesalers"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getWholesalersNotFound() throws Exception {
		List<Wholesaler> wholesalers = new ArrayList<>();

		Mockito.when(wholesalerService.findAll()).thenReturn(wholesalers);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wholesalers"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void getWholesalerByIdOk() throws Exception {

		Mockito.when(wholesalerService.findById(99)).thenReturn(wholesaler);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wholesalers/99"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getWholesalerByIdNotFound() throws Exception {

		Mockito.when(wholesalerService.findById(99)).thenReturn(Optional.empty());
		mockMvc.perform(MockMvcRequestBuilders.get("/api/wholesalers/99"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

}
