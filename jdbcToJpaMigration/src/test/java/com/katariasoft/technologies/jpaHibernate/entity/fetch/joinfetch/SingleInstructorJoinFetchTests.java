package com.katariasoft.technologies.jpaHibernate.entity.fetch.joinfetch;

import java.util.Collections;
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
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleInstructorJoinFetchTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;
	@Autowired
	private QueryExecutor queryExecutor;

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
	public void fetchInstructorIdProof() {
		doInTransaction(() -> {
			Instructor instructor = queryExecutor.fetchSingleResultForJpqlQuery(
					"select i from Instructor i join fetch i.idProof id where i.id = :id",
					Collections.singletonMap("id", 1), Instructor.class);
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
	public void fetchInstructorVehiclesIdProof() {
		doInTransaction(() -> {
			Instructor instructor = queryExecutor.fetchSingleResultForJpqlQuery(
					"select i from Instructor i join fetch i.idProof id join fetch i.vehicles v where i.id = :id",
					Collections.singletonMap("id", 1), Instructor.class);
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
	public void fetchInstructorStudentsVehiclesIdProof() {
		doInTransaction(() -> {
			Instructor instructor = queryExecutor.fetchSingleResultForJpqlQuery(
					"select i from Instructor i join fetch i.idProof id join fetch i.vehicles v join fetch i.students s where i.id = :id",
					Collections.singletonMap("id", 1), Instructor.class);
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

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
