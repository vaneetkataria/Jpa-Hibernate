package com.katariasoft.technologies.jpaHibernate.entity.criteria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle_;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriasJoinsTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	@SuppressWarnings("unchecked")
	@Test
	@Rollback(false)
	public void fetchInstructorsWithIdProof() {
		doInTransaction(() -> {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Instructor> cq = cb.createQuery(Instructor.class);
			Root<Instructor> root = cq.from(Instructor.class);
			root.join(Instructor_.idProof, JoinType.LEFT);
			root.join(Instructor_.vehicles, JoinType.LEFT);
			SetJoin<Instructor, Student> instructorStudentsJoin = root.join(Instructor_.students, JoinType.LEFT);
			instructorStudentsJoin.join(Student_.instructors, JoinType.LEFT);
			SetJoin<Student, Vehicle> StudentsVehcileJoin = instructorStudentsJoin.join(Student_.vehicles,
					JoinType.LEFT);
			StudentsVehcileJoin.join(Vehicle_.documents, JoinType.LEFT);
			cq.select(root);
			TypedQuery<Instructor> query = em.createQuery(cq);
			DataPrinters.listDataPrinter.accept(query.getResultList());
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
