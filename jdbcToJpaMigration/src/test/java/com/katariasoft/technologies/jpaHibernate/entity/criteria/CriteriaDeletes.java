package com.katariasoft.technologies.jpaHibernate.entity.criteria;

import java.util.Arrays;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CriteriaUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaDeletes {
	@Autowired
	private CriteriaUtils criteriaUtils;
	@Autowired
	private QueryExecutor queryExecutor;

	@Test
	@Transactional
	@Rollback(false)
	public void deleteHavingIdsIn() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaDelete<Instructor> cq = cb.createCriteriaDelete(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		In<Integer> in = cb.in(root.get(Instructor_.id));
		Arrays.asList(1, 2, 3).forEach(i -> in.value(i));
		executeDelete(cq.where(in));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void deleteHavingFatherNameAndAddressLike() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaDelete<Instructor> cq = cb.createCriteriaDelete(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		executeDelete(cq.where(cb.like(root.get(Instructor_.fatherName), "%ram%"),
				cb.like(root.get(Instructor_.address), "%#1074%")));

	}

	private <T> void executeDelete(CriteriaDelete<T> criteriaDelete) {
		queryExecutor.executeDeleteWithCriteriaQuery(criteriaDelete);
	}

}
