package com.brewery.wholesale.dto;

import java.util.List;

public class OrderDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer wholesalerId;
	private List<OrderedBeerDto> orderedBeers;

	public Integer getWholesalerId() {
		return wholesalerId;
	}

	public void setWholesalerId(Integer wholesalerId) {
		this.wholesalerId = wholesalerId;
	}

	public List<OrderedBeerDto> getOrderedBeers() {
		return orderedBeers;
	}

	public void setOrderedBeers(List<OrderedBeerDto> orderedBeers) {
		this.orderedBeers = orderedBeers;
	}

}