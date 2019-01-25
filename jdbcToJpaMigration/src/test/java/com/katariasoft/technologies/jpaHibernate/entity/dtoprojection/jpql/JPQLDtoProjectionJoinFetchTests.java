package com.katariasoft.technologies.jpaHibernate.entity.dtoprojection.jpql;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.dto.InstructorDto;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CollectionUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLDtoProjectionJoinFetchTests {

	@Autowired
	private QueryExecutor queryExecutor;

	@Test
	@Rollback(false)
	@Transactional
	public void fetchFullDataWithJPQLQuery() {

		String dtoProjection = "new com.katariasoft.technologies.jpaHibernate.college.data.dto.InstructorDto"
				+ "(i.id, i.name, i.fatherName, i.address, id.proofNo, "
				+ " v.vehicleNumber, v.vechicleType, s.name, s.fatherName, "
				+ " si.name, sv.vehicleNumber , svd.name) ";

		List<InstructorDto> instructors = queryExecutor.fetchListForJpqlQuery(
				"select " + dtoProjection + " from Instructor i " + " join i.idProof id " + " join i.vehicles v "
						+ " join i.students s " + " join s.instructors si " + " join s.vehicles sv "
						+ " join sv.documents svd " + " where i.id > :id and svd.name in (:names) "
						+ " order by i.id , id.proofNo , v.vehicleNumber , si.name , sv.vehicleNumber , svd.name ",
				CollectionUtils.mapOf("id", 2, "names", Arrays.asList("1", "2")), InstructorDto.class);

		if (Objects.nonNull(instructors))
			instructors.forEach(i -> i.setName("Latest Update"));

		DataPrinters.listDataPrinter.accept(instructors);
	}

}
