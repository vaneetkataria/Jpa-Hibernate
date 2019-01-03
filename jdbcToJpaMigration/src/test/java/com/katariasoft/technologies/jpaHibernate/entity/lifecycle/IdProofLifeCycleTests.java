package com.katariasoft.technologies.jpaHibernate.entity.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle.IdProofLifeCycleRealiser;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdProofLifeCycleTests {

	private static final String FETCH_FROM_SAME_TARNSACTION = "FETCH_FROM_SAME_TARNSACTION";
	private static final String FETCH_FROM_DIFFERENT_TARNSACTION = "FETCH_FROM_DIFFERENT_TARNSACTION";
	private static final String TRANSIENT = "TRANSIENT";
	private static final String DETACHED = "DETACHED";
	private String executionCase = FETCH_FROM_SAME_TARNSACTION;

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private IdProofLifeCycleRealiser realiser;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void ruTest() {
		doInTransaction(() -> realiser.updateRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void rudTest() {
		doInTransaction(() -> realiser.updateAndDeleteRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void rud_Flush_Test() {
		doInTransaction(() -> realiser.updateDeleteFlushRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void ru_Flush_Detach_Test() {
		doInTransaction(() -> realiser.updateFlushDetachRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void removingDetachedEntityTest() {
		doInTransaction(() -> realiser.removingDetachedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingDetachedEntityTest() {
		doInTransaction(() -> realiser.persistingDetachedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingDetachedEntityTest() {
		doInTransaction(() -> realiser.mergingDetachedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedAndFlushedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedAndFlushedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingRemovedEntityTest() {
		doInTransaction(() -> realiser.mergingRemovedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void mergedEntityTest() {
		doInTransaction(() -> realiser.mergedEntityRealiser().accept(getIdProofForTest()));
	}

	@Test
	@Rollback(false)
	public void mergedEntityTest_AlreadyPresentInPC() {
		doInTransaction(() -> realiser.mergedEntityRealiser_AlreadyPresentInPC().accept(getIdProofForTest()));
	}

	private IdProof getIdProofForTest() {
		switch (executionCase) {
		/*
		 * case TRANSIENT: return EntityUtils.singleIdProofSupplier().get(); case
		 * DETACHED: { IdProof IdProof = EntityUtils.singleIdProofSupplier().get();
		 * IdProof.setId(1); return IdProof; } case FETCH_FROM_DIFFERENT_TARNSACTION:
		 * return IdProofDao.getIdProof(2);
		 */ case FETCH_FROM_SAME_TARNSACTION:
		default:
			return em.find(IdProof.class, 1);
		}
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}
}
