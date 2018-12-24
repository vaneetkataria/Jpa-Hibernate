package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Synchronize;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicUpdate
@DynamicInsert
public class Instructor implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	@Version
	@Column(columnDefinition = "int(11) not null default 0")
	private int version = 0;
	@Column(length = 64, nullable = false)
	// @Size(min = 10, max = 64)
	private String name;
	@Column(length = 64, nullable = false)
	private String fatherName;
	@Column(length = 64)
	private String motherName;
	@Column(length = 1000, nullable = false)
	private String address;
	@Column(columnDefinition = "BLOB")
	private byte[] photo;
	@Column(nullable = false, name = "monthly_salary", scale = 2, precision = 9)
	// @DecimalMin(value = "10.00")
	private BigDecimal salary;
	@Column(nullable = false, columnDefinition = "TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)")
	private Instant birthDateTime;
	@Column(nullable = false)
	private int birthDateTimeZoneOffset;
	@Column(nullable = false)
	private LocalTime dayStartTime;
	@Column(nullable = false)
	private LocalTime dayOffTime;
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
	private Instant createdDate;
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
	private Instant updatedDate;

	public Instructor() {
	}

	public Instructor(String name, String fatherName, String motherName, String address, byte[] photo,
			BigDecimal salary, Instant birthDateTime, int birthDateTimeZoneOffset, LocalTime dayStartTime,
			LocalTime dayOffTime, Instant createdDate, Instant updatedDate) {
		super();
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.photo = photo;
		this.salary = salary;
		this.birthDateTime = birthDateTime;
		this.birthDateTimeZoneOffset = birthDateTimeZoneOffset;
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

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public Instant getBirthDateTime() {
		return birthDateTime;
	}

	public OffsetDateTime getZoneOffsetBirthDateTime() {
		return birthDateTime.atOffset(ZoneOffset.ofTotalSeconds(birthDateTimeZoneOffset));
	}

	public void setBirthDateTime(Instant birthDate) {
		this.birthDateTime = birthDate;
		this.birthDateTimeZoneOffset = 0;
	}

	public void setBirthDateTime(OffsetDateTime birthDateTime) {
		this.birthDateTime = birthDateTime.toInstant();
		this.birthDateTimeZoneOffset = birthDateTime.getOffset().getTotalSeconds();
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

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Instant getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Instant updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
