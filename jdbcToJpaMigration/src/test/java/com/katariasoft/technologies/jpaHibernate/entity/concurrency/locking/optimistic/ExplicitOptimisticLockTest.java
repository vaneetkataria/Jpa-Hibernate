package com.katariasoft.technologies.jpaHibernate.entity.concurrency.locking.optimistic;

import static com.katariasoft.technologies.jpaHibernate.entity.utilities.ThreadUtil.*;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExplicitOptimisticLockTest {

	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void simpleOptimisticLockTest() {
		doInTransansaction(em -> {
			Instructor instructor = em.find(Instructor.class, 25, LockModeType.OPTIMISTIC);
			System.out.println("Main Thread Instructor HashCode is :" + instructor.hashCode());
			System.out.println("Main Thread Instructor Version is :" + instructor.getVersion());
			instructor.getIdProof().setName("Updated in Main Thread Tx12");
			executeAsync(() -> {
				doInTransansaction(em_ -> {
					Instructor instructor_ = em_.find(Instructor.class, 25);
					System.out.println("Secondary Thread Instructor HashCode is :" + instructor_.hashCode());
					System.out.println("Secondary Thread Instructor Version is :" + instructor_.getVersion());
					instructor_.setName("simpleOptimisticLockTest_Secondary23");
					System.out.println(
							"###Going to commit Second Thread transaction with updating name as simpleOptimisticLockTest_Secondary in Instructor as well as IdProof.");
				});
			});

			System.out.println("###Going to wait for 10000 ms.");
			WAIT_MS(50000L);
			System.out.println(
					"###Going to commit Main Thread transaction with updating only id proof name as name of instructor . No chnage in instructor");
		});

	}

	private void doInTransansaction(Consumer<EntityManager> executable) {
		transactionTemplate.doInProgramaticTx(executable);
	}

}
