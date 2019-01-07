package com.katariasoft.technologies.jpaHibernate.entity.associations;

import static com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils.*;

import java.util.Objects;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyCrudTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void createTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
			instructor.addIdProof(SINGLE_ID_PROOF_PROVIDER.apply("8760_5152_3510"));
			instructor.addVehicles(MULTIPLE_VEHICLES_PROVIDER.apply("HR02 U570"));
			instructor.addStudents(MULTIPLE_STUDENTS_PROVIDER.get());
			em.persist(instructor);
		});
	}

	@Test
	@Rollback(false)
	public void createNewInExistingTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 4);
			instructor.addVehicles(MULTIPLE_VEHICLES_PROVIDER.apply("HR02 U570"));
			instructor.addStudents(MULTIPLE_STUDENTS_PROVIDER.get());
		});
	}

	@Test
	@Rollback(false)
	public void readTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 6);
			IdProof idProof = instructor.getIdProof();
			Set<Vehicle> vehicles = instructor.getVehicles();
			Set<Student> students = instructor.getStudents();
			System.out.println(instructor);
			System.out.println(idProof);
			if (Objects.nonNull(vehicles))
				vehicles.forEach(v -> System.out.println(v.getVehicleNumber()));
			if (Objects.nonNull(students))
				students.forEach(s -> System.out.println(s.getName()));

		});
	}

	@Test
	@Rollback(false)
	public void updateTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 6);
			instructor.setAddress("Updated:" + instructor.getAddress());
			instructor.getIdProof().setAddress("Updated:" + instructor.getIdProof().getAddress());
			instructor.getVehicles().forEach(v -> v.setVehicleNumber("Updated:" + v.getVehicleNumber()));
			instructor.getStudents().forEach(s -> s.setName("Updated:" + s.getName()));
		});
	}

	@Test
	@Rollback(false)
	public void removeTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 3);
			em.remove(instructor);

		});
	}

	@Test
	@Rollback(false)
	public void orphanRemovalTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 4);
			instructor.orphaniseIdProof();
			instructor.orphaniseVehicles();
			Set<Student> students = instructor.getStudents();
			if (Objects.nonNull(students))
				instructor.removeStudent(students.stream().findFirst().get());
			// Not recommended to set for ManyToMany
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
