package com.brewery.wholesale.dto;

public class WholesalerStockDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer wholesalerId;
	private Integer beerId;
	private int quantity;

	public Integer getWholesalerId() {
		return wholesalerId;
	}

	public void setWholesalerId(Integer wholesalerId) {
		this.wholesalerId = wholesalerId;
	}

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