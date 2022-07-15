package com.brewery.wholesale.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import com.brewery.wholesale.dto.OrderDto;
import com.brewery.wholesale.dto.OrderedBeerDto;
import com.brewery.wholesale.models.Beer;
import com.brewery.wholesale.repositories.BeerRepository;

@RunWith(SpringRunner.class)
public class ClientOrderServiceTests {

	@Spy
	private ClientOrderService clientOrderService;

	@Mock
	private BeerRepository beerRepository;

	@InjectMocks
	private BeerService beerService;

	List<OrderedBeerDto> beers = new ArrayList<>();

	OrderedBeerDto beer1 = new OrderedBeerDto();
	OrderedBeerDto beer2 = new OrderedBeerDto();
	Beer beerEntity = new Beer();
	OrderDto orderDto = new OrderDto();

	Optional<Beer> beer = Optional.ofNullable(new Beer());

	@Before
	public void setUp() {

		beer1.setBeerId(9);
		beer1.setQuantity(11);

		beer2.setBeerId(9);
		beer2.setQuantity(10);

		beers.add(beer1);
		beers.add(beer2);

		orderDto.setOrderedBeers(beers);

		beer.get().setId(9);
		beer.get().setPrice((float) 5.5);

	}

	@Test
	public void containsDuplicateFalse() {
		beers.add(beer1);
		beers.add(beer2);

		assertFalse(clientOrderService.checkDuplicate(beers));
	}

	@Test
	public void containsDuplicateTrue() {
		beers.clear();
		
		beer1.setBeerId(99);
		beer2.setBeerId(9);
		
		beers.add(beer1);
		beers.add(beer2);

		assertTrue(clientOrderService.checkDuplicate(beers));
	}

	@Test
	public void calculateDiscountTen() {
		beerEntity.setPrice((float) 5.5);

		assertEquals(54.45, clientOrderService.calculateDiscount(11, (float) 60.5));
	}

	@Test
	public void calculateDiscountTwenty() {
		beerEntity.setPrice((float) 5.5);

		assertEquals(92.4, clientOrderService.calculateDiscount(21, (float) 115.5));
	}

	@Test
	public void calculateDiscountZero() {
		beerEntity.setPrice((float) 5.5);

		assertEquals(49.5, clientOrderService.calculateDiscount(9, (float) 49.5));
	}

}
