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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.katariasoft.technologies.jpaHibernate.college.data.dao.InstructorRepository;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityLifeCycle_FromRead_Tests {

	private static final String FETCH_FROM_SAME_TARNSACTION = "FETCH_FROM_SAME_TARNSACTION";
	private static final String FETCH_FROM_DIFFERENT_TARNSACTION = "FETCH_FROM_DIFFERENT_TARNSACTION";
	private static final String TRANSIENT = "TRANSIENT";
	private static final String DETACHED = "DETACHED";
	private String executionCase = FETCH_FROM_DIFFERENT_TARNSACTION;

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private InstructorRepository instructorDao;

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

	// @Test
	@Rollback(false)
	@Transactional
	public void ruTest() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("ruTest");
			instructor.setAddress("ruTest");
			instructor.setMotherName("ruTest");
			instructor.setFatherName("ruTest");

		} catch (RuntimeException e) {
			throw e;

		}

	}

	// @Test
	@Rollback(false)
	@Transactional
	public void rudTest() {
		try {
			// read
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("rudTest");
			instructor.setAddress("rudTest");
			instructor.setMotherName("rudTest");
			instructor.setFatherName("rudTest");
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void rud_Flush_Test() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("rud_Flush_Test");
			em.flush();
			instructor.setAddress("rud_Flush_Test");
			em.flush();
			instructor.setMotherName("rud_Flush_Test");
			em.flush();
			instructor.setFatherName("rud_Flush_Test");
			em.flush();
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
			em.flush();
		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void ru_Flush_Detach_Test() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("ru_Flush_Detach_Test");
			em.flush();
			instructor.setAddress("ru_Flush_Detach_Test");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("ru_Flush_Detach_Test");
			instructor.setFatherName("ru_Flush_Detach_Test");
			instructor.setMotherName("ru_Flush_Detach_Test");
			instructor.setFatherName("ru_Flush_Detach_Test");
		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void removingDetachedEntityTest() {
		try {
			// read
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("removingDetachedEntityTest");
			em.flush();
			instructor.setAddress("removingDetachedEntityTest");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("removingDetachedEntityTest");
			instructor.setFatherName("removingDetachedEntityTest");
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
			em.flush();
		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void persistingDetachedEntityTest() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("persistingDetachedEntityTest");
			em.flush();
			instructor.setAddress("persistingDetachedEntityTest");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("persistingDetachedEntityTest");
			instructor.setFatherName("persistingDetachedEntityTest");
			// remove any as both are same objects fetched from P.C.
			em.persist(instructor);
		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void mergingDetachedEntityTest() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("mergingDetachedEntityTest");
			em.flush();
			instructor.setAddress("mergingDetachedEntityTest");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("mergingDetachedEntityTest");
			instructor.setFatherName("mergingDetachedEntityTest");
			// remove any as both are same objects fetched from P.C.
			em.merge(instructor);
		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void detachingRemovedEntityTest() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("detachingRemovedEntityTest");
			em.flush();
			instructor.setAddress("detachingRemovedEntityTest");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("detachingRemovedEntityTest");
			instructor.setFatherName("detachingRemovedEntityTest");
			// remove any as both are same objects fetched from P.C.

			em.detach(instructor);

		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void persistingRemovedEntityTest() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("persistingRemovedEntityTest");
			em.flush();
			instructor.setAddress("persistingRemovedEntityTest");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("persistingRemovedEntityTest");
			instructor.setFatherName("persistingRemovedEntityTest");
			// remove any as both are same objects fetched from P.C.

			em.persist(instructor);
		} catch (RuntimeException e) {
			throw e;

		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void detachingRemovedAndFlushedEntityTest() {
		try {
			// read
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("detachingRemovedAndFlushedEntityTest");
			em.flush();
			instructor.setAddress("detachingRemovedAndFlushedEntityTest");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("detachingRemovedAndFlushedEntityTest");
			instructor.setFatherName("detachingRemovedAndFlushedEntityTest");
			// remove any as both are same objects fetched from P.C.
			em.flush();
			em.detach(instructor);

		} catch (RuntimeException e) {
			throw e;
		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void persistingRemovedAndFlushedEntityTest() {
		try {
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("persistingRemovedAndFlushedEntityTest");
			em.flush();
			instructor.setAddress("persistingRemovedAndFlushedEntityTest");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("persistingRemovedAndFlushedEntityTest");
			instructor.setFatherName("persistingRemovedAndFlushedEntityTest");
			em.flush();
			// remove any as both are same objects fetched from P.C.

			em.persist(instructor);
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
	}

	// @Test
	@Rollback(false)
	@Transactional
	public void mergingRemovedEntityTest() {
		try {
			// read
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("mergingRemovedEntityTest");
			em.flush();
			instructor.setAddress("mergingRemovedEntityTest");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("mergingRemovedEntityTest");
			instructor.setFatherName("mergingRemovedEntityTest");
			// remove any as both are same objects fetched from P.C.
			em.merge(instructor);
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
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
	@Transactional
	public void mergedEntityTest() {
		try {
			// read
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("mergingRemovedEntityTest");
			em.flush();
			instructor.setAddress("mergingRemovedEntityTest");
			em.flush();
			instructor.setMotherName("mergingRemovedEntityTest");
			instructor.setFatherName("mergingRemovedEntityTest");
			// remove any as both are same objects fetched from P.C.
			Instructor mergedInstance = em.merge(instructor);
			instructor.setAddress("Address set in input entity merged");
			instructor.setFatherName("Father name set in input entity merged");
			instructor.setMotherName("Mother name set in input entity merged");
			// set in merged returned instance .
			mergedInstance.setAddress("Address set in merged and managed entity");
			mergedInstance.setFatherName("Father name set in merged and managed entity");
			mergedInstance.setMotherName("Mother name set in merged and managed entity");
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
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
	@Transactional
	public void mergedEntityTest_AlreadyPresentInPC() {
		try {
			Instructor fromDb = em.find(Instructor.class, 2);
			System.out.println(fromDb);
			// read
			Instructor instructor = getInstructorForTest();
			// update
			instructor.setAddress("mergingRemovedEntityTest");
			em.flush();
			instructor.setAddress("mergingRemovedEntityTest");
			em.flush();
			instructor.setMotherName("mergingRemovedEntityTest");
			instructor.setFatherName("mergingRemovedEntityTest");
			// remove any as both are same objects fetched from P.C.
			Instructor mergedInstance = em.merge(instructor);
			instructor.setAddress("Address set in input entity merged");
			instructor.setFatherName("Father name set in input entity merged");
			instructor.setMotherName("Mother name set in input entity merged");
			// set in merged returned instance .
			mergedInstance.setAddress("Address set in merged and managed entity");
			mergedInstance.setFatherName("Father name set in merged and managed entity");
			mergedInstance.setMotherName("Mother name set in merged and managed entity");
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
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

}
