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
		testPessimisticLocking(2, Optional.of(LockModeType.PESSIMISTIC_READ), Optional.empty());
	}

	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticReadTest() {
		testPessimisticLocking(4, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_READ));
	}

	// unclear
	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticWriteTest() {
		testPessimisticLocking(6, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_WRITE) , 0 , 0 , 5000L);
	}

	// unclear
	@Test
	@Rollback(false)
	public void pessimisticWriteSimpleReadTest() {
		testPessimisticLocking(7, Optional.of(LockModeType.PESSIMISTIC_WRITE), Optional.empty());
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticReadTest() {
		testPessimisticLocking(8, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_READ));
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticWriteTest() {
		testPessimisticLocking(9, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_WRITE));
	}

}
