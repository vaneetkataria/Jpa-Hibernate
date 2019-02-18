package com.katariasoft.technologies.jpaHibernate.college.data.transaction.propagation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.Document;
import static com.katariasoft.technologies.jpaHibernate.college.data.utils.Assert.*;

@Service
public class TransactionPropagationsTestsSupport {

	private final int revision = 0;
	private static final String HIPHEN = "-";

	@PersistenceContext
	private EntityManager em;

	@Transactional(propagation = Propagation.REQUIRED)
	public void requiredPropagation(boolean succeed) {
		Document document = em.find(Document.class, 1);
		document.setName("UpdatedIn" + HIPHEN + Propagation.REQUIRED.name() + HIPHEN + revision);

		isTrue(!succeed, RuntimeException::new,
				"Exception occured while saving document in requiredPropagation method. ");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void requiresNewPropagation(boolean succeed) {
		Document document = em.find(Document.class, 1);
		document.setName("UpdatedIn" + HIPHEN + Propagation.REQUIRES_NEW.name() + HIPHEN + revision);
		isTrue(!succeed, RuntimeException::new,
				"Exception occured while saving document in requiresNewPropagation method. ");
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void manadatoryPropagation(boolean succeed) {
		Document document = em.find(Document.class, 1);
		document.setName("UpdatedIn" + HIPHEN + Propagation.MANDATORY.name() + HIPHEN + revision);
		isTrue(!succeed, RuntimeException::new,
				"Exception occured while saving document in manadatoryPropagation method. ");
	}

	@Transactional(propagation = Propagation.NEVER)
	public void neverPropagation(boolean succeed) {
		Document document = em.find(Document.class, 1);
		document.setName("UpdatedIn" + HIPHEN + Propagation.NEVER.name() + HIPHEN + revision);
		isTrue(!succeed, RuntimeException::new, "Exception occured while saving document in neverPropagation method. ");
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void supportsPropagation(boolean succeed) {
		Document document = em.find(Document.class, 1);
		document.setName("UpdatedIn" + HIPHEN + Propagation.SUPPORTS.name() + HIPHEN + revision);
		isTrue(!succeed, RuntimeException::new,
				"Exception occured while saving document in supportsPropagation method. ");
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void notSupportedPropagation(boolean succeed) {
		Document document = em.find(Document.class, 1);
		document.setName("UpdatedIn" + HIPHEN + Propagation.NOT_SUPPORTED.name() + HIPHEN + revision);
		isTrue(!succeed, RuntimeException::new,
				"Exception occured while saving document in notSupportedPropagation method. ");
	}

}
