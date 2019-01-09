package com.katariasoft.technologies.jpaHibernate.entity.batch;

import static com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

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
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchCrudTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;
	@Autowired
	private InstructorRepository instructorRepository;

	@Test
	@Rollback(false)
	public void multiInstructorCreateTest() {
		doInTransaction(() -> {
			List<Instructor> instructors = MULTI_INSTRUCTOR_PROVIDER.get();
			IntStream.range(0, instructors.size()).forEach(i -> {
				Instructor instructor = instructors.get(i);
				instructor.addIdProof(SINGLE_ID_PROOF_PROVIDER.apply("8760_5152_3510" + i + "l"));
				instructor.addVehicles(MULTIPLE_VEHICLES_PROVIDER.apply("HR02 U570" + i + "l"));
				instructor.addStudents(MULTIPLE_STUDENTS_PROVIDER.get());
				em.persist(instructor);

				if ((i + 1) % 30 == 0) {
					em.flush();
					em.clear();
				}
			});
		});
	}

	@Test
	@Rollback(false)
	public void updateTest() {
		doInTransaction(() -> {
			List<Instructor> instructors = instructorRepository.findAllHavingSalaryGreaterThan(BigDecimal.ZERO);
			if (Objects.nonNull(instructors))
				IntStream.range(0, instructors.size()).forEach(i -> {
					Instructor instructor = instructors.get(i);
					instructor.setAddress("Updated5:" + instructor.getAddress());
					instructor.getIdProof().setAddress("Updated5:" + instructor.getIdProof().getAddress());
					instructor.getVehicles().forEach(v -> v.setVehicleNumber("Updated5:" + v.getVehicleNumber()));
					instructor.getStudents().forEach(s -> s.setName("Updated5:" + s.getName()));
				});
		});
	}

	@Test
	@Rollback(false)
	public void removeTest() {
		doInTransaction(() -> {
			List<Instructor> instructors = instructorRepository.fetchAllInstructors();
			if (Objects.nonNull(instructors)) {
				instructors.forEach(i -> {
					i.orphaniseIdProof();
				});
				em.flush();
				instructors.forEach(i -> {
					i.orphaniseVehicles();
				});
				em.flush();
				instructors.forEach(i -> {
					i.removeAllStudents();
				});
				em.flush();
			}
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
