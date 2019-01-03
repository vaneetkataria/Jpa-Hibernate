package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.math.BigDecimal;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicUpdate
@DynamicInsert
@NamedQueries({ @NamedQuery(name = "findAll", query = "select i from Instructor i"),
		@NamedQuery(name = "findNameAndSalaryHavingSalaryGreterThan", query = "select i.name , i.salary from Instructor i where i.salary > :salary"),
		@NamedQuery(name = "findAllOrderByBirthDateTimeDesc", query = "select i from Instructor i order by i.birthDateTime desc"),
		@NamedQuery(name = "findAllHavingAddressLike", query = "select i from Instructor i where i.address LIKE CONCAT('%',:address ,'%')"),
		@NamedQuery(name = "findAllHavingFatherNameLike", query = "select i from Instructor i where i.fatherName like CONCAT('%',:fatherName ,'%')"),
		@NamedQuery(name = "findAllBornBetweenBirthDateTimesOrderByBirthDateTimeDesc", query = "select i from Instructor i where i.birthDateTime between :birthDateTimeStart and :birthDateTimeEnd order by i.birthDateTime desc"),
		@NamedQuery(name = "findAllNotBornBetweenBirthDateTimesOrderByBirthDateTimeDesc", query = "select i from Instructor i where i.birthDateTime not between :birthDateTimeStart and :birthDateTimeEnd order by i.birthDateTime desc"),
		@NamedQuery(name = "findAllHavingWorkingTimeBetween", query = "select i from Instructor i where i.dayStartTime > :dayStartTime and i.dayOffTime < :dayOffTime "),
		@NamedQuery(name = "countHavingSalaryBetween", query = "select count(i) from Instructor i  where i.salary between :monthlySalaryMin and :monthlySalaryMax"),
		@NamedQuery(name = "findDistinctFatherName", query = "select distinct(i.fatherName) from Instructor i"),
		@NamedQuery(name = "calculateAverageSalary", query = "select avg(i.salary) from Instructor i"),
		@NamedQuery(name = "findMinSalary", query = "select min(i.salary) from Instructor i"),
		@NamedQuery(name = "findMaxSalary", query = "select max(i.salary) from Instructor i"),
		@NamedQuery(name = "findAllHavingSalaryGreaterThan", query = "select i from Instructor i where i.id in (select j.id from Instructor j where j.salary >= :salary)"),
		@NamedQuery(name = "findAllHavingSalaryGreaterThanBigQuery", query = "select i from Instructor i where i.id in (select j.id from Instructor j where j.name in (select k.name from Instructor k where k.salary >= :salary))"),
		@NamedQuery(name = "updateInstructorSalaryHavingId", query = "update Instructor i set i.salary = :salary where i.id = :id"),
		@NamedQuery(name = "updateInstructorSalaryHavingIdsIn", query = "update Instructor i set i.salary = :salary where i.id in (:ids)"),
		@NamedQuery(name = "deleteInstructorHavingId", query = "delete from Instructor i where i.id = :id"),
		@NamedQuery(name = "deleteInstructorHavingIdsIn", query = "delete from Instructor i where i.id in (:ids)"),
		@NamedQuery(name = "countHavingFatherName", query = "select i.fatherName , count(i) from Instructor i where i.salary > :salary group by i.fatherName having count(i) > 0")

})
public class Instructor {
	public static final String FIND_ALL_INSTRUCTORS = "select * from instructor";
	public static final String DELETE_INSTRICTORS_HAVING_IDS = "delete from instructor where id IN (:ids) ";
	public static final String UPDATE_INSTRUCTORS_HAVING_FATHERNAME_LIKE = "update instructor i set i.monthly_salary = :salary where i.id in (select j.id from(select k.id from instructor k where k.father_name like CONCAT('%',:fatherName,'%')) as j)";
	public static final String UPDATE_INSTRUCTORS_HAVING_FATHERNAME_LIKE_SALARY_GREATER_THAN = "update instructor i set i.monthly_salary = :salary where i.id in (select j.id from (select k.id from instructor k where k.father_name like CONCAT('%',:fatherName ,'%') and k.monthly_salary > :selectSalary) as j)";
	public static final String DELETE_INSTRUCTORS_HAVING_FATHERNAME_LIKE = "delete from instructor where id in (select j.id from (select k.id from instructor k where k.father_name like CONCAT('%',:fatherName,'%')) as j)";
	public static final String DELETE_INSTRUCTORS_HAVING_FATHERNAME_LIKE_SALARY_GREATER_THAN = "delete from instructor where id in (select j.id from (select k.id from instructor k where k.father_name like CONCAT('%',:fatherName ,'%') and k.monthly_salary > :selectSalary) as j)";

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
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "proof_id")
	private IdProof idProof;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "instructor")
	private Set<Vehicle> vehicles;
	@ManyToMany
	@JoinTable(name = "instructors_students_junction_tbl", joinColumns = {
			@JoinColumn(name = "instructor_id") }, inverseJoinColumns = { @JoinColumn(name = "student_id") })
	private Set<Student> students;
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

	public IdProof getIdProof() {
		return idProof;
	}

	public void setIdProof(IdProof idProof) {
		this.idProof = idProof;
	}

	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Instructor [id=").append(id).append(", version=").append(version).append(", name=").append(name)
				.append(", fatherName=").append(fatherName).append(", motherName=").append(motherName)
				.append(", address=").append(address).append(", photo=").append(Arrays.toString(photo))
				.append(", salary=").append(salary).append(", birthDateTime=").append(birthDateTime)
				.append(", birthDateTimeZoneOffset=").append(birthDateTimeZoneOffset).append(", dayStartTime=")
				.append(dayStartTime).append(", dayOffTime=").append(dayOffTime).append(", createdDate=")
				.append(createdDate).append(", updatedDate=").append(updatedDate).append("]");
		return builder.toString();
	}

}
