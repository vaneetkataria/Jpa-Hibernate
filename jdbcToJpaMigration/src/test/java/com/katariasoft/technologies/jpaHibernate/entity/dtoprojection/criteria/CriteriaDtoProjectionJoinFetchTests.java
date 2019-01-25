package com.katariasoft.technologies.jpaHibernate.entity.dtoprojection.criteria;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.katariasoft.technologies.jpaHibernate.college.data.dto.InstructorDto;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.Document;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.Document_;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.CriteriaUtils;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.DataPrinters;
import com.katariasoft.technologies.jpaHibernate.college.data.utils.QueryExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaDtoProjectionJoinFetchTests {

	@Autowired
	private QueryExecutor queryExecutor;
	@Autowired
	private CriteriaUtils criteriaUtils;

	@Test
	public void fetchFullDataWithCriteria() {
		CriteriaBuilder cb = criteriaUtils.criteriaBuilder();
		CriteriaQuery<InstructorDto> cq = cb.createQuery(InstructorDto.class);

		// prepare from expressions
		Root<Instructor> root = cq.from(Instructor.class);
		Join<Instructor, IdProof> insIdProofJoin = root.join(Instructor_.idProof);
		Join<Instructor, Vehicle> insVehicleJoin = root.join(Instructor_.vehicles);
		Join<Instructor, Student> insStudentJoin = root.join(Instructor_.students);
		Join<Student, Instructor> studentInsJoin = insStudentJoin.join(Student_.instructors);
		Join<Student, Vehicle> studentVehicleJoin = insStudentJoin.join(Student_.vehicles);
		Join<Vehicle, Document> vehicleDocumentJoin = studentVehicleJoin.join(Vehicle_.documents);

		// prepare select expressions.
		CompoundSelection<InstructorDto> selection = cb.construct(InstructorDto.class, root.get(Instructor_.id),
				root.get(Instructor_.name), root.get(Instructor_.fatherName), root.get(Instructor_.address),
				insIdProofJoin.get(IdProof_.proofNo), insVehicleJoin.get(Vehicle_.vehicleNumber),
				insVehicleJoin.get(Vehicle_.vechicleType), insStudentJoin.get(Student_.name),
				insStudentJoin.get(Student_.fatherName), studentInsJoin.get(Instructor_.name),
				studentVehicleJoin.get(Vehicle_.vehicleNumber), vehicleDocumentJoin.get(Document_.name));

		// prepare where expressions.
		Predicate instructorIdGreaterThan = cb.greaterThan(root.get(Instructor_.id), 2);
		Predicate documentNameIn = cb.in(vehicleDocumentJoin.get(Document_.name)).value("1").value("2");
		Predicate where = cb.and(instructorIdGreaterThan, documentNameIn);

		// prepare orderBy expressions.
		List<Order> orderBy = Arrays.asList(cb.asc(root.get(Instructor_.id)),
				cb.asc(insIdProofJoin.get(IdProof_.proofNo)), cb.asc(insVehicleJoin.get(Vehicle_.vehicleNumber)),
				cb.asc(studentInsJoin.get(Instructor_.name)), cb.asc(studentVehicleJoin.get(Vehicle_.vehicleNumber)),
				cb.asc(vehicleDocumentJoin.get(Document_.name)));

		// prepare query
		cq.select(selection).where(where).orderBy(orderBy);
		DataPrinters.listDataPrinter.accept(queryExecutor.fetchListForCriteriaQuery(cq));

	}

}
