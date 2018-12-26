package com.katariasoft.technologies.jpaHibernate.entity.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityLifeCycle_FromCreate_Tests {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Rollback(false)
	@Transactional
	public void cruTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			instructor.setAddress("Pankhon wali Gali update 3.");
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");

		} catch (RuntimeException e) {
			throw e;
		}

	}

	@Test
	@Rollback(false)
	@Transactional
	public void crudTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			instructor.setAddress("Pankhon wali Gali update 3.");
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void crud_Flush_Test() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			instructor.setMotherName("Neelam Kataria.");
			em.flush();
			instructor.setFatherName("Naresh Kataria.");
			em.flush();
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
			em.flush();
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void cru_Flush_Detach_Test() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			instructor.setMotherName("Neelam Kataria update 2.");
			instructor.setFatherName("Naresh Kataria update 2.");
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void removingDetachedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
			em.flush();
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void persistingDetachedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			// remove any as both are same objects fetched from P.C.
			em.persist(instructor);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void mergingDetachedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("Neelam Kataria updated 2.");
			instructor.setFatherName("Naresh Kataria updated 2.");
			// remove any as both are same objects fetched from P.C.
			em.merge(instructor);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void detachingRemovedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			// remove any as both are same objects fetched from P.C.

			em.detach(instructor);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void persistingRemovedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			// remove any as both are same objects fetched from P.C.

			em.persist(instructor);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void detachingRemovedAndFlushedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			// remove any as both are same objects fetched from P.C.
			em.flush();
			em.detach(instructor);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void persistingRemovedAndFlushedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
			em.flush();
			// remove any as both are same objects fetched from P.C.

			em.persist(instructor);
		} catch (RuntimeException e) {
			throw e;
		}
	}

	@Test
	@Rollback(false)
	@Transactional
	public void mergingRemovedEntityTest() {
		try {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			em.flush();
			instructor.setAddress("Pankhon wali Gali update 3.");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("Neelam Kataria updated 2.");
			instructor.setFatherName("Naresh Kataria updated 2.");
			// remove any as both are same objects fetched from P.C.
			em.merge(instructor);
		} catch (RuntimeException e) {
			throw e;
		}
	}

}
