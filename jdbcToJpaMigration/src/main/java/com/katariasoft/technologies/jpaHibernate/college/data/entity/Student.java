package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicInsert
@DynamicUpdate
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	@Version
	@Column(columnDefinition = "int(11) not null default 0")
	private int version;
	@Column(length = 64, nullable = false)
	private String name;
	@Column(length = 64, nullable = false)
	private String fatherName;
	@Column(length = 64)
	private String motherName;
	@Column(length = 1000, nullable = false)
	private String address;

	@BatchSize(size = 10)
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Vehicle> vehicles = new HashSet<>();

	@BatchSize(size = 10)
	@ManyToMany(mappedBy = "students", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Set<Instructor> instructors = new HashSet<>();
	@Column(columnDefinition = "BLOB")
	private byte[] photo;
	@Column(precision = 9, scale = 2, nullable = false)
	private BigDecimal fees;
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

	public Student() {
	}

	public Student(String name, String fatherName, String motherName, String address, byte[] photo, BigDecimal fees,
			LocalTime dayStartTime, LocalTime dayOffTime, Instant createdDate, Instant updatedDate) {
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

	public Set<Instructor> getInstructors() {
		return instructors;
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

	public void addVehicle(Vehicle vehicle) {
		if (Objects.nonNull(vehicle)) {
			vehicles.add(vehicle);
			vehicle.setStudent(this);
		}

	}

	public void addVehicles(Set<Vehicle> vehicles) {
		if (Objects.nonNull(vehicles))
			vehicles.forEach(v -> addVehicle(v));
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", version=" + version + ", name=" + name + ", fatherName=" + fatherName
				+ ", motherName=" + motherName + ", address=" + address + ", photo=" + Arrays.toString(photo)
				+ ", fees=" + fees + ", dayStartTime=" + dayStartTime + ", dayOffTime=" + dayOffTime + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
