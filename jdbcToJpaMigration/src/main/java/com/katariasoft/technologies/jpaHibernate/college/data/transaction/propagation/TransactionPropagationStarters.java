package com.katariasoft.technologies.jpaHibernate.college.data.transaction.propagation;

import static com.katariasoft.technologies.jpaHibernate.college.data.transaction.propagation.TransactionPropagationsTestsSupport.revision;
import static com.katariasoft.technologies.jpaHibernate.college.data.utils.Assert.isTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.entity.transactions.propagation.TransactionPropagationTests;

@Service
public class TransactionPropagationStarters {

	private static Propagation propagation = Propagation.REQUIRED;
	private static final Logger logger = LoggerFactory.getLogger(TransactionPropagationTests.class);
	@Autowired
	private TransactionPropagationsTestsSupport propagationSupport;
	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void testRequiredWithSelfSucceedSubsequentSucceed() {
		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		propagate(true);
		System.out.println("########Completed propagation call");
	}

	@Test
	@Transactional
	public void testRequiredWithSelfSucceedSubsequentFail() {

		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		try {
			propagate(false);
		} catch (Exception e) {
			logger.error("Exception occured while execuiting subsequent transaction. Exception is {} ", e);
			throw e;
		}
		System.out.println("########Completed propagation call");
	}

	@Test
	@Transactional
	public void testRequiredWithSelfFailSubsequentSucceed() {
		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		propagate(true);
		System.out.println("########Completed propagation call");
		isTrue(true, RuntimeException::new, "Exception occured in testRequiredWithSubsequentFailSelfFail .");
	}

	@Test
	@Transactional
	public void testRequiredWithSelfFailSubsequentFail() {

		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		try {
			propagate(false);
		} catch (Exception e) {
			logger.error("Exception occured while execuiting subsequent transaction. Exception is {} ", e);
		}
		System.out.println("########Completed propagation call");
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
