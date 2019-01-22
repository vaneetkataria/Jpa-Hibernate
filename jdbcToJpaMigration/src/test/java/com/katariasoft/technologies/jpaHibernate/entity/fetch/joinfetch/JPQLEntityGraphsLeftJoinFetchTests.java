package com.katariasoft.technologies.jpaHibernate.entity.fetch.joinfetch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityGraphUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLEntityGraphsLeftJoinFetchTests {

	@Autowired
	private EntityGraphUtils entityGraphUtils;
	@Autowired
	private QueryExecutor queryExecutor;
	@Autowired
	private EntityManager em;

	@Test
	@Rollback(false)
	@Transactional
	public void fetchFullDataWithJPQLQuery() {
		List<Instructor> instructors = queryExecutor.fetchListForJpqlQuery(
				"select i from Instructor i " + "left join i.idProof id " + "left join i.vehicles v "
						+ "left join v.documents vd " + "left join i.students s " + "left join s.instructors si "
						+ "left join s.vehicles sv " + "left join sv.documents svd "
						+ "where i.id > :id and svd.name in (:names)",
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3", "4", "5")), Instructor.class);
		DataPrinters.listDataPrinter.accept(instructors);
	}

	@Test
	@Rollback(false)
	@Transactional
	public void fetchFullDataWithJPQLQueryWithEqualEntityGraph() {
		List<Instructor> instructors = queryExecutor.fetchListForJpqlQuery(
				"select i from Instructor i " + "left join i.idProof id " + "left join i.vehicles v "
						+ "left join i.students s " + "left join s.instructors si " + "left join s.vehicles sv "
						+ "left join sv.documents svd " + "where i.id > :id and svd.name in (:names)",
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3")), Instructor.class,
				entityGraphUtils.fullEntityGraph());
		DataPrinters.listDataPrinter.accept(instructors);
	}

	@Test
	@Rollback(false)
	@Transactional
	public void fetchFullDataWithJPQLQueryWithPartialEntityGraph() {
		List<Instructor> instructors = queryExecutor.fetchListForJpqlQuery(
				"select i from Instructor i " + " left join i.idProof id " + " left join i.vehicles v "
						+ " left join v.documents vd " + " left join i.students s " + "left join s.instructors si "
						+ "left join s.vehicles sv " + " where i.id > :id ",
				CollectionUtils.mapOf("id", 2), Instructor.class, entityGraphUtils.partialEntityGraph());
		DataPrinters.listDataPrinter.accept(instructors);
	}

	@Test
	@Rollback(false)
	@Transactional
	public void fetchInstructorsAndIdProofWithJPQLQueryWithFullEntityGraph() {
		EntityGraph<Instructor> eg = em.createEntityGraph(Instructor.class);
		eg.addAttributeNodes(Instructor_.idProof);
		List<Instructor> instructors = queryExecutor.fetchListForJpqlQuery(
				"select i from Instructor i " + "left join i.idProof id" + " where i.id > :id ",
				Collections.singletonMap("id", 2), Instructor.class, eg);
		DataPrinters.listDataPrinter.accept(instructors);
	}

	@Test
	@Rollback(false)
	@Transactional
	public void fetchInstructorsOnlyWithJPQLQueryWithFullEntityGraph() {
		List<Instructor> instructors = queryExecutor.fetchListForJpqlQuery(
				"select i from Instructor i where id.id > :id", Collections.singletonMap("id", 2), Instructor.class,
				entityGraphUtils.fullEntityGraph());
		DataPrinters.listDataPrinter.accept(instructors);
	}

}
