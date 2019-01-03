package com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;

@Repository
public class InstructorLifeCycleRealiser extends EntityLifeCycleRealiser<Instructor> {

	@PersistenceContext
	private EntityManager em;

	protected Consumer<Instructor> setUpdateRealiser() {
		return instructor -> {
			// update
			instructor.setAddress("ruRealiser");
			instructor.setAddress("ruRealiser");
			instructor.setMotherName("ruRealiser");
			instructor.setFatherName("ruRealiser");
		};
	}

	protected Consumer<Instructor> setUpdateAndDeleteRealiser() {
		return instructor -> {
			instructor.setAddress("rudRealiser");
			instructor.setAddress("rudRealiser");
			instructor.setMotherName("rudRealiser");
			instructor.setFatherName("rudRealiser");
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
		};

	}

	protected Consumer<Instructor> setUpdateDeleteFlushRealiser() {
		return instructor -> {
			instructor.setAddress("rud_Flush_Realiser");
			em.flush();
			instructor.setAddress("rud_Flush_Realiser");
			em.flush();
			instructor.setMotherName("rud_Flush_Realiser");
			em.flush();
			instructor.setFatherName("rud_Flush_Realiser");
			em.flush();
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
			em.flush();
		};
	}

	protected Consumer<Instructor> setUpdateFlushDetachRealiser() {
		return instructor -> {
			instructor.setAddress("ru_Flush_Detach_Realiser");
			em.flush();
			instructor.setAddress("ru_Flush_Detach_Realiser");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("ru_Flush_Detach_Realiser");
			instructor.setFatherName("ru_Flush_Detach_Realiser");
			instructor.setMotherName("ru_Flush_Detach_Realiser");
			instructor.setFatherName("ru_Flush_Detach_Realiser");
		};
	}

	protected Consumer<Instructor> setRemovingDetachedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("removingDetachedEntityRealiser");
			em.flush();
			instructor.setAddress("removingDetachedEntityRealiser");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("removingDetachedEntityRealiser");
			instructor.setFatherName("removingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.remove(instructor);
			em.flush();
		};
	}

	protected Consumer<Instructor> setPersistingDetachedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("persistingDetachedEntityRealiser");
			em.flush();
			instructor.setAddress("persistingDetachedEntityRealiser");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("persistingDetachedEntityRealiser");
			instructor.setFatherName("persistingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.persist(instructor);
		};
	}

	protected Consumer<Instructor> setMergingDetachedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("mergingDetachedEntityRealiser");
			em.flush();
			instructor.setAddress("mergingDetachedEntityRealiser");
			em.flush();
			em.detach(instructor);
			instructor.setMotherName("mergingDetachedEntityRealiser");
			instructor.setFatherName("mergingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.merge(instructor);
		};

	}

	protected Consumer<Instructor> setDetachingRemovedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("detachingRemovedEntityRealiser");
			em.flush();
			instructor.setAddress("detachingRemovedEntityRealiser");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("detachingRemovedEntityRealiser");
			instructor.setFatherName("detachingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.detach(instructor);
		};

	}

	protected Consumer<Instructor> setPersistingRemovedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("persistingRemovedEntityRealiser");
			em.flush();
			instructor.setAddress("persistingRemovedEntityRealiser");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("persistingRemovedEntityRealiser");
			instructor.setFatherName("persistingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.persist(instructor);
		};
	}

	protected Consumer<Instructor> setDetachingRemovedAndFlushedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			instructor.setAddress("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("detachingRemovedAndFlushedEntityRealiser");
			instructor.setFatherName("detachingRemovedAndFlushedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.flush();
			em.detach(instructor);
		};

	}

	protected Consumer<Instructor> setPersistingRemovedAndFlushedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			instructor.setAddress("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("persistingRemovedAndFlushedEntityRealiser");
			instructor.setFatherName("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			// remove any as both are same objects fetched from P.C.

			em.persist(instructor);
		};

	}

	protected Consumer<Instructor> setMergingRemovedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			instructor.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			em.remove(instructor);
			instructor.setMotherName("mergingRemovedEntityRealiser");
			instructor.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.merge(instructor);
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

	protected Consumer<Instructor> setMergedEntityRealiser() {
		return instructor -> {
			instructor.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			instructor.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			instructor.setMotherName("mergingRemovedEntityRealiser");
			instructor.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			Instructor mergedInstance = em.merge(instructor);
			instructor.setAddress("Address set in input entity merged");
			instructor.setFatherName("Father name set in input entity merged");
			instructor.setMotherName("Mother name set in input entity merged");
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

	protected Consumer<Instructor> setMergedEntityRealiser_AlreadyPresentInPC() {
		return instructor -> {
			Instructor fromDb = em.find(Instructor.class, 1);
			System.out.println(fromDb);
			instructor.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			instructor.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			instructor.setMotherName("mergingRemovedEntityRealiser");
			instructor.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			Instructor mergedInstance = em.merge(instructor);
			instructor.setAddress("Address set in input entity merged");
			instructor.setFatherName("Father name set in input entity merged");
			instructor.setMotherName("Mother name set in input entity merged");
			// set in merged returned instance .
			mergedInstance.setAddress("Address set in merged and managed entity");
			mergedInstance.setFatherName("Father name set in merged and managed entity");
			mergedInstance.setMotherName("Mother name set in merged and managed entity");
		};
	}

}
