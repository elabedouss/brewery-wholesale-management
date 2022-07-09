package com.brewery.wholesale.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Brewery", catalog = "SNDTmgluQz")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Brewery implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	public Brewery() {
	}

	public Brewery(String name) {
		this.name = name;
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

	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
