package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "id_proof_tbl")
@DynamicInsert
@DynamicUpdate
public class IdProof {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(updatable = false)
	private int id;
	@Version
	@Column(columnDefinition = "int(11) not null default 0")
	private int version;
	@Column(length = 20, name = "proof_sequence_no", nullable = false, unique = true)
	private String proofNo;
	@OneToOne(mappedBy = "idProof", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Instructor instructor;
	@Column(length = 64, nullable = false)
	private String name;
	@Column(length = 64, nullable = false)
	private String fatherName;
	@Column(length = 64)
	private String motherName;
	@Column(length = 1000, nullable = false)
	private String address;
	@Column(nullable = false)
	private char sex;
	@Column(nullable = false)
	private boolean isForeigner;
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
	private Instant createdDate;
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6)")
	private Instant updatedDate;

	public IdProof() {
	}

	public IdProof(String proofNo, String name, String fatherName, String motherName, String address, char sex,
			boolean isForeigner, Instant updatedDate, Instant createdDate) {
		super();
		this.proofNo = proofNo;
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.sex = sex;
		this.isForeigner = isForeigner;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public boolean isForeigner() {
		return isForeigner;
	}

	public void setForeigner(boolean isForeigner) {
		this.isForeigner = isForeigner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProofNo() {
		return proofNo;
	}

	public void setProofNo(String proofNo) {
		this.proofNo = proofNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IdProof [id=").append(id).append(", version=").append(version).append(", proofNo=")
				.append(proofNo).append(", name=").append(name).append(", fatherName=").append(fatherName)
				.append(", motherName=").append(motherName).append(", address=").append(address).append(", sex=")
				.append(sex).append(", isForeigner=").append(isForeigner).append(", createdDate=").append(createdDate)
				.append(", updatedDate=").append(updatedDate).append("]");
		return builder.toString();
	}

}
