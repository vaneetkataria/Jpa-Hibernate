package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.Objects;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Repository
public class TransactionExecutionTemplate {

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

}
