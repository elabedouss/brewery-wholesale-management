package com.brewery.wholesale.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brewery.wholesale.api.rest.response.ResponseHandler;
import com.brewery.wholesale.dto.WholesalerStockDto;
import com.brewery.wholesale.models.Beer;
import com.brewery.wholesale.models.Wholesaler;
import com.brewery.wholesale.models.Wholesalerstock;
import com.brewery.wholesale.models.WholesalerstockId;
import com.brewery.wholesale.services.BeerService;
import com.brewery.wholesale.services.WholesalerService;
import com.brewery.wholesale.services.WholesalerStockService;
import com.brewery.wholesale.utils.Constants;

@RestController
@RequestMapping("/api/wholesalerstock")
public class WholesalerStockRestController {

	@Autowired
	private WholesalerService wholesalerService;

	@Autowired
	private BeerService beerService;

	@Autowired
	private WholesalerStockService wholesalerStockService;

	/**
	 * retrieve wholesalers Stock
	 * 
	 * @return list of beers
	 */
	@GetMapping("")
	public ResponseEntity<Object> getWholesalerStock() {
		List<Wholesalerstock> result = wholesalerStockService.findAll();
		if (result.isEmpty()) {
			return ResponseHandler.generateResponse(Constants.EMPTY_DATA, HttpStatus.NOT_FOUND, result);
		} else {
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_RETREIVED, HttpStatus.OK, result);
		}

	}

	/**
	 * FR4- Add the sale of an existing beer to an existing wholesaler
	 * 
	 * @param wholesalerStockDto, entity to be saved
	 * @return the saved entity
	 */
	@PostMapping("/save")
	public ResponseEntity<Object> saveWholesalerStock(@RequestBody WholesalerStockDto wholesalerStockDto) {
		Optional<Wholesaler> wholesaler = wholesalerService.findById(wholesalerStockDto.getWholesalerId());
		Optional<Beer> beer = beerService.findById(wholesalerStockDto.getBeerId());
		Optional<Wholesalerstock> wholesalerStock = Optional.ofNullable(new Wholesalerstock());
		if (!wholesaler.isPresent()) {
			return ResponseHandler.generateResponse(Constants.ERR_WHOLESALER_MUST_EXIST, HttpStatus.NOT_FOUND, null);

		} else {
			if (!beer.isPresent()) {
				return ResponseHandler.generateResponse(Constants.ERR_BEER_MUST_EXIST, HttpStatus.NOT_FOUND, null);
			} else {
				wholesalerStock.get().setWholesaler(wholesaler.get());
				wholesalerStock.get().setBeer(beer.get());
				wholesalerStock.get().setQuantity(wholesalerStockDto.getQuantity());

				wholesalerStock.get().setId(new WholesalerstockId(wholesaler.get().getId(), beer.get().getId()));
				return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_INSERTED, HttpStatus.CREATED,
						wholesalerStockService.save(wholesalerStock.get()));
			}

		}
	}

	/**
	 * FR5- A wholesaler can update the remaining quantity of a beer in his stock
	 * 
	 * @param wholesalerStockDto, entity to be saved
	 * @return the saved entity
	 */
	@PatchMapping("/save")
	public ResponseEntity<Object> updateWholesalerStock(@RequestBody WholesalerStockDto wholesalerStockDto) {
		Optional<Wholesalerstock> wholesalerStock = wholesalerStockService
				.findById(new WholesalerstockId(wholesalerStockDto.getWholesalerId(), wholesalerStockDto.getBeerId()));

		if (!wholesalerStock.isPresent()) {
			return ResponseHandler.generateResponse(Constants.ERR_WHOLESALER_STOCK_MUST_EXIST, HttpStatus.NOT_FOUND,
					null);

		} else {
			wholesalerStock.get().setQuantity(wholesalerStockDto.getQuantity());

			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_INSERTED, HttpStatus.CREATED,
					wholesalerStockService.save(wholesalerStock.get()));

		}
	}

}