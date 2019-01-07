package com.katariasoft.technologies.jpaHibernate.entity.associations;

import static com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils.SINGLE_ID_PROOF_PROVIDER;
import static com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils.SINGLE_INSTRUCTOR_PROVIDER;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.Executable;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.TransactionExecutionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyCrudTests {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private TransactionExecutionTemplate transactionTemplate;

	// @Test
	@Rollback(false)
	public void createTest() {
		doInTransaction(() -> {
			Instructor instructor = SINGLE_INSTRUCTOR_PROVIDER.get();
			instructor.addIdProof(SINGLE_ID_PROOF_PROVIDER.apply("8760_5152_3510"));
			instructor.addVehicles(EntityUtils.MULTIPLE_VEHICLES_PROVIDER.apply("HR02 U570"));
			em.persist(instructor);
		});
	}

	@Test
	@Rollback(false)
	public void readTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1);
			IdProof idProof = instructor.getIdProof();
			Set<Vehicle> vehicles = instructor.getVehicles();
			System.out.println(instructor);
			System.out.println(idProof);
			vehicles.forEach(v -> System.out.println(v.getVehicleNumber()));
		});
	}

	@Test
	@Rollback(false)
	public void updateTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1);
			instructor.setAddress("Updated:" + instructor.getAddress());
			instructor.getIdProof().setAddress("Updated:" + instructor.getIdProof().getAddress());
			instructor.getVehicles().forEach(v -> v.setVehicleNumber("Updated:" + v.getVehicleNumber()));
		});
	}

	@Test
	@Rollback(false)
	public void removeTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1);
			em.remove(instructor);
		});
	}

	@Test
	@Rollback(false)
	public void orphanRemovalTest() {
		doInTransaction(() -> {
			Instructor instructor = em.find(Instructor.class, 1);
			instructor.orphaniseIdProof();
			instructor.orphaniseVehicles();
		});
	}

	private void doInTransaction(Executable executable) {
		transactionTemplate.doInTransaction(executable);
	}

}
