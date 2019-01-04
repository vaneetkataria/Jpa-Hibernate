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
			instructors.stream().forEach(oa -> {
				oa[0] = "Changed After fetch.";
				oa[1] = BigDecimal.valueOf(0.0);
			});
			listDataPrinter.accept(instructors);
		});
	}

	@Test
	@Rollback(false)
	public void findDistinctFatherName() {
		doInTransaction(() -> {
			List<String> instructors = instructorDao.findDistinctFatherName();
			listDataPrinter.accept(instructors);
			instructors.stream().peek(System.out::println).forEach(name -> {
				name = name + "1";
				System.out.println(name);
			});
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
			count = 15000;
		});
	}

	@Test
	@Rollback(false)
	public void countHavingFatherName() {
		doInTransaction(() -> {
			List<Object[]> objs = instructorDao.countHavingFatherName(BigDecimal.valueOf(0));
			System.out.println(objs);
			objs.stream().forEach(oa -> {
				oa[0] = "Changed";
				oa[1] = 15000;
			});
			objs.stream().forEach(oa -> {
				System.out.println("Name is :" + oa[0]);
				System.out.println("count is :" + oa[1]);
			});
		});
	}

	@Test
	@Rollback(false)
	public void calculateAverageSalary() {
		doInTransaction(() -> {
			double avgSalary = instructorDao.calculateAverageSalary();
			System.out.println("Average salary is : " + avgSalary);
			avgSalary = 0.0d;
			System.out.println("Average salary is : " + avgSalary);
		});
	}

	@Test
	@Rollback(false)
	public void minSalary() {
		doInTransaction(() -> {
			BigDecimal minSalary = instructorDao.findMinSalary();
			System.out.println("Min salary is : " + minSalary);
			minSalary = BigDecimal.ZERO;
			System.out.println("Min salary is : " + minSalary);
		});
	}

	@Test
	@Rollback(false)
	public void maxSalary() {
		doInTransaction(() -> {
			BigDecimal maxSalary = instructorDao.findMaxSalary();
			System.out.println("Max salary is : " + maxSalary);
			maxSalary = BigDecimal.ZERO;
			System.out.println("Min salary is : " + maxSalary);
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
