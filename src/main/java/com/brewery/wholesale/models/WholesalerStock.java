package com.brewery.wholesale.models;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wholesalerstock", catalog = "sndtmgluqz")
public class Wholesalerstock implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WholesalerstockId id;
	private Beer beer;
	private Wholesaler wholesaler;
	private int quantity;

	public Wholesalerstock() {
	}

	public Wholesalerstock(WholesalerstockId id, Beer beer, Wholesaler wholesaler, int quantity) {
		this.id = id;
		this.beer = beer;
		this.wholesaler = wholesaler;
		this.quantity = quantity;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "wholesalerId", column = @Column(name = "wholesalerId", nullable = false)),
			@AttributeOverride(name = "beerId", column = @Column(name = "beerId", nullable = false)) })
	public WholesalerstockId getId() {
		return this.id;
	}

	public void setId(WholesalerstockId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "beerId", nullable = false, insertable = false, updatable = false)
	public Beer getBeer() {
		return this.beer;
	}

	public void setBeer(Beer beer) {
		this.beer = beer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wholesalerId", nullable = false, insertable = false, updatable = false)
	public Wholesaler getWholesaler() {
		return this.wholesaler;
	}

	public void setWholesaler(Wholesaler wholesaler) {
		this.wholesaler = wholesaler;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
