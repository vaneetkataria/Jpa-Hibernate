package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.Objects;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Repository
public class TransactionExecutionTemplate {

	@Autowired
	private EntityManagerFactory emf;

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
		System.out.println("Entity Manager Instance for Thread " + Thread.currentThread().getName() + " is :  " + em);
		try {
			em.getTransaction().begin();
			executable.accept(em);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().setRollbackOnly();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

}
