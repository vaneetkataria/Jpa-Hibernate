package com.katariasoft.technologies.jpaHibernate.entity.associations;

import java.time.Instant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToOneTests {

	@PersistenceContext
	private EntityManager em;

	// @Test
	@Transactional
	@Rollback(false)
	public void fetchInstructor() {
		Instructor instructor = em.find(Instructor.class, 1);
		System.out.println(instructor);
		System.out.println(instructor.getIdProof());
	}

	// @Test
	@Transactional
	@Rollback(false)
	public void fetchIdProof() {
		IdProof idProof = em.find(IdProof.class, 1);
		System.out.println(idProof);
		System.out.println(idProof.getInstructor());
	}

	@Test
	@Transactional
	@Rollback(false)
	public void orphanRemovalInstructor() {
		Instructor instructor = em.find(Instructor.class, 1);
		em.remove(instructor);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void setIdProofAsNullInInstructor() {
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setIdProof(null);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void orphanRemovalIdProof() {
		IdProof idProof = em.find(IdProof.class, 1);
		em.remove(idProof);
	}

	//
	@Test
	@Transactional
	@Rollback(false)
	public void setInstructorAsNullInIdProof() {
		IdProof idProof = em.find(IdProof.class, 4);
		idProof.setInstructor(null);
	}

	// For Bi directional one to one : for both objects crud fqdm rules applied
	// successfully,
	// For Bi Directional owner side object also agreed with crud fqdm rules .
	// For Bi Directional non owner side object didn't agreed with crud fqdm rules .
	// Hence to delete them
	// just set their reference as null in parent .
	@Test
	@Transactional
	@Rollback(false)
	public void crudfqdm() {
		IdProof idProof = em.find(IdProof.class, 3);
		// Instructor instructor = idProof.getInstructor();
		// Instructor instructor = em.find(Instructor.class, 3);
		// IdProof idProof = instructor.getIdProof();
		// instructor.setAddress("vaneet4");
		idProof.setAddress("vaneet4");
		em.remove(idProof);
		// em.persist(idProof);
		// instructor.setIdProof(null);
		// em.remove(idProof);
		// em.flush();
		// em.detach(idProof);
		// em.persist(instructor);
		// em.merge(idProof);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void createInstructor() {
		Instructor instructor = EntityUtils.singleInstructorSupplier().get();
		em.persist(instructor);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void createIdProof() {
		IdProof idProof = new IdProof("a", "a", "a", "a", "a", 'a', true, Instant.now(), Instant.now());
		em.persist(idProof);
	}

}
