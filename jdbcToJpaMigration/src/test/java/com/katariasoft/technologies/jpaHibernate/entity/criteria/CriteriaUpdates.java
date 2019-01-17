package com.katariasoft.technologies.jpaHibernate.entity.criteria;

import java.math.BigDecimal;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
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
public class CriteriaUpdates {
	@Autowired
	private CriteriaUtils criteriaUtils;
	@Autowired
	private QueryExecutor queryExecutor;

	@Test
	@Transactional
	@Rollback(false)
	public void updateSalaryHavingIdsIn() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaUpdate<Instructor> cq = cb.createCriteriaUpdate(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		executeUpdate(cq.set(Instructor_.salary, BigDecimal.valueOf(150000))
				.where(cb.lessThan(root.get(Instructor_.salary), BigDecimal.valueOf(100000))));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void updateSalaryAndNameHavingFatherNameAndAddressLike() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaUpdate<Instructor> cq = cb.createCriteriaUpdate(Instructor.class);
		Root<Instructor> root = cq.from(Instructor.class);
		executeUpdate(cq.set(root.get(Instructor_.salary), BigDecimal.ZERO)
				.set(root.get(Instructor_.fatherName), "Updated").set(root.get(Instructor_.motherName), "updated")
				.where(cb.like(root.get(Instructor_.fatherName), "%nare%"),
						cb.like(root.get(Instructor_.address), "%#1074%")));

	}

	private <T> void executeUpdate(CriteriaUpdate<T> criteriaUpdate) {
		queryExecutor.executeUpdateWithCriteriaQuery(criteriaUpdate);
	}

}
