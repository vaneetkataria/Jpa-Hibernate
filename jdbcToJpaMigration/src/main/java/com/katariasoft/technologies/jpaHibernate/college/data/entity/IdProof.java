package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "id_proof_tbl")
public class IdProof {

	@Id
	private int id;
	@Column(length = 20, name = "proof_sequence_no", nullable = false, unique = true)
	private String proofNo;
	@Column(length = 64, nullable = false)
	private String name;
	@Column(length = 64, nullable = false)
	private String fatherName;
	@Column(length = 64)
	private String motherName;
	@Column(length = 1000, nullable = false)
	private String address;
	@Column(columnDefinition = "char(1)", length = 1, nullable = false)
	private char sex;
	@Column(nullable = false)
	private boolean isForeigner;
	@Column(nullable = false)
	private LocalDateTime createdDate;
	@Column(nullable = false)
	private LocalDateTime updatedDate;

	public IdProof() {
	}

	public IdProof(String proofNo, String name, String fatherName, String motherName, String address, char sex,
			boolean isForeigner, LocalDateTime updatedDate, LocalDateTime createdDate) {
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
