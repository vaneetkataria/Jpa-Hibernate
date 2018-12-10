package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	private int id;
	private String name;
	private String fatherName;
	private String motherName;
	private String address;
	private BigDecimal fees;

	public Student() {
	}

	public Student(String name, String fatherName, String motherName, String address, BigDecimal fees) {
		super();
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.fees = fees;
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

	public String getAddess() {
		return address;
	}

	public void setAddess(String addess) {
		this.address = addess;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getFees() {
		return fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

}
