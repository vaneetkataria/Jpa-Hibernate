package com.katariasoft.technologies.jpaHibernate.entity.fetch.entitygraph.dynamic;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javax.persistence.EntityGraph;
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
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityGraphUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleInstructorsDynamicEntityGrpahTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;
	@Autowired
	private EntityGraphUtils entityGraphUtils;

	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProof() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1, Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH,
					entityGraphUtils.createGraph(Instructor.class, "idProof")));
			if (Objects.nonNull(instructor)) {
				IdProof idProof = instructor.getIdProof();
				Set<Vehicle> vehicles = instructor.getVehicles();
				Set<Student> students = instructor.getStudents();
				System.out.println(instructor);
				System.out.println(idProof);
				if (Objects.nonNull(vehicles))
					vehicles.forEach(v -> System.out.println(v.getVehicleNumber()));
				if (Objects.nonNull(students))
					students.forEach(s -> System.out.println(s.getName()));
			}
		});
	}

	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofAndVehicles() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1, Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH,
					entityGraphUtils.createGraph(Instructor.class, "idProof", "vehicles")));
			if (Objects.nonNull(instructor)) {
				IdProof idProof = instructor.getIdProof();
				Set<Vehicle> vehicles = instructor.getVehicles();
				Set<Student> students = instructor.getStudents();
				System.out.println(instructor);
				System.out.println(idProof);
				if (Objects.nonNull(vehicles))
					vehicles.forEach(v -> System.out.println(v.getVehicleNumber()));
				if (Objects.nonNull(students))
					students.forEach(s -> System.out.println(s.getName()));
			}
		});
	}

	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofAndVehiclesAndStudents() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1, Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH,
					entityGraphUtils.createGraph(Instructor.class, "idProof", "vehicles", "students")));
			if (Objects.nonNull(instructor)) {
				IdProof idProof = instructor.getIdProof();
				Set<Vehicle> vehicles = instructor.getVehicles();
				Set<Student> students = instructor.getStudents();
				System.out.println(instructor);
				System.out.println(idProof);
				if (Objects.nonNull(vehicles))
					vehicles.forEach(v -> System.out.println(v.getVehicleNumber()));
				if (Objects.nonNull(students))
					students.forEach(s -> System.out.println(s.getName()));
			}
		});
	}

	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofAndVehiclesAndStudentsAndTheirInstructors() {
		EntityGraph instructorIdProofVehiclesStudents = em
				.getEntityGraph("graph.instructor.idProof.vehicles.students.instructors");
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1,
					Collections.singletonMap("javax.persistence.loadgraph", instructorIdProofVehiclesStudents));
			if (Objects.nonNull(instructor)) {
				IdProof idProof = instructor.getIdProof();
				Set<Vehicle> vehicles = instructor.getVehicles();
				Set<Student> students = instructor.getStudents();
				System.out.println(instructor);
				System.out.println(idProof);
				if (Objects.nonNull(vehicles))
					vehicles.forEach(v -> System.out.println(v.getVehicleNumber()));
				if (Objects.nonNull(students))
					students.forEach(s -> System.out.println(s.getName()));
			}
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
