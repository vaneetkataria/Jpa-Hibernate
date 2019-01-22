package com.katariasoft.technologies.jpaHibernate.entity.criteria;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.Document;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.Document_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityGraphUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaJoinsWithEntityGraphTests {

	@Autowired
	private EntityGraphUtils entityGraphUtils;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private QueryExecutor queryExecutor;

	@Test
	@Rollback(false)
	@Transactional
	public void fetchFullDataWithJPQLQuery() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof);
		root.join(Instructor_.vehicles);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students);
		insStuJoin.join(Student_.instructors);
		Join<Student, Vehicle> stuVehcileJoin = insStuJoin.join(Student_.vehicles);
		Join<Vehicle, Document> vehicleDocumentJoin = stuVehcileJoin.join(Vehicle_.documents);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.select(root).where(cb.greaterThan(root.get(Instructor_.id), 2),
						cb.in(vehicleDocumentJoin.get(Document_.name)).value("1").value("2").value("3"))));
	}

	@Test
	@Rollback(false)
	@Transactional
	public void fetchFullDataWithJPQLQueryWithEqualEntityGraph() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof);
		root.join(Instructor_.vehicles);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students);
		insStuJoin.join(Student_.instructors);
		Join<Student, Vehicle> stuVehcileJoin = insStuJoin.join(Student_.vehicles);
		Join<Vehicle, Document> vehicleDocumentJoin = stuVehcileJoin.join(Vehicle_.documents);
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForCriteriaQuery(
				cq.select(root).where(cb.greaterThan(root.get(Instructor_.id), 2),
						cb.in(vehicleDocumentJoin.get(Document_.name)).value("1").value("2").value("3")),
				entityGraphUtils.fullEntityGraph()));
	}

	@Test
	@Rollback(false)
	@Transactional
	public void fetchFullDataWithJPQLQueryWithPartialEntityGraph() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof);
		root.join(Instructor_.vehicles).join(Vehicle_.documents);
		root.join(Instructor_.students).join(Student_.vehicles);
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForCriteriaQuery(
				cq.select(root).where(cb.greaterThan(root.get(Instructor_.id), 2)),
				entityGraphUtils.partialEntityGraph()));
	}

	@Test
	@Rollback(false)
	@Transactional
	public void fetchInstructorsOnlyWithJPQLQueryWithFullEntityGraph() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof);
		EntityGraph<Instructor> eg = entityManager.createEntityGraph(Instructor.class);
		eg.addAttributeNodes(Instructor_.idProof);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.select(root).where(cb.greaterThan(root.get(Instructor_.id), 2)), eg));

	}

}
