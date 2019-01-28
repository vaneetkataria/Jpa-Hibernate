package com.katariasoft.technologies.jpaHibernate.entity.fetch;

import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityGraphUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StackOveflowQuestion {

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	@Rollback(false)
	public void fetchTest1() {
		// join fetch i.students fetchTest4s join fetch s.vehicles sv join fetch sv.documents svd
		EntityGraph<Instructor> eg = em.createEntityGraph(Instructor.class);
		eg.addAttributeNodes(Instructor_.vehicles);

		List<Instructor> instructorsJpqlJoinFetch = em
				.createQuery("select distinct i from Instructor i join fetch i.vehicles v ", Instructor.class)
				/*.setHint(EntityGraphUtils.FETCH_GRAPH, eg)*/.getResultList();
		print(instructorsJpqlJoinFetch);

	}

	@Test
	@Transactional
	@Rollback(false)
	public void fetchTest2() {
		EntityGraph<Instructor> eg = em.createEntityGraph(Instructor.class);
		eg.addAttributeNodes(Instructor_.vehicles);

		List<Instructor> instructorsJpqlLeftJoin = em
				.createQuery("select distinct i from Instructor i left join i.vehicles v ", Instructor.class)
				/*.setHint(EntityGraphUtils.FETCH_GRAPH, eg)*/.getResultList();
		print(instructorsJpqlLeftJoin);

	}

	@Test
	@Transactional
	@Rollback(false)
	public void fetchTest3() {
		EntityGraph<Instructor> eg = em.createEntityGraph(Instructor.class);
		eg.addAttributeNodes(Instructor_.vehicles);

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		root.join(Instructor_.vehicles);
		List<Instructor> instructorsWithCriteria = em.createQuery(cq.distinct(true).select(root))
				.setHint(EntityGraphUtils.FETCH_GRAPH, eg).getResultList();
		print(instructorsWithCriteria);

	}

	@Test
	@Transactional
	@Rollback(false)
	public void fetchTest4() {
		EntityGraph<Instructor> eg = em.createEntityGraph(Instructor.class);
		eg.addAttributeNodes(Instructor_.vehicles);

		CriteriaBuilder cbLeftJoin = em.getCriteriaBuilder();
		CriteriaQuery<Instructor> cqLeftJoin = cbLeftJoin.createQuery(Instructor.class);
		Root<Instructor> rootLeftJoin = cqLeftJoin.from(Instructor.class);
		rootLeftJoin.join(Instructor_.vehicles, JoinType.LEFT);
		List<Instructor> instructorsWithCriteriaLeftJoin = em
				.createQuery(cqLeftJoin.distinct(true).select(rootLeftJoin)).setHint(EntityGraphUtils.FETCH_GRAPH, eg)
				.getResultList();
		print(instructorsWithCriteriaLeftJoin);

	}

	private void print(List<Instructor> instructors) {
		instructors.forEach(i -> {
			System.out.println("######Instructor Name : " + i.getName());
			i.getVehicles().forEach(v -> {
				System.out.println("######Instructor Vehicle Number : " + v.getVehicleNumber());
			});

		});

	}

}
