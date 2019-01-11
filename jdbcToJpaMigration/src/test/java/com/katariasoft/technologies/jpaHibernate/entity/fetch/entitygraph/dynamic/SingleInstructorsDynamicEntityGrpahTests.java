package com.katariasoft.technologies.jpaHibernate.entity.fetch.entitygraph.dynamic;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student_;
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
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addSubgraph(Instructor_.idProof);
			Instructor instructor = em.find(Instructor.class, 1,
					Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH, instructorGraph));
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
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles);
			Instructor instructor = em.find(Instructor.class, 1,
					Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH, instructorGraph));
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

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofVehiclesAndStudents() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles, Instructor_.students);
			Instructor instructor = em.find(Instructor.class, 1,
					Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH, instructorGraph));
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

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofVehiclesAndStudentsByOnlyAddSubgraphs() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addSubgraph(Instructor_.idProof);
			instructorGraph.addSubgraph(Instructor_.vehicles);
			instructorGraph.addSubgraph(Instructor_.students);

			Instructor instructor = em.find(Instructor.class, 1,
					Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH, instructorGraph));
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

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofVehiclesStudentsAndTheirInstructors() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles);
			Subgraph<Student> studentSubgraph = instructorGraph.addSubgraph(Instructor_.STUDENTS);
			studentSubgraph.addSubgraph(Student_.instructors);
			Instructor instructor = em.find(Instructor.class, 1,
					Collections.singletonMap(EntityGraphUtils.FETCH_GRAPH, instructorGraph));
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
