package com.brewery.wholesale.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class WholesalerstockId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int wholesalerId;
	private int beerId;

	public WholesalerstockId() {
	}

	public WholesalerstockId(int wholesalerId, int beerId) {
		this.wholesalerId = wholesalerId;
		this.beerId = beerId;
	}

	@Column(name = "wholesalerId", nullable = false)
	public int getWholesalerId() {
		return this.wholesalerId;
	}

	public void setWholesalerId(int wholesalerId) {
		this.wholesalerId = wholesalerId;
	}

	@Column(name = "beerId", nullable = false)
	public int getBeerId() {
		return this.beerId;
	}

	public void setBeerId(int beerId) {
		this.beerId = beerId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WholesalerstockId))
			return false;
		WholesalerstockId castOther = (WholesalerstockId) other;

		return (this.getWholesalerId() == castOther.getWholesalerId()) && (this.getBeerId() == castOther.getBeerId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getWholesalerId();
		result = 37 * result + this.getBeerId();
		return result;
	}

}
