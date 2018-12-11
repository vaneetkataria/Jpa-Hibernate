package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	private int id;
	@Column(length = 64, nullable = false)
	private String name;
	@Column(length = 64, nullable = false)
	private String fatherName;
	@Column(length = 64)
	private String motherName;
	@Column(length = 1000, nullable = false)
	private String address;
	@Column(columnDefinition = "BLOB")
	private byte[] photo;
	@Column(precision = 9, scale = 2, nullable = false)
	private BigDecimal fees;
	@Column(nullable = false)
	private LocalTime dayStartTime;
	@Column(nullable = false)
	private LocalTime dayOffTime;
	@Column(nullable = false)
	private LocalDateTime createdDate;
	@Column(nullable = false)
	private LocalDateTime updatedDate;

	public Student() {
	}

	public Student(String name, String fatherName, String motherName, String address, byte[] photo, BigDecimal fees,
			LocalTime dayStartTime, LocalTime dayOffTime, LocalDateTime createdDate, LocalDateTime updatedDate) {
		super();
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.photo = photo;
		this.fees = fees;
		this.dayStartTime = dayStartTime;
		this.dayOffTime = dayOffTime;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public BigDecimal getFees() {
		return fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

	public LocalTime getDayStartTime() {
		return dayStartTime;
	}

	public void setDayStartTime(LocalTime dayStartTime) {
		this.dayStartTime = dayStartTime;
	}

	public LocalTime getDayOffTime() {
		return dayOffTime;
	}

	public void setDayOffTime(LocalTime dayOffTime) {
		this.dayOffTime = dayOffTime;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
