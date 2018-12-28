package com.katariasoft.technologies.jpaHibernate.entity.nativeQueries;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.dao.InstructorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueryTests {

	@Autowired
	private InstructorRepository instructorRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void updateInstructorSalaryHavingFatherNameLike() {
		instructorRepository.updateInstructorSalaryHavingFatherNameLike("Nares", BigDecimal.valueOf(50000));
	}

	@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void updateInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan() {
		instructorRepository.updateInstructorSalaryHavingFatherNameLikeAndMonthlySalaryGreaterThan("giri",
				BigDecimal.valueOf(789), BigDecimal.valueOf(0));
	}

	@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void deleteInstructorHavingFatherNameLike() {
		instructorRepository.deleteInstructorHavingFatherNameLike("giri");
	}

	@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void deleteInstructorHavingFatherNameLikeAndMonthlySalaryGreaterThan() {
		instructorRepository.deleteInstructorHavingFatherNameLikeAndMonthlySalaryGreaterThan("nare",
				BigDecimal.valueOf(0));
	}

	@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void deleteAllInstructors() {
		instructorRepository.deleteAllInstructors(Arrays.asList(1, 2));
	}

}
