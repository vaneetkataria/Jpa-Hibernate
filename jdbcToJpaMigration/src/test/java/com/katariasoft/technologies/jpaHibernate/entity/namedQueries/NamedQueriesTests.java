package com.katariasoft.technologies.jpaHibernate.entity.namedQueries;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.katariasoft.technologies.jpaHibernate.college.data.dao.InstructorDao;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NamedQueriesTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private InstructorDao instructorDao;

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void fetchAndPrintAllInstructors() {
		try {
			Optional<List<Instructor>> instructors = instructorDao.fetchAllInstructors();
			if (instructors.isPresent())
				instructors.get().stream().forEach(System.out::println);
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	// @Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void fetchAndDeleteAllInstructorsOneByOne() {
		try {
			Optional<List<Instructor>> instructors = instructorDao.fetchAllInstructors();
			if (instructors.isPresent())
				instructors.get().stream().forEach(e -> em.remove(e));
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

	@Test
	@Rollback(false)
	@Transactional(rollbackFor = Exception.class)
	public void fetchAndChnageAllInstructorsBatch() {
		try {
			Optional<List<Instructor>> instructors = instructorDao.fetchAllInstructors();
			List<Integer> instructorsToDelete = null;
			if (instructors.isPresent())
				instructorsToDelete = instructors.get().stream().limit(4).skip(2).map(Instructor::getId)
						.collect(Collectors.toList());
			instructorDao.deleteAllInstructors(instructorsToDelete);
		} catch (RuntimeException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		}
	}

}
