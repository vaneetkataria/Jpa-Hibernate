package com.katariasoft.technologies.jpaHibernate.college.data.dao;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

/*
	
	@NamedQuery(name = "findAllHavingWorkingTimeBetween", query = "select i from Instructor i where i.dayStartTime > :dayStartTime and i.dayOffTime < :dayOffTime "),
	@NamedQuery(name = "updateInstructorSalaryHavingId", query = "update Instructor i set i.salary = :salary where i.id = :id"),
		@NamedQuery(name = "updateInstructorSalaryHavingIdsIn", query = "update Instructor i set i.salary = :salary where i.id in (:ids)"),
		@NamedQuery(name = "updateInstructorSalaryHavingFatherNameLike", query = "update Instructor i set i.salary = :salary where i.id in (select j.id from (select k.id from Instructor k where k.fatherName like CONCAT('%',:fatherName ,'%')) as j)"),
		@NamedQuery(name = "updateInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan", query = "update Instructor i set i.salary = :salary where i.id in (select j.id from (select k.id from Instructor k where k.fatherName like CONCAT('%',:fatherName ,'%') and k.salary = :selectSalary) as j)"),
		@NamedQuery(name = "deleteInstructorSalaryHavingId", query = "delete from Instructor i where i.id = :id"),
		@NamedQuery(name = "deleteInstructorSalaryHavingIdsIn", query = "delete from Instructor i where i.id in (:ids)"),
		@NamedQuery(name = "deleteInstructorSalaryHavingFatherNameLike", query = "delete from Instructor i where i.id in (select j.id from (select k.id from Instructor k where k.fatherName like CONCAT('%',:fatherName ,'%')) as j)"),
		@NamedQuery(name = "deleteInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan", query = "delete from Instructor i where i.id in (select j.id from (select k.id from Instructor k where k.fatherName like CONCAT('%',:fatherName ,'%') and k.salary = :selectSalary) as j)") })
*/

@Repository
public class InstructorRepository {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private QueryExecutor queryExecutor;

	public List<Instructor> fetchAllInstructors() {
		return queryExecutor.fetchList("findAll", null, Instructor.class);
	}

	public List<Instructor> findAllHavingAddressLike(String address) {
		return queryExecutor.fetchList("findAllHavingAddressLike", Collections.singletonMap("address", address),
				Instructor.class);
	}

	public List<Instructor> findAllHavingFatherNameLike(String fatherName) {
		return queryExecutor.fetchList("findAllHavingFatherNameLike",
				Collections.singletonMap("fatherName", fatherName), Instructor.class);
	}

	public List<Instructor> findAllOrderByBirthDateTimeDesc() {
		return queryExecutor.fetchList("findAllOrderByBirthDateTimeDesc", null, Instructor.class);
	}

	public List<Instructor> findAllHavingSalaryGreaterThan(BigDecimal salary) {
		return queryExecutor.fetchList("findAllHavingSalaryGreaterThan", Collections.singletonMap("salary", salary),
				Instructor.class);
	}

	public List<Instructor> findAllHavingSalaryGreaterThanBigQuery(BigDecimal salary) {
		return queryExecutor.fetchList("findAllHavingSalaryGreaterThanBigQuery",
				Collections.singletonMap("salary", salary), Instructor.class);
	}

	public List<String> findDistinctFatherName() {
		return queryExecutor.fetchList("findDistinctFatherName", null, String.class);
	}

	public long countHavingSalaryBetween(BigDecimal monthlySalaryMin, BigDecimal monthlySalaryMax) {
		return queryExecutor.fetchValue("countHavingSalaryBetween",
				CollectionUtils.mapOf("monthlySalaryMin", monthlySalaryMax, "monthlySalaryMax", monthlySalaryMax),
				Long.class);
	}

	public BigDecimal findMinSalary() {
		return queryExecutor.fetchValue("findMinSalary", null, BigDecimal.class);
	}

	public BigDecimal findMaxSalary() {
		return queryExecutor.fetchValue("findMaxSalary", null, BigDecimal.class);
	}

	public Double calculateAverageSalary() {
		return queryExecutor.fetchValue("calculateAverageSalary", null, Double.class);
	}

	public List<Instructor> findAllBornBetweenBirthDateTimesOrderByBirthDateTimeDesc(Instant birthDateTimeStart,
			Instant birthDateTimeEnd) {
		return queryExecutor.fetchList("findAllBornBetweenBirthDateTimesOrderByBirthDateTimeDesc",
				CollectionUtils.mapOf("birthDateTimeStart", birthDateTimeStart, "birthDateTimeEnd", birthDateTimeEnd),
				Instructor.class);
	}

	public List<Instructor> findAllNotBornBetweenBirthDateTimesOrderByBirthDateTimeDesc(Instant birthDateTimeStart,
			Instant birthDateTimeEnd) {
		return queryExecutor.fetchList("findAllNotBornBetweenBirthDateTimesOrderByBirthDateTimeDesc",
				CollectionUtils.mapOf("birthDateTimeStart", birthDateTimeStart, "birthDateTimeEnd", birthDateTimeEnd),
				Instructor.class);
	}

	public void updateInstructorSalaryHavingId(int id) {
		queryExecutor.executeQuery("updateInstructorSalaryHavingId", Collections.singletonMap("id", id));
	}

	public void updateInstructorSalaryHavingIdsIn(List<Integer> ids) {
		queryExecutor.executeQuery("updateInstructorSalaryHavingIdsIn", Collections.singletonMap("ids", ids));
	}

	public void updateInstructorSalaryHavingFatherNameLike(String fatherName, BigDecimal salary) {
		queryExecutor.executeQuery("updateInstructorSalaryHavingFatherNameLike",
				CollectionUtils.mapOf("fatherName", fatherName, "salary", salary));
	}

	public void updateInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan(String fatherName,
			BigDecimal salaryToUpdate, BigDecimal salaryGreaterThan) {
		queryExecutor.executeQuery("updateInstructorSalaryHavingFatherNameLike", CollectionUtils.mapOf("fatherName",
				fatherName, "salary", salaryToUpdate, "selectSalary", salaryGreaterThan));
	}

	public void deleteInstructorSalaryHavingId(int id) {
		queryExecutor.executeQuery("deleteInstructorSalaryHavingId", Collections.singletonMap("id", id));
	}

	public void deleteInstructorSalaryHavingIdsIn(List<Integer> ids) {
		queryExecutor.executeQuery("deleteInstructorSalaryHavingIdsIn", Collections.singletonMap("ids", ids));
	}

	public void deleteInstructorSalaryHavingFatherNameLike(String fatherName) {
		queryExecutor.executeQuery("updateInstructorSalaryHavingFatherNameLike",
				CollectionUtils.mapOf("fatherName", fatherName));
	}

	public void deleteInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan(String fatherName,
			BigDecimal salaryGreaterThan) {
		queryExecutor.executeQuery("updateInstructorSalaryHavingFatherNameLike",
				CollectionUtils.mapOf("fatherName", fatherName, "selectSalary", salaryGreaterThan));
	}

	public boolean deleteAllInstructors(List<Integer> ids) {
		try {
			em.createQuery(Instructor.DELETE_INSTRICTORS_HAVING_IDS).setParameter("ids", ids).executeUpdate();
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Instructor getInstructor(int id) {
		try {
			Instructor instructor = em.find(Instructor.class, id);
			instructor.setAddress("Updated in Requires new Transaction");
			return instructor;
		} catch (Exception e) {
			throw e;
		}
	}

}
