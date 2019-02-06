package com.katariasoft.technologies.jpaHibernate.entity.concurrency.locking.pessimistic;

import static com.katariasoft.technologies.jpaHibernate.entity.utilities.ThreadUtil.executeAsync;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

import static com.katariasoft.technologies.jpaHibernate.entity.utilities.ThreadUtil.*;

@Component
public class PessimisticLockTestSupport {

	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	public void testPessimisticLocking(int recordId, Optional<LockModeType> mainThreadLock,
			Optional<LockModeType> secondaryThreadLock, long waitms, long lockTimeOut) {

		Map<String, Object> lockProperties = CollectionUtils.mapOf("javax.persistence.lock.timeout", lockTimeOut);

		doInTransansaction(em -> {

			Instructor instructor = mainThreadLock.isPresent()
					? em.find(Instructor.class, recordId, mainThreadLock.get(), lockProperties)
					: em.find(Instructor.class, recordId);

			instructor.setName("simpleOptimisticLockTest_MainThread");
			System.out.println("Instructor in main Thread is :" + instructor);

			executeAsync(() -> {

				doInTransansaction(em_ -> {

					Instructor instructor_ = secondaryThreadLock.isPresent()
							? em_.find(Instructor.class, recordId, secondaryThreadLock.get(), lockProperties)
							: em_.find(Instructor.class, recordId);

					System.out.println("Instructor in secondary Thread is :" + instructor_);
					instructor_.setName("simpleOptimisticLockTest_SecondaryThread");
					System.out.println(
							"###Going to commit Second Thread transaction with updating name as simpleOptimisticLockTest_Secondary");
				});
			});

			if (waitms > 0)
				WAIT_MS(waitms);

			System.out.println(
					"###Going to commit Main Thread transaction with updating name as simpleOptimisticLockTest");
		});

	}

	public void testPessimisticLocking(int recordId, Optional<LockModeType> mainThreadLock,
			Optional<LockModeType> secondaryThreadLock) {
		testPessimisticLocking(recordId, mainThreadLock, secondaryThreadLock, 0, 0);
	}

	public void testPessimisticLocking(int recordId, Optional<LockModeType> mainThreadLock,
			Optional<LockModeType> secondaryThreadLock, long waitms) {
		testPessimisticLocking(recordId, mainThreadLock, secondaryThreadLock, waitms, 0);
	}

	public void doInTransansaction(Consumer<EntityManager> executable) {
		transactionTemplate.doInProgramaticTx(executable);
	}

}
