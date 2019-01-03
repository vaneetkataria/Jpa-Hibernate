package com.katariasoft.technologies.jpaHibernate.entity.lifecycle;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
	private String executionCase = FETCH_FROM_SAME_TARNSACTION;

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private InstructorRepository instructorDao;
	@Autowired
	private InstructorLifeCycleRealiser realiser;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	// @Before
	public void createAnInstructor() {
		try {
			Instructor fromDb = getInstructorForTest();
			if (Objects.isNull(fromDb)) {
				Instructor instructor = EntityUtils.singleInstructorSupplier().get();
				instructor.setId(1);
				em.merge(instructor);
				System.out.println(instructor);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	// @After
	public void deleteAnInstructor() {
		try {
			if (TransactionAspectSupport.currentTransactionStatus().isRollbackOnly())
				throw new RuntimeException("Transaction rolled back.");
			Instructor instructor = getInstructorForTest();
			if (Objects.nonNull(instructor))
				em.remove(instructor);
		} catch (Exception e) {
			throw e;
		}

	}

	@Test
	@Rollback(false)
	public void ruTest() {
		doInTransaction(() -> realiser.updateRealiser().accept(getInstructorForTest()));
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

	// 1. Merge method looks up to database if entity with same id and version is
	// found then a new object is created for that in PC and input entity state is
	// merged into it .
	// 2. New entity becomes persistent and input entity continue remain unmanaged
	// by
	// PC
	// 3.If record is not present in db even then a new object is created and input
	// entity state is
	// merged into it .
	// 4. Merging input entity never becomes persistent .
	// 5. If an entity already present in PC then no db lookup will happen and
	// input entity will be merge into that . Input entity continue remaining
	// unmanaged .
	@Test
	@Rollback(false)
	public void mergedEntityTest() {
		doInTransaction(() -> realiser.mergedEntityRealiser().accept(getInstructorForTest()));
	}

	// 1. Merge method looks up to database if entity with same id and version is
	// found then a new object is created for that in PC and input entity state is
	// merged into it .
	// 2. New entity becomes persistent and input entity continue remain unmanaged
	// by
	// PC
	// 3.If record is not present in db even then a new object is created and input
	// entity state is
	// merged into it .
	// 4. Merging input entity never becomes persistent .
	// 5. If an entity already present in PC then no db lookup will happen and
	// input entity will be merge into that . Input entity continue remaining
	// unmanaged .
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
			return instructorDao.getInstructor(2);
		case FETCH_FROM_SAME_TARNSACTION:
		default:
			return em.find(Instructor.class, 1);
		}
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
