package com.katariasoft.technologies.jpaHibernate.entity.associations;

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

	// @Test
	@Transactional
	@Rollback(false)
	public void orphanRemovalInstructor() {
		Instructor instructor = em.find(Instructor.class, 1);
		em.remove(instructor);
	}

	// @Test
	@Transactional
	@Rollback(false)
	public void setIdProofAsNullInInstructor() {
		Instructor instructor = em.find(Instructor.class, 2);
		instructor.setIdProof(null);
	}

	// @Test
	@Transactional
	@Rollback(false)
	public void orphanRemovalIdProof() {
		IdProof idProof = em.find(IdProof.class, 3);
		em.remove(idProof);
	}

	// @Test
	@Transactional
	@Rollback(false)
	public void setInstructorAsNullInIdProof() {
		IdProof idProof = em.find(IdProof.class, 4);
		idProof.setInstructor(null);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void crudfqdm() {
		Instructor instructor = em.find(Instructor.class, 3);
		IdProof idProof = instructor.getIdProof();
		instructor.setAddress("set in crudfqdm6");
		idProof.setAddress("set in fqdm6");
		em.remove(idProof);
		em.flush();
		// em.detach(instructor);

		em.persist(instructor);
		// em.merge(idProof);
	}

}
