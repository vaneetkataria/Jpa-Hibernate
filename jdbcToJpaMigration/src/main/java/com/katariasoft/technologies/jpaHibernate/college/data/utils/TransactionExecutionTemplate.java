package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.Objects;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
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
		Objects.requireNonNull(executable);
		EntityManager em = emf.createEntityManager();
		logger.info("Entity Manager Instance for Thread {} is {}  ", Thread.currentThread().getName(), em);
		try {
			em.getTransaction().begin();

			em.unwrap(Session.class).doWork(connection -> {
				connection.setTransactionIsolation(4);
				executable.accept(em);
			});

			em.getTransaction().commit();
			logger.info("Committed transaction properly for Thread {} ", Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error("Rolling back transaction for Thread {} ", Thread.currentThread().getName());
			em.getTransaction().setRollbackOnly();
			e.printStackTrace();
		} finally {
			logger.warn("Closing entity manager properly for Thread {} ", Thread.currentThread().getName());
			em.close();
		}
	}

}
