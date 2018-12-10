package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Instructor {

	@Id
	private int id;
	private String name;
	private String fatherName;
	private String motherName;
	private String address;
	private BigDecimal salary;

	public Instructor() {
	}

	public Instructor(String name, String fatherName, String motherName, String address, BigDecimal salary) {
		super();
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.salary = salary;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
