package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.katariasoft.technologies.jpaHibernate.college.data.enums.VechicleType;

@Entity
@DynamicInsert
@DynamicUpdate
public class vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Instructor instructor;
	@Column(nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private VechicleType vechicleType;
	@Size(min = 8, max = 30)
	@NotBlank
	@Column(length = 30, nullable = false, unique = true)
	private String vehicleNumber;
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

}
