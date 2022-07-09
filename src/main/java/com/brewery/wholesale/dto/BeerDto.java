package com.brewery.wholesale.dto;

public class BeerDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private float alcoholContent;
	private float price;
	private int breweryId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAlcoholContent() {
		return alcoholContent;
	}

	public void setAlcoholContent(float alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getBreweryId() {
		return breweryId;
	}

	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}

}