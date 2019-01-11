package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.Document;
import com.katariasoft.technologies.jpaHibernate.college.data.enums.VechicleType;

@Entity
@DynamicInsert
@DynamicUpdate
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;
	@ManyToOne(fetch = FetchType.LAZY)
	private Instructor instructor;
	@Column(nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private VechicleType vechicleType;
	@Size(min = 8, max = 100)
	@NotBlank
	@Column(length = 100, nullable = false, unique = true)
	private String vehicleNumber;
	@OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<Document> documents = new HashSet<>();
	@Past
	@Column(columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = false)
	private Instant purchasedDateTime;
	@Column(nullable = false)
	private int purchasedDateZoneOffset;
	@CreationTimestamp
	@Column(columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = false)
	private Instant creationDate;
	@UpdateTimestamp
	@Column(columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)", nullable = false)
	private Instant updatedDate;

	public Vehicle() {
		super();
	}

	public Vehicle(VechicleType vechicleType, @Size(min = 8, max = 100) @NotBlank String vehicleNumber,
			@Past Instant purchasedDateTime, int purchasedDateZoneOffset, Instant creationDate, Instant updatedDate) {
		super();
		this.vechicleType = vechicleType;
		this.vehicleNumber = vehicleNumber;
		this.purchasedDateTime = purchasedDateTime;
		this.purchasedDateZoneOffset = purchasedDateZoneOffset;
		this.creationDate = creationDate;
		this.updatedDate = updatedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VechicleType getVechicleType() {
		return vechicleType;
	}

	public void setVechicleType(VechicleType vechicleType) {
		this.vechicleType = vechicleType;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public void setPurchasedOffsetDateTime(OffsetDateTime purchasedOffsetDateTime) {
		this.purchasedDateTime = Objects.isNull(purchasedOffsetDateTime) ? null : purchasedOffsetDateTime.toInstant();
		this.purchasedDateZoneOffset = Objects.isNull(purchasedOffsetDateTime) ? 0
				: purchasedOffsetDateTime.getOffset().getTotalSeconds();
	}

	public void setPurchasedInstant(Instant purchasedInstant) {
		this.purchasedDateTime = Objects.isNull(purchasedInstant) ? null : purchasedInstant;
		this.purchasedDateZoneOffset = 0;
	}

	public OffsetDateTime getPurchasedOffsetDateTime() {
		return this.purchasedDateTime.atOffset(ZoneOffset.ofTotalSeconds(purchasedDateZoneOffset));
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public Instant getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Instant updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void addDocument(Document document) {
		if (Objects.nonNull(document)) {
			documents.add(document);
			document.setVehicle(this);
		}
	}

	public void addDocuments(Set<Document> documents) {
		if (Objects.nonNull(documents))
			documents.forEach(d -> addDocument(d));
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", vechicleType=" + vechicleType + ", vehicleNumber=" + vehicleNumber
				+ ", purchasedDateTime=" + purchasedDateTime + ", purchasedDateZoneOffset=" + purchasedDateZoneOffset
				+ ", creationDate=" + creationDate + ", updatedDate=" + updatedDate + "]";
	}

}
