package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.Objects;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Repository
public class TransactionExecutionTemplate {

	@Autowired
	private EntityManagerFactory emf;

	private static final Logger logger = LoggerFactory.getLogger(TransactionExecutionTemplate.class);

	@Transactional
	public void doInTransaction(Executable executable) {
		try {
			Objects.requireNonNull(executable);
			executable.execute();
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw e;
		}
	}

	public void doInProgramaticTx(Consumer<EntityManager> executable) {
		boolean exceptionOccured = false;
		Objects.requireNonNull(executable);
		String threadName = Thread.currentThread().getName();
		EntityManager em = emf.createEntityManager();
		logger.info("Entity Manager Instance for Thread {} is {}  ", threadName, em);
		try {
			em.getTransaction().begin();
			logger.info("Transaction started for entity Manager Instance for Thread {} ", threadName);
			executable.accept(em);
			logger.info("Going to commit Transaction for entity Manager Instance for Thread {} ", threadName);
			em.getTransaction().commit();
			logger.info("Committed transaction properly for Thread {} ", threadName);
		} catch (Exception e) {
			logger.error("Rolling back transaction for Thread {} ", threadName);
			em.getTransaction().setRollbackOnly();
			logger.error("Execption occured while committing thread {} exception is {} : ", threadName, e);
			exceptionOccured = true;
		} finally {
			logger.warn("Closing entity manager " + (exceptionOccured ? "because of exception" : "properly")
					+ "for Thread {} ", threadName);
			em.close();
		}
	}

}
