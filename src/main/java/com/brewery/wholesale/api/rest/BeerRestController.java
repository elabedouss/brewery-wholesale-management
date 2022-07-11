package com.brewery.wholesale.api.rest;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brewery.wholesale.api.rest.response.ResponseHandler;
import com.brewery.wholesale.dto.BeerDto;
import com.brewery.wholesale.models.Beer;
import com.brewery.wholesale.models.Brewery;
import com.brewery.wholesale.services.BeerService;
import com.brewery.wholesale.services.BreweryService;
import com.brewery.wholesale.utils.Constants;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

	@Autowired
	private BeerService beerService;

	@Autowired
	private BreweryService breweryService;

	private ModelMapper mapper = new ModelMapper();

	/**
	 * FR1 retrieve all beers by brewer
	 * 
	 * @return list of beers
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getBeersByBrewery(@PathVariable("id") Integer id) {
		List<Beer> result = beerService.findAllbyBrewery(id);
		if (result.isEmpty()) {
			return ResponseHandler.generateResponse(Constants.EMPTY_DATA, HttpStatus.NOT_FOUND, result);
		} else {
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_RETREIVED, HttpStatus.OK, result);
		}
	}

	/**
	 * FR2 Add new beer by existing brewer
	 * 
	 * @param beerDto, entity to be saved
	 * @return the saved entity
	 */
	@PostMapping("/save")
	public ResponseEntity<Object> saveBeerByBrewer(@RequestBody BeerDto beerDto) {
		Optional<Brewery> brewery = breweryService.findById(beerDto.getBreweryId());

		if (brewery.isPresent()) {
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_INSERTED, HttpStatus.CREATED,
					beerService.save(mapper.map(beerDto, Beer.class)));
		} else {
			return ResponseHandler.generateResponse(Constants.ERR_BREWERY_MUST_EXIST, HttpStatus.NOT_FOUND, null);
		}
	}

	/**
	 * FR3 Delete a beer by brewer
	 * 
	 * @param id, brewer id
	 * @return HttpStatus
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBeerByBrewer(@PathVariable("id") int id) {
		Optional<Beer> beer = beerService.findById(id);
		if (beer.isPresent()) {
			beerService.deleteById(id);
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_DELETED, HttpStatus.ACCEPTED, null);
		} else {
			return ResponseHandler.generateResponse(Constants.ERR_BEER_MUST_EXIST, HttpStatus.NOT_FOUND, null);
		}
	}

}