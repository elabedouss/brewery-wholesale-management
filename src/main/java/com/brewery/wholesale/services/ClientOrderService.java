package com.brewery.wholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brewery.wholesale.dto.OrderDto;
import com.brewery.wholesale.dto.OrderedBeerDto;
import com.brewery.wholesale.models.Beer;
import com.brewery.wholesale.utils.Constants;

@Service
public class ClientOrderService {

	@Autowired
	private BeerService beerService;

	public boolean checkDuplicate(List<OrderedBeerDto> beers) {
		return beers.stream().map(OrderedBeerDto::getBeerId).distinct().count() == beers.size();
	}

	public float getTotalPrice(OrderDto orderDto) {
		
		float totalPrice = 0;
		int totalQuantity = 0;

		for (OrderedBeerDto orderedBeerDto : orderDto.getOrderedBeers()) {
			Optional<Beer> beerEntity = beerService.findById(orderedBeerDto.getBeerId());
			if (beerEntity.isPresent()) {
				totalPrice += beerEntity.get().getPrice() * orderedBeerDto.getQuantity();
				totalQuantity += orderedBeerDto.getQuantity();
			}
		}

		return (float) calculateDiscount(totalQuantity, totalPrice);
	}

	public double calculateDiscount(int totalQuantity, float totalPrice) {
		float appliedDiscount = 0;

		if (totalQuantity > 20) {
			appliedDiscount = Constants.DISCOUNT_ABOVE_TWENTY_BEERS;
		} else if (totalQuantity > 10) {
			appliedDiscount = Constants.DISCOUNT_ABOVE_TEN_BEERS;
		}

		totalPrice -= totalPrice * appliedDiscount;

		return Math.round(totalPrice * 100.0) / 100.0;
	}
}
