package com.katariasoft.technologies.jpaHibernate.entity.fetch.entitygraph.dynamic;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;

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
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityGraphUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiInstructorsDynamicEntityGrpahTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProof() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof);
			TypedQuery<Instructor> query = em.createQuery("select i from Instructor i ", Instructor.class)
					.setHint(EntityGraphUtils.FETCH_GRAPH, instructorGraph);
			List<Instructor> instructors = query.getResultList();
			if (Objects.nonNull(instructors))
				instructors.forEach(instructor -> {
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
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofAndVehicles() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles);
			TypedQuery<Instructor> query = em.createQuery("select i from Instructor i ", Instructor.class)
					.setHint(EntityGraphUtils.FETCH_GRAPH, instructorGraph);
			List<Instructor> instructors = query.getResultList();
			if (Objects.nonNull(instructors))
				instructors.forEach(instructor -> {
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
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofAndVehiclesAndStudents() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles, Instructor_.students);
			TypedQuery<Instructor> query = em.createQuery("select i from Instructor i ", Instructor.class)
					.setHint(EntityGraphUtils.FETCH_GRAPH, instructorGraph);
			List<Instructor> instructors = query.getResultList();
			if (Objects.nonNull(instructors))
				instructors.forEach(instructor -> {
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
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofAndVehiclesAndStudentsAndTheirInstructors() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles);
			Subgraph<Student> studentSubgraph = instructorGraph.addSubgraph(Instructor_.STUDENTS);
			studentSubgraph.addAttributeNodes(Student_.instructors);
			TypedQuery<Instructor> query = em.createQuery("select i from Instructor i ", Instructor.class)
					.setHint(EntityGraphUtils.FETCH_GRAPH, instructorGraph);
			List<Instructor> instructors = query.getResultList();
			if (Objects.nonNull(instructors))
				instructors.forEach(instructor -> {
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
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProofAndVehiclesAndStudentsAndTheirInstructorsAndVehicles() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles);
			Subgraph<Student> studentSubgraph = instructorGraph.addSubgraph(Instructor_.STUDENTS);
			studentSubgraph.addAttributeNodes(Student_.instructors);
			studentSubgraph.addAttributeNodes(Student_.vehicles);
			TypedQuery<Instructor> query = em.createQuery("select i from Instructor i ", Instructor.class)
					.setHint(EntityGraphUtils.FETCH_GRAPH, instructorGraph);
			List<Instructor> instructors = query.getResultList();
			if (Objects.nonNull(instructors))
				instructors.forEach(instructor -> {
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
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstrctrsIdProofVehiclesStudentsTheirInstructorsVehiclesAndTheirDocuments() {
		doInTransaction(() -> {
			EntityGraph<Instructor> instructorGraph = em.createEntityGraph(Instructor.class);
			instructorGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles);
			Subgraph<Student> studentSubgraph = instructorGraph.addSubgraph(Instructor_.STUDENTS);
			studentSubgraph.addAttributeNodes(Student_.instructors);
			Subgraph<Vehicle> vehicleSubgraph = studentSubgraph.addSubgraph(Student_.VEHICLES);
			vehicleSubgraph.addAttributeNodes(Vehicle_.documents);
			TypedQuery<Instructor> query = em.createQuery("select i from Instructor i", Instructor.class)
					.setHint(EntityGraphUtils.FETCH_GRAPH, instructorGraph);
			List<Instructor> instructors = query.getResultList();
			if (Objects.nonNull(instructors))
				instructors.forEach(instructor -> {
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
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
