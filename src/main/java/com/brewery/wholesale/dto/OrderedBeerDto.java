package com.brewery.wholesale.dto;

public class OrderedBeerDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer beerId;
	private int quantity;

	public Integer getBeerId() {
		return beerId;
	}

	public void setBeerId(Integer beerId) {
		this.beerId = beerId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}