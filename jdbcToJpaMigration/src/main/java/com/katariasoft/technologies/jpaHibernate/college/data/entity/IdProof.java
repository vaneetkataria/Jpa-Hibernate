package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdProof {

	@Id
	private int id;
	private String proofNo;
	private String name;;
	private String fatherName;
	private String motherName;
	private String address;
	private char sex;
	private boolean isForeigner;

	public IdProof() {
	}

	public IdProof(String proofNo, String name, String fatherName, String motherName, String address, char sex,
			boolean isForeigner) {
		super();
		this.proofNo = proofNo;
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.sex = sex;
		this.isForeigner = isForeigner;
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

}
