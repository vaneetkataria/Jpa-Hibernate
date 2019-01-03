package com.katariasoft.technologies.jpaHibernate.entity.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle.StudentLifeCycleRealiser;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentLifeCycleTests {

	private static final String FETCH_FROM_SAME_TARNSACTION = "FETCH_FROM_SAME_TARNSACTION";
	private static final String FETCH_FROM_DIFFERENT_TARNSACTION = "FETCH_FROM_DIFFERENT_TARNSACTION";
	private static final String TRANSIENT = "TRANSIENT";
	private static final String DETACHED = "DETACHED";
	private String executionCase = FETCH_FROM_SAME_TARNSACTION;

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private StudentLifeCycleRealiser realiser;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void ruTest() {
		doInTransaction(() -> realiser.updateRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void rudTest() {
		doInTransaction(() -> realiser.updateAndDeleteRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void rud_Flush_Test() {
		doInTransaction(() -> realiser.updateDeleteFlushRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void ru_Flush_Detach_Test() {
		doInTransaction(() -> realiser.updateFlushDetachRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void removingDetachedEntityTest() {
		doInTransaction(() -> realiser.removingDetachedEntityRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingDetachedEntityTest() {
		doInTransaction(() -> realiser.persistingDetachedEntityRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingDetachedEntityTest() {
		doInTransaction(() -> realiser.mergingDetachedEntityRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedEntityRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedEntityRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedAndFlushedEntityRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedAndFlushedEntityRealiser().accept(getStudentForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingRemovedEntityTest() {
		doInTransaction(() -> realiser.mergingRemovedEntityRealiser().accept(getStudentForTest()));
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
		doInTransaction(() -> realiser.mergedEntityRealiser().accept(getStudentForTest()));
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
		doInTransaction(() -> realiser.mergedEntityRealiser_AlreadyPresentInPC().accept(getStudentForTest()));
	}

	private Student getStudentForTest() {
		switch (executionCase) {
		/*
		 * case TRANSIENT: return EntityUtils.singleStudentSupplier().get(); case
		 * DETACHED: { Student Student = EntityUtils.singleStudentSupplier().get();
		 * Student.setId(1); return Student; } case FETCH_FROM_DIFFERENT_TARNSACTION:
		 * return StudentDao.getStudent(2);
		 */ case FETCH_FROM_SAME_TARNSACTION:
		default:
			return em.find(Student.class, 1);
		}
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
