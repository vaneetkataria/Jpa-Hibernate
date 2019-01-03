package com.katariasoft.technologies.jpaHibernate.entity.lifecycle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle.VehicleLifeCycleRealiser;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleLifeCycleTests {

	private static final String FETCH_FROM_SAME_TARNSACTION = "FETCH_FROM_SAME_TARNSACTION";
	private static final String FETCH_FROM_DIFFERENT_TARNSACTION = "FETCH_FROM_DIFFERENT_TARNSACTION";
	private static final String TRANSIENT = "TRANSIENT";
	private static final String DETACHED = "DETACHED";
	private String executionCase = FETCH_FROM_SAME_TARNSACTION;

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private VehicleLifeCycleRealiser realiser;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void ruTest() {
		doInTransaction(() -> realiser.updateRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void rudTest() {
		doInTransaction(() -> realiser.updateAndDeleteRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void rud_Flush_Test() {
		doInTransaction(() -> realiser.updateDeleteFlushRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void ru_Flush_Detach_Test() {
		doInTransaction(() -> realiser.updateFlushDetachRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void removingDetachedEntityTest() {
		doInTransaction(() -> realiser.removingDetachedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingDetachedEntityTest() {
		doInTransaction(() -> realiser.persistingDetachedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingDetachedEntityTest() {
		doInTransaction(() -> realiser.mergingDetachedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void detachingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.detachingRemovedAndFlushedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void persistingRemovedAndFlushedEntityTest() {
		doInTransaction(() -> realiser.persistingRemovedAndFlushedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void mergingRemovedEntityTest() {
		doInTransaction(() -> realiser.mergingRemovedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void mergedEntityTest() {
		doInTransaction(() -> realiser.mergedEntityRealiser().accept(getVehicleForTest()));
	}

	@Test
	@Rollback(false)
	public void mergedEntityTest_AlreadyPresentInPC() {
		doInTransaction(() -> realiser.mergedEntityRealiser_AlreadyPresentInPC().accept(getVehicleForTest()));
	}

	private Vehicle getVehicleForTest() {
		switch (executionCase) {
		/*
		 * case TRANSIENT: return EntityUtils.singleVehicleSupplier().get(); case
		 * DETACHED: { Vehicle Vehicle = EntityUtils.singleVehicleSupplier().get();
		 * Vehicle.setId(1); return Vehicle; } case FETCH_FROM_DIFFERENT_TARNSACTION:
		 * return VehicleDao.getVehicle(2);
		 */ case FETCH_FROM_SAME_TARNSACTION:
		default:
			return em.find(Vehicle.class, 1);
		}
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
