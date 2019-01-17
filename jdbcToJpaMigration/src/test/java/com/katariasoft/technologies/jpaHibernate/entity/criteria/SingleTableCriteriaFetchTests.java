package com.katariasoft.technologies.jpaHibernate.entity.criteria;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CriteriaUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleTableCriteriaFetchTests {
	@Autowired
	private CriteriaUtils criteriaUtils;
	@Autowired
	private QueryExecutor queryExecutor;

	@Test
	public void fetchAllInstructors() {
		CriteriaQuery<Instructor> cq = criteriaUtils.criteriaQuery(Instructor.class);
		printResultList(cq.select(cq.from(Instructor.class)));
	}

	@Test
	public void findAllHavingSalaryGreaterThan() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root).where(cb.greaterThan(root.get(Instructor_.salary), BigDecimal.valueOf(30000))));
	}

	@Test
	public void findAllHavingAddressLike() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root).where(cb.like(root.get(Instructor_.address), "%#1074%")));
	}

	@Test
	public void findAllHavingFatherNameLike() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root).where(cb.like(root.get(Instructor_.fatherName), "%Nare%")));

	}

	@Test
	public void findAllHavingFatherNameNotLike() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root).where(cb.notLike(root.get(Instructor_.fatherName), "%Nare%")));
	}

	@Test
	public void findAllHavingAddressAndFatherNameLike() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root)
				.where(cb.greaterThan(root.get(Instructor_.salary), BigDecimal.ZERO),
						cb.like(root.get(Instructor_.fatherName), "%nare%"))
				.orderBy(cb.asc(root.get(Instructor_.birthDateTime)), cb.desc(root.get(Instructor_.name))));

	}

	@Test
	public void findAllHavingAddressAndFatherNameLikeOrSalaryLessThan() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root)
				.where(cb.or(
						cb.and(cb.greaterThan(root.get(Instructor_.salary), BigDecimal.ZERO),
								cb.like(root.get(Instructor_.fatherName), "%nare%")),
						cb.lessThan(root.get(Instructor_.salary), BigDecimal.valueOf(20000))))
				.orderBy(cb.asc(root.get(Instructor_.birthDateTime)), cb.asc(root.get(Instructor_.name))));

	}

	@Test
	public void findAllOrderByBirthDateTimeDesc() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root).orderBy(cb.desc(root.get(Instructor_.birthDateTime))));
	}

	@Test
	public void findAllBornBetweenBirthDateTimesOrderByBirthDateTimeDesc() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root)
				.where(cb.between(root.get(Instructor_.birthDateTime), Instant.parse("1970-08-15T16:30:30.000001Z"),
						Instant.parse("1993-09-19T14:27:29.999999Z")))
				.orderBy(cb.desc(root.get(Instructor_.birthDateTime))));

	}

	@Test
	public void findAllHavingIdsIn() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		In<Integer> in = cb.in(root.get(Instructor_.id));
		Arrays.asList(1, 2, 3).forEach(i -> in.value(i));
		printResultList(cq.select(root).where(in));
	}

	@Test
	public void findAllHavingIdsNotIn() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		In<Integer> in = cb.in(root.get(Instructor_.id));
		Arrays.asList(1, 2, 3).forEach(i -> in.value(i));
		printResultList(cq.select(root).where(cb.not(in)));
	}

	@Test
	public void findAllNotBornBetweenBirthDateTimesOrderByBirthDateTimeDesc() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root)
				.where(cb.not(cb.between(root.get(Instructor_.birthDateTime),
						Instant.parse("1970-08-15T16:30:30.000001Z"), Instant.parse("1993-09-19T14:27:29.999999Z"))))
				.orderBy(cb.desc(root.get(Instructor_.birthDateTime))));
	}

	@Test
	public void findNameAndSalaryHavingSalaryGreterThan() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.multiselect(root.get(Instructor_.name), root.get(Instructor_.salary))
				.where(cb.greaterThan(root.get(Instructor_.salary), BigDecimal.valueOf(75000))));
	}

	@Test
	public void findDistinctFatherName() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.select(root.get(Instructor_.fatherName)).distinct(true));
	}

	@Test
	public void countHavingSalaryBetween() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printValue((cq.select(cb.count(root))
				.where(cb.between(root.get(Instructor_.salary), BigDecimal.ZERO, BigDecimal.valueOf(100000.00)))));
	}

	// TODO
	@Test
	public void findMinSalary() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printValue(cq.select(cb.min(root.get(Instructor_.salary))));
	}

	@Test
	public void findMaxSalary() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<BigDecimal> cq = cb.createQuery(BigDecimal.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printValue(cq.select(cb.max(root.get(Instructor_.salary))));
	}

	@Test
	public void calculateAverageSalary() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Instructor> root = cq.from(Instructor.class);
		printValue(cq.select(cb.avg(root.get(Instructor_.salary))));
	}

	@Test
	public void countHavingFatherName() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
		Root<Instructor> root = cq.from(Instructor.class);
		printResultList(cq.multiselect(root.get(Instructor_.fatherName), cb.count(root)).groupBy(root.get(Instructor_.fatherName))
				.having(cb.gt(cb.count(root), 0)));
	}

	private <T> void printResultList(CriteriaQuery<T> criteriaQuery) {
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForCriteriaQuery(criteriaQuery));
	}

	private <T> void printValue(CriteriaQuery<T> criteriaQuery) {
		System.out.println(queryExecutor.fetchValueForCriteriaQuery(criteriaQuery));
	}

}
