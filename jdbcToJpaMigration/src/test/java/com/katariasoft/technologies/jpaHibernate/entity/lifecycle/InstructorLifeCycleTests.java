package com.katariasoft.technologies.jpaHibernate.entity.lifecycle;

import static org.junit.Assert.assertTrue;

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
import com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle.InstructorLifeCycleRealiser;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstructorLifeCycleTests {

	private static final String FETCH_FROM_SAME_TARNSACTION = "FETCH_FROM_SAME_TARNSACTION";
	private static final String FETCH_FROM_DIFFERENT_TARNSACTION = "FETCH_FROM_DIFFERENT_TARNSACTION";
	private static final String TRANSIENT = "TRANSIENT";
	private static final String DETACHED = "DETACHED";
	private String executionCase = DETACHED;

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private InstructorRepository instructorDao;
	@Autowired
	private InstructorLifeCycleRealiser realiser;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void ruTest() {
		doInTransaction(() -> realiser.updateRealiser().accept(getInstructorForTest()));
		// ruRealiser
		Instructor instructor = em.find(Instructor.class, 1);
		assertTrue(instructor.getAddress().equalsIgnoreCase("ruRealiser")
				&& instructor.getFatherName().equalsIgnoreCase("ruRealiser")
				&& instructor.getMotherName().equalsIgnoreCase("ruRealiser"));

	}

	@Test
	@Rollback(false)
	public void rudTest() {
		doInTransaction(() -> realiser.updateAndDeleteRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void rud_Flush_Test() {
		doInTransaction(() -> realiser.updateDeleteFlushRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void ru_Flush_Detach_Test() {
		doInTransaction(() -> realiser.updateFlushDetachRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void removingDetachedEntityTest() {
		doInTransaction(() -> realiser.removingDetachedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingDetachedEntityTest() {
		doInTransaction(() -> realiser.persistingDetachedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingDetachedEntityTest() {
		doInTransaction(() -> realiser.mergingDetachedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedAndFlushedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedAndFlushedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingRemovedEntityTest() {
		doInTransaction(() -> realiser.mergingRemovedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void mergedEntityTest() {
		doInTransaction(() -> realiser.mergedEntityRealiser().accept(getInstructorForTest()));
	}

	@Test
	@Rollback(false)
	public void mergedEntityTest_AlreadyPresentInPC() {
		doInTransaction(() -> realiser.mergedEntityRealiser_AlreadyPresentInPC().accept(getInstructorForTest()));
	}

	private Instructor getInstructorForTest() {
		switch (executionCase) {
		case TRANSIENT:
			return EntityUtils.singleInstructorSupplier().get();
		case DETACHED: {
			Instructor instructor = EntityUtils.singleInstructorSupplier().get();
			instructor.setId(1);
			return instructor;
		}
		case FETCH_FROM_DIFFERENT_TARNSACTION:
			return instructorDao.getInstructor(1);
		case FETCH_FROM_SAME_TARNSACTION:
		default:
			return em.find(Instructor.class, 1);
		}
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
