package com.katariasoft.technologies.jpaHibernate.college.data.entity.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;

@Entity
@DynamicInsert
@DynamicUpdate
public class Document {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	@Column(length = 50, nullable = false)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private Vehicle vehicle;

	public Document() {
	}

	public Document(String name) {
		super();
		this.name = name;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", name=" + name + "]";
	}

}
