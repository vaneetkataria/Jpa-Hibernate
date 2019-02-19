package com.katariasoft.technologies.jpaHibernate.college.data.transaction.propagation;

import static com.katariasoft.technologies.jpaHibernate.college.data.utils.Assert.isTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	private static final Logger logger = LoggerFactory.getLogger(TransactionPropagationTests.class);

	@Autowired
	private TransactionPropagationsTestsSupport propagationSupport;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void testRequiredWithSelfFailSubsequentFail(int revision, Propagation propagation) {
		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		try {
			propagate(false, propagation, revision);
		} catch (Exception e) {
			logger.error("Exception occured while execuiting subsequent transaction. Exception is {} ", e);
		}
		System.out.println("########Completed propagation call");
		isTrue(true, RuntimeException::new, "Exception occured in testRequiredWithSubsequentFailSelfFail .");
	}

	@Transactional
	public void testRequiredWithSelfFailSubsequentSucceed(int revision, Propagation propagation) {
		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		propagate(true, propagation, revision);
		System.out.println("########Completed propagation call");
		isTrue(true, RuntimeException::new, "Exception occured in testRequiredWithSubsequentFailSelfFail .");
	}

	@Transactional
	public void testRequiredWithSelfSucceedSubsequentFail(int revision, Propagation propagation) {
		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		try {
			propagate(false, propagation, revision);
		} catch (Exception e) {
			logger.error("Exception occured while execuiting subsequent transaction. Exception is {} ", e);
		}
		System.out.println("########Completed propagation call");
	}

	@Transactional
	public void testRequiredWithSelfSucceedSubsequentSucceed(int revision, Propagation propagation) {
		logger.info("Entity Manager intance in test method is {}", em);
		Instructor instructor = em.find(Instructor.class, 1);
		instructor.setName("testRequiredWithSubsequentSucceed" + revision);
		propagate(true, propagation, revision);
		System.out.println("########Completed propagation call");
	}

	private void propagate(boolean succeed, Propagation propagation, int revision) {
		switch (propagation) {
		case REQUIRES_NEW:
			propagationSupport.requiresNewPropagation(succeed, revision);
			break;
		case MANDATORY:
			propagationSupport.manadatoryPropagation(succeed, revision);
			break;
		case NEVER:
			propagationSupport.neverPropagation(succeed, revision);
			break;
		case SUPPORTS:
			propagationSupport.supportsPropagation(succeed, revision);
			break;
		case NOT_SUPPORTED:
			propagationSupport.notSupportedPropagation(succeed, revision);
			break;
		case NESTED:
			propagationSupport.nestedPropagation(succeed, revision);
			break;
		default:
			propagationSupport.requiredPropagation(succeed, revision);
		}

	}

}
