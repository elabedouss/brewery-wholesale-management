package com.brewery.wholesale.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Wholesaler", catalog = "SNDTmgluQz")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Wholesaler implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	public Wholesaler() {
	}

	public Wholesaler(String name) {
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
