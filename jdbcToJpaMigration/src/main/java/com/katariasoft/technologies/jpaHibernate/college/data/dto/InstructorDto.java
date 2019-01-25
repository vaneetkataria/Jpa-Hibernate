package com.katariasoft.technologies.jpaHibernate.college.data.dto;

import java.io.Serializable;

import com.katariasoft.technologies.jpaHibernate.college.data.enums.VechicleType;

public class InstructorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int instructorId;
	private String name;
	private String fatherName;
	private String address;
	private String idProofNo;
	private String vehicleNo;
	private VechicleType vehicleType;
	private String studentName;
	private String studentFatherName;
	private String stuInstructorName;
	private String studenVehicleNo;
	private String stuVehcileDocumentName;

	public InstructorDto(int instructorId, String name, String fatherName, String address, String idProofNo,
			String vehicleNo, VechicleType vehicleType, String studentName, String studentFatherName,
			String stuInstructorName, String studenVehicleNo, String stuVehcileDocumentName) {
		super();
		this.instructorId = instructorId;
		this.name = name;
		this.fatherName = fatherName;
		this.address = address;
		this.idProofNo = idProofNo;
		this.vehicleNo = vehicleNo;
		this.vehicleType = vehicleType;
		this.studentName = studentName;
		this.studentFatherName = studentFatherName;
		this.stuInstructorName = stuInstructorName;
		this.studenVehicleNo = studenVehicleNo;
		this.stuVehcileDocumentName = stuVehcileDocumentName;
	}

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdProofNo() {
		return idProofNo;
	}

	public void setIdProofNo(String idProofNo) {
		this.idProofNo = idProofNo;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public VechicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VechicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentFatherName() {
		return studentFatherName;
	}

	public void setStudentFatherName(String studentFatherName) {
		this.studentFatherName = studentFatherName;
	}

	public String getStuInstructorName() {
		return stuInstructorName;
	}

	public void setStuInstructorName(String stuInstructorName) {
		this.stuInstructorName = stuInstructorName;
	}

	public String getStudenVehicleNo() {
		return studenVehicleNo;
	}

	public void setStudenVehicleNo(String studenVehicleNo) {
		this.studenVehicleNo = studenVehicleNo;
	}

	public String getStuVehcileDocumentName() {
		return stuVehcileDocumentName;
	}

	public void setStuVehcileDocumentName(String stuVehcileDocumentName) {
		this.stuVehcileDocumentName = stuVehcileDocumentName;
	}

	@Override
	public String toString() {
		return "InstructorDto [instructorId=" + instructorId + ", name=" + name + ", fatherName=" + fatherName
				+ ", address=" + address + ", idProofNo=" + idProofNo + ", vehicleNo=" + vehicleNo + ", vehicleType="
				+ vehicleType + ", studentName=" + studentName + ", studentFatherName=" + studentFatherName
				+ ", stuInstructorName=" + stuInstructorName + ", studenVehicleNo=" + studenVehicleNo
				+ ", stuVehcileDocumentName=" + stuVehcileDocumentName + "]";
	}

}
