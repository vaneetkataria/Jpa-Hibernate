package com.katariasoft.technologies.jpaHibernate.college.data.dao;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

/*
	
	@NamedQuery(name = "findAllHavingWorkingTimeBetween", query = "select i from Instructor i where i.dayStartTime > :dayStartTime and i.dayOffTime < :dayOffTime "),
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

	public List<Instructor> fetchAllInstructorsWithNativeQuery() {
		return queryExecutor.fetchListWithNativeQuery(Instructor.FIND_ALL_INSTRUCTORS, null, Instructor.class);
	}

	public List<Object[]> findNameAndSalaryHavingSalaryGreterThan(BigDecimal salary) {
		return queryExecutor.fetchList("findNameAndSalaryHavingSalaryGreterThan",
				CollectionUtils.mapOf("salary", salary), Object[].class);
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
				CollectionUtils.mapOf("monthlySalaryMin", monthlySalaryMin, "monthlySalaryMax", monthlySalaryMax),
				Long.class);
	}

	public List<Object[]> countHavingFatherName(BigDecimal salary) {
		return queryExecutor.fetchList("countHavingFatherName", CollectionUtils.mapOf("salary", salary),
				Object[].class);
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

	public void updateInstructorSalaryHavingId(int id, BigDecimal salary) {
		queryExecutor.execute("updateInstructorSalaryHavingId", CollectionUtils.mapOf("id", id, "salary", salary));
	}

	public void updateInstructorSalaryHavingIdsIn(List<Integer> ids, BigDecimal salary) {
		queryExecutor.execute("updateInstructorSalaryHavingIdsIn", CollectionUtils.mapOf("ids", ids, "salary", salary));
	}

	public void updateInstructorSalaryHavingFatherNameLike(String fatherName, BigDecimal salary) {
		queryExecutor.executeNativeQuery(Instructor.UPDATE_INSTRUCTORS_HAVING_FATHERNAME_LIKE,
				CollectionUtils.mapOf("fatherName", fatherName, "salary", salary));
	}

	public void deleteInstructorHavingId(int id) {
		queryExecutor.execute("deleteInstructorHavingId", Collections.singletonMap("id", id));
	}

	public void deleteInstructorHavingIdsIn(List<Integer> ids) {
		queryExecutor.execute("deleteInstructorHavingIdsIn", Collections.singletonMap("ids", ids));
	}

	// Native queries
	public void updateInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan(String fatherName,
			BigDecimal salaryToUpdate, BigDecimal salaryGreaterThan) {
		queryExecutor.executeNativeQuery(Instructor.UPDATE_INSTRUCTORS_HAVING_FATHERNAME_LIKE_SALARY_GREATER_THAN,
				CollectionUtils.mapOf("fatherName", fatherName, "salary", salaryToUpdate, "selectSalary",
						salaryGreaterThan));
	}

	public void deleteInstructorHavingFatherNameLike(String fatherName) {
		queryExecutor.executeNativeQuery(Instructor.DELETE_INSTRUCTORS_HAVING_FATHERNAME_LIKE,
				CollectionUtils.mapOf("fatherName", fatherName));
	}

	public void deleteInstructorHavingFatherNameLikeAndMonthlySalaryGreaterThan(String fatherName,
			BigDecimal salaryGreaterThan) {
		queryExecutor.executeNativeQuery(Instructor.DELETE_INSTRUCTORS_HAVING_FATHERNAME_LIKE_SALARY_GREATER_THAN,
				CollectionUtils.mapOf("fatherName", fatherName, "selectSalary", salaryGreaterThan));
	}

	public void deleteAllInstructors(List<Integer> ids) {
		queryExecutor.executeNativeQuery(Instructor.DELETE_INSTRICTORS_HAVING_IDS, CollectionUtils.mapOf("ids", ids));
	}

	// In separate Transaction
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
