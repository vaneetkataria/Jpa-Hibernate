package com.katariasoft.technologies.jpaHibernate.entity.fetch.optimising;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CriteriaUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptimisingFetchTests {

	@Autowired
	private QueryExecutor queryExecutor;
	@Autowired
	private CriteriaUtils criteriaUtils;
	@Autowired
	private EntityGraphUtils entityGraphUtils;

	@Test
	@Transactional
	@Rollback(false)
	public void jpqlJoinDistinctTest() {
		String query = "select distinct i from Instructor i " + "join i.idProof id " + "join i.vehicles v "
				+ "join i.students s " + "join s.instructors si " + "join s.vehicles sv " + "join sv.documents svd "
				+ "where i.id > :id and svd.name in (:names)";
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForJpqlQuery(query,
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3")), Instructor.class));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void jpqlLeftJoinDistinctTest() {
		String query = "select distinct i from Instructor i " + "left join i.idProof id " + "left join i.vehicles v "
				+ "left join i.students s " + "left join s.instructors si " + "left join s.vehicles sv "
				+ "left join sv.documents svd " + "where i.id > :id and svd.name in (:names)";
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForJpqlQuery(query,
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3")), Instructor.class));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void jpqlInnerJoinDistinctTest() {
		String query = "select distinct i from Instructor i " + "inner join i.idProof id " + "inner join i.vehicles v "
				+ "inner join i.students s " + "inner join s.instructors si " + "inner join s.vehicles sv "
				+ "inner join sv.documents svd " + "where i.id > :id and svd.name in (:names)";
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForJpqlQuery(query,
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3")), Instructor.class));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void jpqlJoinFetchDistinctTest() {
		String query = "select distinct i from Instructor i " + "join fetch i.idProof id " + "join fetch i.vehicles v "
				+ "join fetch i.students s " + "join fetch s.instructors si " + "join fetch s.vehicles sv "
				+ "join fetch sv.documents svd " + "where i.id > :id and svd.name in (:names)";
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForJpqlQuery(query,
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3")), Instructor.class));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void jpqlLeftJoinFetchDistinctTest() {
		String query = "select distinct i from Instructor i " + "left join fetch i.idProof id "
				+ "left join fetch i.vehicles v " + "left join fetch i.students s "
				+ "left join fetch s.instructors si " + "left join fetch s.vehicles sv "
				+ "left join fetch sv.documents svd " + "where i.id > :id and svd.name in (:names)";
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForJpqlQuery(query,
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3")), Instructor.class));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void jpqlInnerJoinFetchDistinctTest() {
		String query = "select distinct i from Instructor i " + "inner join fetch i.idProof id "
				+ "inner join fetch i.vehicles v " + "inner join fetch i.students s "
				+ "inner join fetch s.instructors si " + "inner join fetch s.vehicles sv "
				+ "inner join fetch sv.documents svd " + "where i.id > :id and svd.name in (:names)";
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForJpqlQuery(query,
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2", "3")), Instructor.class));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctJoinTest() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof);
		root.join(Instructor_.vehicles);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students);
		insStuJoin.join(Student_.instructors);
		Join<Student, Vehicle> stuVehicleJoin = insStuJoin.join(Student_.vehicles);
		Join<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.join(Vehicle_.documents);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.distinct(true).select(root).where(cb.ge(root.get(Instructor_.id), 2),
						cb.in(vehcileDocJoin.get(Document_.name)).value("1").value("2").value("3"))));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctLeftJoinTest() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof, JoinType.LEFT);
		root.join(Instructor_.vehicles, JoinType.LEFT);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students, JoinType.LEFT);
		insStuJoin.join(Student_.instructors, JoinType.LEFT);
		Join<Student, Vehicle> stuVehicleJoin = insStuJoin.join(Student_.vehicles, JoinType.LEFT);
		Join<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.join(Vehicle_.documents, JoinType.LEFT);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.distinct(true).select(root).where(cb.ge(root.get(Instructor_.id), 2),
						cb.in(vehcileDocJoin.get(Document_.name)).value("1").value("2").value("3"))));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctInnerJoinTest() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof, JoinType.INNER);
		root.join(Instructor_.vehicles, JoinType.INNER);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students, JoinType.INNER);
		insStuJoin.join(Student_.instructors, JoinType.INNER);
		Join<Student, Vehicle> stuVehicleJoin = insStuJoin.join(Student_.vehicles, JoinType.INNER);
		Join<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.join(Vehicle_.documents, JoinType.INNER);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.distinct(true).select(root).where(cb.ge(root.get(Instructor_.id), 2),
						cb.in(vehcileDocJoin.get(Document_.name)).value("1").value("2").value("3"))));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctJoinFetchTest() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.fetch(Instructor_.idProof);
		root.fetch(Instructor_.vehicles);
		Fetch<Instructor, Student> insStuJoin = root.fetch(Instructor_.students);
		insStuJoin.fetch(Student_.instructors);
		Fetch<Student, Vehicle> stuVehicleJoin = insStuJoin.fetch(Student_.vehicles);
		Fetch<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.fetch(Vehicle_.documents);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.distinct(true).select(root).where(cb.ge(root.get(Instructor_.id), 2))));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctLeftJoinFetchTest() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.fetch(Instructor_.idProof, JoinType.LEFT);
		root.fetch(Instructor_.vehicles, JoinType.LEFT);
		Fetch<Instructor, Student> insStuJoin = root.fetch(Instructor_.students, JoinType.LEFT);
		insStuJoin.fetch(Student_.instructors, JoinType.LEFT);
		Fetch<Student, Vehicle> stuVehicleJoin = insStuJoin.fetch(Student_.vehicles, JoinType.LEFT);
		Fetch<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.fetch(Vehicle_.documents, JoinType.LEFT);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.distinct(true).select(root).where(cb.ge(root.get(Instructor_.id), 2))));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctInnerJoinFetchTest() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.fetch(Instructor_.idProof, JoinType.INNER);
		root.fetch(Instructor_.vehicles, JoinType.INNER);
		Fetch<Instructor, Student> insStuJoin = root.fetch(Instructor_.students, JoinType.INNER);
		insStuJoin.fetch(Student_.instructors, JoinType.INNER);
		Fetch<Student, Vehicle> stuVehicleJoin = insStuJoin.fetch(Student_.vehicles, JoinType.INNER);
		Fetch<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.fetch(Vehicle_.documents, JoinType.INNER);
		DataPrinters.listDataPrinter.accept(queryExecutor
				.fetchListForCriteriaQuery(cq.distinct(true).select(root).where(cb.ge(root.get(Instructor_.id), 2))));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctJoinFetchTestEntityGraph() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof);
		root.join(Instructor_.vehicles);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students);
		insStuJoin.join(Student_.instructors);
		Join<Student, Vehicle> stuVehicleJoin = insStuJoin.join(Student_.vehicles);
		Join<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.join(Vehicle_.documents);
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForCriteriaQuery(
				cq.select(root).where(cb.ge(root.get(Instructor_.id), 2),
						cb.in(vehcileDocJoin.get(Document_.name)).value("1").value("2").value("3")),
				entityGraphUtils.fullEntityGraph()));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctLeftJoinFetchTestEntityGraph() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof, JoinType.LEFT);
		root.join(Instructor_.vehicles, JoinType.LEFT);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students, JoinType.LEFT);
		insStuJoin.join(Student_.instructors, JoinType.LEFT);
		Join<Student, Vehicle> stuVehicleJoin = insStuJoin.join(Student_.vehicles, JoinType.LEFT);
		Join<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.join(Vehicle_.documents, JoinType.LEFT);
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForCriteriaQuery(
				cq.select(root).where(cb.ge(root.get(Instructor_.id), 2),
						cb.in(vehcileDocJoin.get(Document_.name)).value("1").value("2").value("3")),
				entityGraphUtils.fullEntityGraph()));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void criteriaDistinctInnerJoinFetchTestEntityGraph() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.idProof, JoinType.INNER);
		root.join(Instructor_.vehicles, JoinType.INNER);
		Join<Instructor, Student> insStuJoin = root.join(Instructor_.students, JoinType.INNER);
		insStuJoin.join(Student_.instructors, JoinType.INNER);
		Join<Student, Vehicle> stuVehicleJoin = insStuJoin.join(Student_.vehicles, JoinType.INNER);
		Join<Vehicle, Document> vehcileDocJoin = stuVehicleJoin.join(Vehicle_.documents, JoinType.INNER);
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForCriteriaQuery(
				cq.select(root).where(cb.ge(root.get(Instructor_.id), 2),
						cb.in(vehcileDocJoin.get(Document_.name)).value("1").value("2").value("3")),
				entityGraphUtils.fullEntityGraph()));
	}

}
