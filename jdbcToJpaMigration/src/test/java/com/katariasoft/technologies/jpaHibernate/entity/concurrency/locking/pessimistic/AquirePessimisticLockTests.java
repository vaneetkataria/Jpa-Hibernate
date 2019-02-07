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
public class AquirePessimisticLockTests extends PessimisticLockTestSupport {

	@Test
	@Rollback(false)
	public void pessimisticReadSimpleReadTest() {
		testAquirePessimisticLocking(22, Optional.of(LockModeType.PESSIMISTIC_READ), Optional.empty(), 5000L);
	}

	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticReadTest() {
		testAquirePessimisticLocking(24, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_READ));
	}

	@Test
	@Rollback(false)
	public void pessimisticReadPessimisticWriteTest() {
		testAquirePessimisticLocking(25, Optional.of(LockModeType.PESSIMISTIC_READ),
				Optional.of(LockModeType.PESSIMISTIC_WRITE), 0, 0, 5000L);
	}

	@Test
	@Rollback(false)
	public void pessimisticWriteSimpleReadTest() {
		testAquirePessimisticLocking(26, Optional.of(LockModeType.PESSIMISTIC_WRITE), Optional.empty());
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticReadTest() {
		testAquirePessimisticLocking(27, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_READ));
	}

	@Test
	@Rollback(false)
	public void pessimisticWritePessimisticWriteTest() {
		testAquirePessimisticLocking(28, Optional.of(LockModeType.PESSIMISTIC_WRITE),
				Optional.of(LockModeType.PESSIMISTIC_WRITE));
	}

}
