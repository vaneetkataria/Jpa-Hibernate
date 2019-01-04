package com.katariasoft.technologies.jpaHibernate.entity.nativeQueries;

import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import com.katariasoft.technologies.jpaHibernate.college.data.dao.InstructorRepository;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueryTests {

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void updateInstructorSalaryHavingFatherNameLike() {
		doInTransaction(() -> instructorRepository.updateInstructorSalaryHavingFatherNameLike("Nares",
				BigDecimal.valueOf(50000)));
	}

	@Test
	@Rollback(false)
	public void updateInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan() {
		doInTransaction(
				() -> instructorRepository.updateInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan("giri",
						BigDecimal.valueOf(789), BigDecimal.valueOf(0)));
	}

	@Test
	@Rollback(false)
	public void deleteInstructorHavingFatherNameLike() {
		doInTransaction(() -> instructorRepository.deleteInstructorHavingFatherNameLike("giri"));
	}

	@Test
	@Rollback(false)
	public void deleteInstructorHavingFatherNameLikeAndMonthlySalaryGreaterThan() {
		doInTransaction(() -> instructorRepository
				.deleteInstructorHavingFatherNameLikeAndMonthlySalaryGreaterThan("nare", BigDecimal.valueOf(0)));
	}

	@Test
	@Rollback(false)
	public void deleteAllInstructors() {
		doInTransaction(() -> instructorRepository.deleteAllInstructors(Arrays.asList(1, 2)));
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
