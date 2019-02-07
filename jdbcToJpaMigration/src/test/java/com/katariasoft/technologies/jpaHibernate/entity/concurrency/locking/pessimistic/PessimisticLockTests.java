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
		testPessimisticLocking(2, Optional.of(LockModeType.PESSIMISTIC_READ), Optional.empty(), 5000L);
	}

	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticReadTest() {
		testPessimisticLocking(15, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_READ));
	}

	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticWriteTest() {
		testPessimisticLocking(19, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_WRITE) , 0 , 0 , 5000L);
	}

	@Test
	@Rollback(false)
	public void pessimisticWriteSimpleReadTest() {
		testPessimisticLocking(22, Optional.of(LockModeType.PESSIMISTIC_WRITE), Optional.empty());
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticReadTest() {
		testPessimisticLocking(20, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_READ));
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticWriteTest() {
		testPessimisticLocking(50, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_WRITE));
	}

}
