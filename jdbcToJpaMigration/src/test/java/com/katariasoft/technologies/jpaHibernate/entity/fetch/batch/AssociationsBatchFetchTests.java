package com.katariasoft.technologies.jpaHibernate.entity.fetch.batch;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationsBatchFetchTests {

	@Autowired
	private QueryExecutor queryExecutor;

	@Test
	@Transactional
	@Rollback(false)
	public void fetchAssociationInBatches() {
		List<Instructor> instructors = queryExecutor.fetchListForJpqlQuery("select i from Instructor i ", null,
				Instructor.class);
		instructors.forEach(i -> {
			System.out.println("###########Instructor Name :" + i.getName());
			i.getVehicles().forEach(v -> {
				System.out.println("############Vehicle Number " + v.getVehicleNumber());
				v.getDocuments().forEach(d -> {
					System.out.println("############Vehicle document Name " + d.getName());
				});
			});

			i.getStudents().forEach(s -> {
				System.out.println("###########Student Name :" + i.getName());
				s.getVehicles().forEach(v -> {
					System.out.println("############Student Vehicle Number " + v.getVehicleNumber());
					v.getDocuments().forEach(d -> {
						System.out.println("############Student Vehicle document Name " + d.getName());
					});
				});
			});
		});

	}

}
