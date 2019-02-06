package com.katariasoft.technologies.jpaHibernate.entity.concurrency.locking.pessimistic;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessimisticLockTests extends PessimisticLockTestSupport {

	@Test
	@Rollback(false)
	public void pessimisticReadSimpleReadTest() {
		testPessimisticLocking(21, Optional.of(LockModeType.PESSIMISTIC_READ), Optional.empty());
	}

	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticReadTest() {
		testPessimisticLocking(35, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_READ));
	}

	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticWriteTest() {
		testPessimisticLocking(40, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_WRITE), 0, 5000L);
	}

	@Test
	@Rollback(false)
	public void pessimisticWriteSimpleReadTest() {
		testPessimisticLocking(33, Optional.of(LockModeType.PESSIMISTIC_WRITE), Optional.empty());
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticReadTest() {
		testPessimisticLocking(31, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_READ), 0, 5000L);
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticWriteTest() {
		testPessimisticLocking(32, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_WRITE), 0, 5000L);
	}

}
