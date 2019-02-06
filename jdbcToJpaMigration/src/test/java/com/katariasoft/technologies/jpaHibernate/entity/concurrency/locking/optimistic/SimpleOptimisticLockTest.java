package com.katariasoft.technologies.jpaHibernate.entity.concurrency.locking.optimistic;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

import static com.katariasoft.technologies.jpaHibernate.entity.utilities.ThreadUtil.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleOptimisticLockTest {

	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@Test
	@Rollback(false)
	public void simpleOptimisticLockTest() {
		doInTransansaction(em -> {
			Instructor instructor = em.find(Instructor.class, 1);
			instructor.setName("simpleOptimisticLockTest_Main");

			executeAsync(() -> {
				doInTransansaction(em_ -> {
					Instructor instructor_ = em_.find(Instructor.class, 1);
					instructor_.setName("simpleOptimisticLockTest_Secondary");
					System.out.println(
							"###Going to commit Second Thread transaction with updating name as simpleOptimisticLockTest_Secondary");
				});
			});

			System.out.println("###Going to wait for 10000 ms.");
			WAIT_MS(5000L);
			System.out.println(
					"###Going to commit Main Thread transaction with updating name as simpleOptimisticLockTest");
		});

	}

	private void doInTransansaction(Consumer<EntityManager> executable) {
		transactionTemplate.doInProgramaticTx(executable);
	}

}
