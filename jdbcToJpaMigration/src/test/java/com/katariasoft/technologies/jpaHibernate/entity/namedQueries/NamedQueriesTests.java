package com.katariasoft.technologies.jpaHibernate.entity.namedQueries;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.katariasoft.technologies.jpaHibernate.college.data.dao.InstructorRepository;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;

import static com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters.listDataPrinter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NamedQueriesTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private InstructorRepository instructorDao;

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void fetchAndPrintAllInstructors() {
		try {
			List<Instructor> instructors = instructorDao.fetchAllInstructors();
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findNameAndSalaryHavingSalaryGreterThan() {
		try {
			List<Object[]> instructors = instructorDao
					.findNameAndSalaryHavingSalaryGreterThan(BigDecimal.valueOf(100000));
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findAllHavingAddressLike() {
		try {
			List<Instructor> instructors = instructorDao.findAllHavingAddressLike("Block");
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findAllHavingFatherNameLike() {
		try {
			List<Instructor> instructors = instructorDao.findAllHavingFatherNameLike("giri");
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findAllOrderByBirthDateTimeDesc() {
		try {
			List<Instructor> instructors = instructorDao.findAllOrderByBirthDateTimeDesc();
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findAllHavingSalaryGreaterThan() {
		try {
			List<Instructor> instructors = instructorDao.findAllHavingSalaryGreaterThan(BigDecimal.valueOf(10000));
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findAllHavingSalaryGreaterThanBigQuery() {
		try {
			List<Instructor> instructors = instructorDao
					.findAllHavingSalaryGreaterThanBigQuery(BigDecimal.valueOf(10000));
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findDistinctFatherName() {
		try {
			List<String> instructors = instructorDao.findDistinctFatherName();
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void countHavingSalaryBetween() {
		try {
			BigDecimal salaryMin = BigDecimal.valueOf(10000);
			BigDecimal salaryMax = BigDecimal.valueOf(120000);
			long count = instructorDao.countHavingSalaryBetween(salaryMin, salaryMax);
			System.out.println("Number of Instructors having salary between " + salaryMin + " and salary max "
					+ salaryMax + " are " + count);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void countHavingFatherName() {
		try {
			List<Object[]> objs = instructorDao.countHavingFatherName(BigDecimal.valueOf(0));
			System.out.println(objs);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void calculateAverageSalary() {
		try {
			System.out.println("Average salary is : " + instructorDao.calculateAverageSalary());
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void minSalary() {
		try {
			System.out.println("Min salary is : " + instructorDao.findMinSalary());
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void maxSalary() {
		try {
			System.out.println("Max salary is : " + instructorDao.findMaxSalary());
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findAllBornBetweenBirthDateTimesOrderByBirthDateTimeDesc() {
		try {
			Instant birthDateTimeStart = Instant.parse("1970-08-15T16:30:30.000001Z");
			Instant birthDateTimeEnd = Instant.parse("1993-09-19T14:27:29.999999Z");
			List<Instructor> instructors = instructorDao
					.findAllBornBetweenBirthDateTimesOrderByBirthDateTimeDesc(birthDateTimeStart, birthDateTimeEnd);
			DataPrinters.print(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void findAllNotBornBetweenBirthDateTimesOrderByBirthDateTimeDesc() {
		try {
			Instant birthDateTimeStart = Instant.parse("1970-08-15T16:30:30.000001Z");
			Instant birthDateTimeEnd = Instant.parse("1993-09-19T14:27:29.999999Z");
			List<Instructor> instructors = instructorDao
					.findAllNotBornBetweenBirthDateTimesOrderByBirthDateTimeDesc(birthDateTimeStart, birthDateTimeEnd);
			listDataPrinter.accept(instructors);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void updateInstructorSalaryHavingId() {
		instructorDao.updateInstructorSalaryHavingId(1, BigDecimal.valueOf(2500000));
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void updateInstructorSalaryHavingIdsIn() {
		instructorDao.updateInstructorSalaryHavingIdsIn(Arrays.asList(1, 2, 3), BigDecimal.valueOf(2500000));
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void deleteInstructorHavingId() {
		instructorDao.deleteInstructorHavingId(1);
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void deleteInstructorHavingIdsIn() {
		instructorDao.deleteInstructorHavingIdsIn(Arrays.asList(1, 2, 3));
	}

	// //@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void fetchAndDeleteAllInstructorsOneByOne() {
		try {
			List<Instructor> instructors = instructorDao.fetchAllInstructors();
			if (Objects.nonNull(instructors) && !instructors.isEmpty())
				instructors.stream().forEach(e -> {
					em.remove(e);
					em.flush();
				});
			instructors.stream().forEach(e -> em.detach(e));
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// //@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void fetchAndChnageAllInstructorsBatch() {
		try {
			List<Instructor> instructors = instructorDao.fetchAllInstructors();
			List<Integer> instructorsToDelete = null;
			if (Objects.nonNull(instructors) && !instructors.isEmpty())
				instructorsToDelete = instructors.stream().limit(4).skip(2).map(Instructor::getId)
						.collect(Collectors.toList());
			instructorDao.deleteAllInstructors(instructorsToDelete);
			instructors.stream().forEach(i -> em.detach(i));
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

}
