package com.katariasoft.technologies.jpaHibernate.entity.transactions.propagation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.transaction.propagation.TransactionPropagationsTestsSupport;
import static com.katariasoft.technologies.jpaHibernate.college.data.utils.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionPropagationTests {

	private static Propagation propagation = Propagation.REQUIRED;

	@Autowired
	private TransactionPropagationsTestsSupport propagationSupport;
	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	@Rollback(false)
	public void testRequiredWithSubsequentSucceedSelfSucceed() {
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed");
		propagationSupport.requiredPropagation(true);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testRequiredWithSubsequentFailSelfSucceed() {
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed");
		propagationSupport.requiredPropagation(false);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testRequiredWithSubsequentSucceedSelfFail() {
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed");
		propagationSupport.requiredPropagation(true);
		isTrue(true, RuntimeException::new, "Exception occured in testRequiredWithSubsequentSucceedSelfFail .");
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testRequiredWithSubsequentFailSelfFail() {
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed");
		propagationSupport.requiredPropagation(false);
		isTrue(true, RuntimeException::new, "Exception occured in testRequiredWithSubsequentFailSelfFail .");
	}

	private void propagate(boolean succeed) {
		switch (propagation) {

		case REQUIRES_NEW:
			propagationSupport.requiresNewPropagation(succeed);
			break;
		case MANDATORY:
			propagationSupport.manadatoryPropagation(succeed);
			break;
		case NEVER:
			propagationSupport.neverPropagation(succeed);
			break;
		case SUPPORTS:
			propagationSupport.supportsPropagation(succeed);
			break;
		case NOT_SUPPORTED:
			propagationSupport.notSupportedPropagation(succeed);
			break;
		default:
			propagationSupport.requiredPropagation(succeed);

		}

	}

}
