package com.brewery.wholesale.api.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brewery.wholesale.api.rest.response.ResponseHandler;
import com.brewery.wholesale.dto.OrderDto;
import com.brewery.wholesale.dto.OrderedBeerDto;
import com.brewery.wholesale.models.Beer;
import com.brewery.wholesale.models.Wholesaler;
import com.brewery.wholesale.models.Wholesalerstock;
import com.brewery.wholesale.models.WholesalerstockId;
import com.brewery.wholesale.services.BeerService;
import com.brewery.wholesale.services.ClientOrderService;
import com.brewery.wholesale.services.WholesalerService;
import com.brewery.wholesale.services.WholesalerStockService;
import com.brewery.wholesale.utils.Constants;

@RestController
@RequestMapping("/api/client")
public class ClientOrderRestController {

	@Autowired
	private ClientOrderService clientOrderService;

	@Autowired
	private BeerService beerService;

	@Autowired
	private WholesalerStockService wholesalerStockService;

	@Autowired
	private WholesalerService wholesalerService;

	@PostMapping("/order")
	public ResponseEntity<Object> getOrderBeers(@RequestBody OrderDto orderDto) {
		Optional<Wholesaler> wholesaler = wholesalerService.findById(orderDto.getWholesalerId());

		if (!wholesaler.isPresent()) {
			return ResponseHandler.generateResponse(Constants.ERR_WHOLESALER_MUST_EXIST, HttpStatus.BAD_REQUEST, null);
		} else {
			for (OrderedBeerDto orderedBeerDto : orderDto.getOrderedBeers()) {
				if (orderedBeerDto.getQuantity() == 0) {
					return ResponseHandler.generateResponse(Constants.ERR_EMPTY_ORDER, HttpStatus.BAD_REQUEST, null);
				} else {
					Optional<Beer> beer = this.beerService.findById(orderedBeerDto.getBeerId());
					if (!beer.isPresent()) {
						return ResponseHandler.generateResponse(Constants.ERR_BEER_MUST_EXIST, HttpStatus.BAD_REQUEST,
								null);
					} else {
						Optional<Wholesalerstock> wholesalerstock = wholesalerStockService.findById(
								new WholesalerstockId(orderDto.getWholesalerId(), orderedBeerDto.getBeerId()));

						if (wholesalerstock.isPresent()
								&& wholesalerstock.get().getQuantity() < orderedBeerDto.getQuantity()) {
							return ResponseHandler.generateResponse(
									Constants.ERR_ORDERED_BEERS_NUMBER_GREATER_THAN_WHOLESALERS_STOCK,
									HttpStatus.BAD_REQUEST, null);
						}
					}
				}
			}
		}

		if (!clientOrderService.checkDuplicate(orderDto.getOrderedBeers())) {
			return ResponseHandler.generateResponse(Constants.ERR_DUPLICATE_ORDER, HttpStatus.BAD_REQUEST, null);
		} else {
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_RETREIVED, HttpStatus.OK,
					clientOrderService.getTotalPrice(orderDto));
		}
	}

}