package com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;

@Repository
public class StudentLifeCycleRealiser extends EntityLifeCycleRealiser<Student> {

	@PersistenceContext
	private EntityManager em;

	protected Consumer<Student> setUpdateRealiser() {
		return student -> {
			// update
			student.setAddress("ruRealiser");
			student.setAddress("ruRealiser");
			student.setMotherName("ruRealiser");
			student.setFatherName("ruRealiser");
		};
	}

	protected Consumer<Student> setUpdateAndDeleteRealiser() {
		return student -> {
			student.setAddress("rudRealiser");
			student.setAddress("rudRealiser");
			student.setMotherName("rudRealiser");
			student.setFatherName("rudRealiser");
			// remove any as both are same objects fetched from P.C.
			em.remove(student);
		};

	}

	protected Consumer<Student> setUpdateDeleteFlushRealiser() {
		return student -> {
			student.setAddress("rud_Flush_Realiser");
			em.flush();
			student.setAddress("rud_Flush_Realiser");
			em.flush();
			student.setMotherName("rud_Flush_Realiser");
			em.flush();
			student.setFatherName("rud_Flush_Realiser");
			em.flush();
			// remove any as both are same objects fetched from P.C.
			em.remove(student);
			em.flush();
		};
	}

	protected Consumer<Student> setUpdateFlushDetachRealiser() {
		return student -> {
			student.setAddress("ru_Flush_Detach_Realiser");
			em.flush();
			student.setAddress("ru_Flush_Detach_Realiser");
			em.flush();
			em.detach(student);
			student.setMotherName("ru_Flush_Detach_Realiser");
			student.setFatherName("ru_Flush_Detach_Realiser");
			student.setMotherName("ru_Flush_Detach_Realiser");
			student.setFatherName("ru_Flush_Detach_Realiser");
		};
	}

	protected Consumer<Student> setRemovingDetachedEntityRealiser() {
		return student -> {
			student.setAddress("removingDetachedEntityRealiser");
			em.flush();
			student.setAddress("removingDetachedEntityRealiser");
			em.flush();
			em.detach(student);
			student.setMotherName("removingDetachedEntityRealiser");
			student.setFatherName("removingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.remove(student);
			em.flush();
		};
	}

	protected Consumer<Student> setPersistingDetachedEntityRealiser() {
		return student -> {
			student.setAddress("persistingDetachedEntityRealiser");
			em.flush();
			student.setAddress("persistingDetachedEntityRealiser");
			em.flush();
			em.detach(student);
			student.setMotherName("persistingDetachedEntityRealiser");
			student.setFatherName("persistingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.persist(student);
		};
	}

	protected Consumer<Student> setMergingDetachedEntityRealiser() {
		return student -> {
			student.setAddress("mergingDetachedEntityRealiser");
			em.flush();
			student.setAddress("mergingDetachedEntityRealiser");
			em.flush();
			em.detach(student);
			student.setMotherName("mergingDetachedEntityRealiser");
			student.setFatherName("mergingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.merge(student);
		};

	}

	protected Consumer<Student> setDetachingRemovedEntityRealiser() {
		return student -> {
			student.setAddress("detachingRemovedEntityRealiser");
			em.flush();
			student.setAddress("detachingRemovedEntityRealiser");
			em.flush();
			em.remove(student);
			student.setMotherName("detachingRemovedEntityRealiser");
			student.setFatherName("detachingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.detach(student);
		};

	}

	protected Consumer<Student> setPersistingRemovedEntityRealiser() {
		return student -> {
			student.setAddress("persistingRemovedEntityRealiser");
			em.flush();
			student.setAddress("persistingRemovedEntityRealiser");
			em.flush();
			em.remove(student);
			student.setMotherName("persistingRemovedEntityRealiser");
			student.setFatherName("persistingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.persist(student);
		};
	}

	protected Consumer<Student> setDetachingRemovedAndFlushedEntityRealiser() {
		return student -> {
			student.setAddress("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			student.setAddress("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(student);
			student.setMotherName("detachingRemovedAndFlushedEntityRealiser");
			student.setFatherName("detachingRemovedAndFlushedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.flush();
			em.detach(student);
		};

	}

	protected Consumer<Student> setPersistingRemovedAndFlushedEntityRealiser() {
		return student -> {
			student.setAddress("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			student.setAddress("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(student);
			student.setMotherName("persistingRemovedAndFlushedEntityRealiser");
			student.setFatherName("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			// remove any as both are same objects fetched from P.C.

			em.persist(student);
		};

	}

	protected Consumer<Student> setMergingRemovedEntityRealiser() {
		return student -> {
			student.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			student.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			em.remove(student);
			student.setMotherName("mergingRemovedEntityRealiser");
			student.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.merge(student);
		};

	}

	// 1. Merge method looks up to database if entity with same id and version is
	// found then a new object is created for that in PC and input entity state is
	// merged into it .
	// 2. New entity becomes persistent and input entity continue remain unmanaged
	// by
	// PC
	// 3.If record is not present in db even then a new object is created and input
	// entity state is
	// merged into it .
	// 4. Merging input entity never becomes persistent .
	// 5. If an entity already present in PC then no db lookup will happen and
	// input entity will be merge into that . Input entity continue remaining
	// unmanaged .

	protected Consumer<Student> setMergedEntityRealiser() {
		return student -> {
			student.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			student.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			student.setMotherName("mergingRemovedEntityRealiser");
			student.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			Student mergedInstance = em.merge(student);
			student.setAddress("Address set in input entity merged");
			student.setFatherName("Father name set in input entity merged");
			student.setMotherName("Mother name set in input entity merged");
			// set in merged returned instance .
			mergedInstance.setAddress("Address set in merged and managed entity");
			mergedInstance.setFatherName("Father name set in merged and managed entity");
			mergedInstance.setMotherName("Mother name set in merged and managed entity");
		};

	}

	// 1. Merge method looks up to database if entity with same id and version is
	// found then a new object is created for that in PC and input entity state is
	// merged into it .
	// 2. New entity becomes persistent and input entity continue remain unmanaged
	// by
	// PC
	// 3.If record is not present in db even then a new object is created and input
	// entity state is
	// merged into it .
	// 4. Merging input entity never becomes persistent .
	// 5. If an entity already present in PC then no db lookup will happen and
	// input entity will be merge into that . Input entity continue remaining
	// unmanaged .

	protected Consumer<Student> setMergedEntityRealiser_AlreadyPresentInPC() {
		return student -> {
			Student fromDb = em.find(Student.class, 2);
			System.out.println(fromDb);
			student.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			student.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			student.setMotherName("mergingRemovedEntityRealiser");
			student.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			Student mergedInstance = em.merge(student);
			student.setAddress("Address set in input entity merged");
			student.setFatherName("Father name set in input entity merged");
			student.setMotherName("Mother name set in input entity merged");
			// set in merged returned instance .
			mergedInstance.setAddress("Address set in merged and managed entity");
			mergedInstance.setFatherName("Father name set in merged and managed entity");
			mergedInstance.setMotherName("Mother name set in merged and managed entity");
		};
	}

}
