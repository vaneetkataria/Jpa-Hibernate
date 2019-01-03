package com.katariasoft.technologies.jpaHibernate.entity.namedQueries;

import static com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters.listDataPrinter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.dao.InstructorRepository;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle.InstructorLifeCycleRealiser;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NamedQueriesAndEntityLifeCycleTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private InstructorRepository instructorDao;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;
	@Autowired
	private InstructorLifeCycleRealiser realiser;

	@Test
	@Rollback(false)
	public void fetchAndPrintAllInstructors() {
		doInTransaction(() -> {
			List<Instructor> instructors = instructorDao.fetchAllInstructors();
			listDataPrinter.accept(instructors);
			if (Objects.nonNull(instructors))
				instructors.stream().forEach(realiser.updateRealiser());
		});
	}

	@Test
	@Rollback(false)
	public void findNameAndSalaryHavingSalaryGreterThan() {
		doInTransaction(() -> {
			List<Object[]> instructors = instructorDao
					.findNameAndSalaryHavingSalaryGreterThan(BigDecimal.valueOf(100000));
			listDataPrinter.accept(instructors);
		});
	}

	@Test
	@Rollback(false)
	public void findDistinctFatherName() {
		doInTransaction(() -> {
			List<String> instructors = instructorDao.findDistinctFatherName();
			listDataPrinter.accept(instructors);
		});
	}

	@Test
	@Rollback(false)
	public void countHavingSalaryBetween() {
		doInTransaction(() -> {
			BigDecimal salaryMin = BigDecimal.valueOf(10000);
			BigDecimal salaryMax = BigDecimal.valueOf(120000);
			long count = instructorDao.countHavingSalaryBetween(salaryMin, salaryMax);
			System.out.println("Number of Instructors having salary between " + salaryMin + " and salary max "
					+ salaryMax + " are " + count);
		});
	}

	@Test
	@Rollback(false)
	public void countHavingFatherName() {
		doInTransaction(() -> {
			List<Object[]> objs = instructorDao.countHavingFatherName(BigDecimal.valueOf(0));
			System.out.println(objs);
		});
	}

	@Test
	@Rollback(false)
	public void calculateAverageSalary() {
		doInTransaction(() -> {
			System.out.println("Average salary is : " + instructorDao.calculateAverageSalary());
		});
	}

	@Test
	@Rollback(false)
	public void minSalary() {
		doInTransaction(() -> {
			System.out.println("Min salary is : " + instructorDao.findMinSalary());
		});
	}

	@Test
	@Rollback(false)
	public void maxSalary() {
		doInTransaction(() -> {
			System.out.println("Max salary is : " + instructorDao.findMaxSalary());
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
