package com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;

@Repository
public class IdProofLifeCycleRealiser extends EntityLifeCycleRealiser<IdProof> {

	@PersistenceContext
	public EntityManager em;

	public Consumer<IdProof> setUpdateRealiser() {
		return idProof -> {
			// update
			idProof.setAddress("ruRealiser");
			idProof.setAddress("ruRealiser");
			idProof.setMotherName("ruRealiser");
			idProof.setFatherName("ruRealiser");
		};
	}

	public Consumer<IdProof> setUpdateAndDeleteRealiser() {
		return idProof -> {
			idProof.setAddress("rudRealiser");
			idProof.setAddress("rudRealiser");
			idProof.setMotherName("rudRealiser");
			idProof.setFatherName("rudRealiser");
			// remove any as both are same objects fetched from P.C.
			em.remove(idProof);
		};

	}

	public Consumer<IdProof> setUpdateDeleteFlushRealiser() {
		return idProof -> {
			idProof.setAddress("rud_Flush_Realiser");
			em.flush();
			idProof.setAddress("rud_Flush_Realiser");
			em.flush();
			idProof.setMotherName("rud_Flush_Realiser");
			em.flush();
			idProof.setFatherName("rud_Flush_Realiser");
			em.flush();
			// remove any as both are same objects fetched from P.C.
			em.remove(idProof);
			em.flush();
		};
	}

	public Consumer<IdProof> setUpdateFlushDetachRealiser() {
		return idProof -> {
			idProof.setAddress("ru_Flush_Detach_Realiser");
			em.flush();
			idProof.setAddress("ru_Flush_Detach_Realiser");
			em.flush();
			em.detach(idProof);
			idProof.setMotherName("ru_Flush_Detach_Realiser");
			idProof.setFatherName("ru_Flush_Detach_Realiser");
			idProof.setMotherName("ru_Flush_Detach_Realiser");
			idProof.setFatherName("ru_Flush_Detach_Realiser");
		};
	}

	public Consumer<IdProof> setRemovingDetachedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("removingDetachedEntityRealiser");
			em.flush();
			idProof.setAddress("removingDetachedEntityRealiser");
			em.flush();
			em.detach(idProof);
			idProof.setMotherName("removingDetachedEntityRealiser");
			idProof.setFatherName("removingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.remove(idProof);
			em.flush();
		};
	}

	public Consumer<IdProof> setPersistingDetachedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("persistingDetachedEntityRealiser");
			em.flush();
			idProof.setAddress("persistingDetachedEntityRealiser");
			em.flush();
			em.detach(idProof);
			idProof.setMotherName("persistingDetachedEntityRealiser");
			idProof.setFatherName("persistingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.persist(idProof);
		};
	}

	public Consumer<IdProof> setMergingDetachedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("mergingDetachedEntityRealiser");
			em.flush();
			idProof.setAddress("mergingDetachedEntityRealiser");
			em.flush();
			em.detach(idProof);
			idProof.setMotherName("mergingDetachedEntityRealiser");
			idProof.setFatherName("mergingDetachedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.merge(idProof);
		};

	}

	public Consumer<IdProof> setDetachingRemovedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("detachingRemovedEntityRealiser");
			em.flush();
			idProof.setAddress("detachingRemovedEntityRealiser");
			em.flush();
			em.remove(idProof);
			idProof.setMotherName("detachingRemovedEntityRealiser");
			idProof.setFatherName("detachingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.detach(idProof);
		};

	}

	public Consumer<IdProof> setPersistingRemovedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("persistingRemovedEntityRealiser");
			em.flush();
			idProof.setAddress("persistingRemovedEntityRealiser");
			em.flush();
			em.remove(idProof);
			idProof.setMotherName("persistingRemovedEntityRealiser");
			idProof.setFatherName("persistingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.persist(idProof);
		};
	}

	public Consumer<IdProof> setDetachingRemovedAndFlushedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			idProof.setAddress("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(idProof);
			idProof.setMotherName("detachingRemovedAndFlushedEntityRealiser");
			idProof.setFatherName("detachingRemovedAndFlushedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.flush();
			em.detach(idProof);
		};

	}

	public Consumer<IdProof> setPersistingRemovedAndFlushedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			idProof.setAddress("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(idProof);
			idProof.setMotherName("persistingRemovedAndFlushedEntityRealiser");
			idProof.setFatherName("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			// remove any as both are same objects fetched from P.C.

			em.persist(idProof);
		};

	}

	public Consumer<IdProof> setMergingRemovedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			idProof.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			em.remove(idProof);
			idProof.setMotherName("mergingRemovedEntityRealiser");
			idProof.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			em.merge(idProof);
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

	public Consumer<IdProof> setMergedEntityRealiser() {
		return idProof -> {
			idProof.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			idProof.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			idProof.setMotherName("mergingRemovedEntityRealiser");
			idProof.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			IdProof mergedInstance = em.merge(idProof);
			idProof.setAddress("Address set in input entity merged");
			idProof.setFatherName("Father name set in input entity merged");
			idProof.setMotherName("Mother name set in input entity merged");
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

	public Consumer<IdProof> setMergedEntityRealiser_AlreadyPresentInPC() {
		return idProof -> {
			IdProof fromDb = em.find(IdProof.class, 2);
			System.out.println(fromDb);
			idProof.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			idProof.setAddress("mergingRemovedEntityRealiser");
			em.flush();
			idProof.setMotherName("mergingRemovedEntityRealiser");
			idProof.setFatherName("mergingRemovedEntityRealiser");
			// remove any as both are same objects fetched from P.C.
			IdProof mergedInstance = em.merge(idProof);
			idProof.setAddress("Address set in input entity merged");
			idProof.setFatherName("Father name set in input entity merged");
			idProof.setMotherName("Mother name set in input entity merged");
			// set in merged returned instance .
			mergedInstance.setAddress("Address set in merged and managed entity");
			mergedInstance.setFatherName("Father name set in merged and managed entity");
			mergedInstance.setMotherName("Mother name set in merged and managed entity");
		};
	}

}
