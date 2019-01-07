package com.katariasoft.technologies.jpaHibernate.entity.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;
import static com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils.SINGLE_INSTRUCTOR_PROVIDER;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityLifeCycle_FromCreate_Tests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void cruTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
			// create
			em.persist(instructor);
			// read
			Instructor instructorFromDb = em.find(Instructor.class, 1);
			// update
			instructor.setAddress("Pankhon wali Gali update 2.");
			instructor.setAddress("Pankhon wali Gali update 3.");
			instructor.setMotherName("Neelam Kataria.");
			instructor.setFatherName("Naresh Kataria.");
		});
	}

	@Test
	@Rollback(false)
	public void crudTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void crud_Flush_Test() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void cru_Flush_Detach_Test() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void removingDetachedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void persistingDetachedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void mergingDetachedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void detachingRemovedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void persistingRemovedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void detachingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void persistingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});

	}

	@Test
	@Rollback(false)
	public void mergingRemovedEntityTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
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

		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
