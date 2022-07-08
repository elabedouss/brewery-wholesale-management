package com.brewery.wholesale.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Beer", catalog = "SNDTmgluQz")
public class Beer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Brewery brewery;
	private String name;
	private float alcoholContent;
	private float price;

	public Beer() {
	}

	public Beer(Brewery brewery, String name, float alcoholContent, float price) {
		this.brewery = brewery;
		this.name = name;
		this.alcoholContent = alcoholContent;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "breweryId", nullable = false)
	public Brewery getBrewery() {
		return this.brewery;
	}

	public void setBrewery(Brewery brewery) {
		this.brewery = brewery;
	}

	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "alcoholContent", nullable = false, precision = 12, scale = 0)
	public float getAlcoholContent() {
		return this.alcoholContent;
	}

	public void setAlcoholContent(float alcoholContent) {
		this.alcoholContent = alcoholContent;
	}

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
