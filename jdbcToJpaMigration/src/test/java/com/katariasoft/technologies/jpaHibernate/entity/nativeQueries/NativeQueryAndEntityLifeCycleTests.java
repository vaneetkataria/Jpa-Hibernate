package com.katariasoft.technologies.jpaHibernate.entity.nativeQueries;

import java.util.List;

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
public class NativeQueryAndEntityLifeCycleTests {

	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;
	@Autowired
	private InstructorLifeCycleRealiser realiser;

	@Test
	@Rollback(false)
	public void fetchAllInstructors() {
		doInTransaction(() -> {
			List<Instructor> instructors = instructorRepository.fetchAllInstructorsWithNativeQuery();
			instructors.stream().forEach(realiser.updateRealiser());
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
