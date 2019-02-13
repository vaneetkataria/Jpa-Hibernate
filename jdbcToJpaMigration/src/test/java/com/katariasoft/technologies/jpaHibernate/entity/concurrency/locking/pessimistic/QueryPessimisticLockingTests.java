package com.katariasoft.technologies.jpaHibernate.entity.concurrency.locking.pessimistic;

import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.Document;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryPessimisticLockingTests extends PessimisticLockTestSupport {

	private static final Logger logger = LoggerFactory.getLogger(QueryPessimisticLockingTests.class);

	// unclear
	@Test
	@Rollback(false)
	public void pessimisticReadLockWithUpdateQueryTest() {
		testPessimisticLockingWithQuery(Optional.of(LockModeType.PESSIMISTIC_READ), em -> {
			Query query = em.createNativeQuery("update document d set d.name = :name  where d.id < :id ");
			CollectionUtils.mapOf("name", "UpdatedInSecondaryRunnable" + revision, "id", 100)
					.forEach(query::setParameter);
			query.executeUpdate();
			logger.info("Going to commit secondary thread {} with for updation  ", Thread.currentThread().getName());
		}, defaultMainThreadWaitMs);

	}

	// unclear
	@Test
	@Rollback(false)
	public void pessimisticReadLockWithDeleteQueryTest() {
		testPessimisticLockingWithQuery(Optional.of(LockModeType.PESSIMISTIC_READ), em -> {
			Query query = em.createNativeQuery("delete from document where id < :id ");
			query.setParameter("id", 50);
			query.executeUpdate();
			logger.info("Going to commit secondary thread {} for deletion ", Thread.currentThread().getName());
		}, defaultMainThreadWaitMs);

	}

	@Test
	@Rollback(false)
	public void pessimisticReadLockWithInsertQueryTest() {
		testPessimisticLockingWithQuery(Optional.of(LockModeType.PESSIMISTIC_READ), em -> {
			em.persist(new Document("pessimisticLockTestWithInserts+" + revision));
			logger.info("Going to commit secondary thread {} for insertion ", Thread.currentThread().getName());
		}, defaultMainThreadWaitMs);
	}

	@Test
	@Rollback(false)
	public void pessimisticWriteLockWithUpdateQueryTest() {
		testPessimisticLockingWithQuery(Optional.of(LockModeType.PESSIMISTIC_WRITE), em -> {
			Query query = em.createNativeQuery("update document d set d.name = :name  where d.id < :id ");
			CollectionUtils.mapOf("name", "UpdatedInSecondaryRunnable" + revision, "id", 100)
					.forEach(query::setParameter);
			query.executeUpdate();
			logger.info("Going to commit secondary thread {} with for updation  ", Thread.currentThread().getName());
		}, defaultMainThreadWaitMs);
	}

	@Test
	@Rollback(false)
	public void pessimisticWriteLockWithDeleteQueryTest() {
		testPessimisticLockingWithQuery(Optional.of(LockModeType.PESSIMISTIC_WRITE), em -> {
			Query query = em.createNativeQuery("delete from document where id > :id ");
			query.setParameter("id", 2);
			query.executeUpdate();
			logger.info("Going to commit secondary thread {} for deletion ", Thread.currentThread().getName());
		}, defaultMainThreadWaitMs);

	}

	@Test
	@Rollback(false)
	public void pessimisticWriteLockWithInsertQueryTest() {
		testPessimisticLockingWithQuery(Optional.of(LockModeType.PESSIMISTIC_WRITE), em -> {
			em.persist(new Document("pessimisticLockTestWithInserts+" + revision));
			logger.info("Going to commit secondary thread {} for insertion ", Thread.currentThread().getName());
		}, defaultMainThreadWaitMs);
	}

}
