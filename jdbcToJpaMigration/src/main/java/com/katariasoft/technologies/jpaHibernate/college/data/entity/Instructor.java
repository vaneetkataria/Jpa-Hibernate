package com.katariasoft.technologies.jpaHibernate.college.data.entity;

import java.math.BigDecimal;

import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

/*
select * from instructor;
select * from instructor order by birth_date_time desc;
select * from instructor where address like '%#1074%';
select * from instructor where father_name like '%Naresh%';
select * from instructor where birth_date_time between '1980-01-01 00:00:00.000000' and '1995-01-01 23:59:59.999999' order by birth_date_time desc; 
select * from instructor where birth_date_time not between '1980-01-01 00:00:00.000000' and '1995-01-01 23:59:59.999999' order by birth_date_time ; 
select * from instructor where day_start_time > '10:00:00' and  day_off_time < '19:01:00';
select count(*) from instructor where monthly_salary between 10000 and 100000;
select distinct father_name from instructor ;
select sum(monthly_salary) from instructor;
select avg(monthly_salary) from instructor;
select min(monthly_salary) from instructor;
select max(monthly_salary) from instructor;
select * from instructor where id in (select id from instructor where monthly_salary >= 10000);
select * from instructor where id in (select id from instructor where name in (select name from instructor where monthly_salary >= 10000 ));
select father_name , count(*) from instructor where father_name like '%Naresh%' group by father_name having count(*) > 0;
*/
//updates
/*
update instructor set monthly_salary = 130000 where id = 1; 
update instructor set monthly_salary = 150000 where id in (1 , 2); 
update instructor set monthly_salary = 170000 where id in 
(select id from  (select * from instructor i where i.father_name like '%Naresh%')
as j);
update instructor set monthly_salary = 200000 where id in (
select id from (select id from instructor where father_name like '%Naresh%' and monthly_salary > 50000) as j);
*/
//deletes
/*
delete from instructor where id = 5;
delete from instructor where id in (3 , 4);
delete from instructor where id in (
select id from  
(select id from instructor where father_name like '%Naresh%') as j);
delete from instructor where id in (select id from (select id from instructor where father_name like '%Naresh%' and monthly_salary > 20000)as j);
*/

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
public class Instructor implements Cloneable {
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
	@OneToOne(cascade = { CascadeType.ALL }, orphanRemoval = true)
	private IdProof idProof;
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
