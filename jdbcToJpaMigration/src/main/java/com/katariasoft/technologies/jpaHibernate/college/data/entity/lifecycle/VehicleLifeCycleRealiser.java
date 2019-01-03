package com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.enums.VechicleType;

@Repository
public class VehicleLifeCycleRealiser extends EntityLifeCycleRealiser<Vehicle> {

	@PersistenceContext
	public EntityManager em;

	public Consumer<Vehicle> setUpdateRealiser() {
		return vehicle -> {
			// update
			vehicle.setVehicleNumber("ruRealiser");
		};
	}

	public Consumer<Vehicle> setUpdateAndDeleteRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("ruRealiser");
			// remove any as both are same objects fetched from P.C.
			em.remove(vehicle);
		};

	}

	public Consumer<Vehicle> setUpdateDeleteFlushRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("rud_Flush_Realiser");
			em.flush();
			vehicle.setVehicleNumber("rud_Flush_Realiser");
			em.flush();
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			em.flush();
			// remove any as both are same objects fetched from P.C.
			em.remove(vehicle);
			em.flush();
		};
	}

	public Consumer<Vehicle> setUpdateFlushDetachRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("ru_Flush_Detach_Realiser");
			em.flush();
			vehicle.setVehicleNumber("ru_Flush_Detach_Realiser");
			em.flush();
			em.detach(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
		};
	}

	public Consumer<Vehicle> setRemovingDetachedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("removingDetachedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("removingDetachedEntityRealiser");
			em.flush();
			em.detach(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			em.remove(vehicle);
			em.flush();
		};
	}

	public Consumer<Vehicle> setPersistingDetachedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("persistingDetachedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("persistingDetachedEntityRealiser");
			em.flush();
			em.detach(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			em.persist(vehicle);
		};
	}

	public Consumer<Vehicle> setMergingDetachedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("mergingDetachedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("mergingDetachedEntityRealiser");
			em.flush();
			em.detach(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			em.merge(vehicle);
		};

	}

	public Consumer<Vehicle> setDetachingRemovedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("detachingRemovedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("detachingRemovedEntityRealiser");
			em.flush();
			em.remove(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			em.detach(vehicle);
		};

	}

	public Consumer<Vehicle> setPersistingRemovedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("persistingRemovedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("persistingRemovedEntityRealiser");
			em.flush();
			em.remove(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			em.persist(vehicle);
		};
	}

	public Consumer<Vehicle> setDetachingRemovedAndFlushedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("detachingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			em.flush();
			em.detach(vehicle);
		};

	}

	public Consumer<Vehicle> setPersistingRemovedAndFlushedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("persistingRemovedAndFlushedEntityRealiser");
			em.flush();
			em.remove(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			em.flush();
			// remove any as both are same objects fetched from P.C.

			em.persist(vehicle);
		};

	}

	public Consumer<Vehicle> setMergingRemovedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("mergingRemovedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("mergingRemovedEntityRealiser");
			em.flush();
			em.remove(vehicle);
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			em.merge(vehicle);
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

	public Consumer<Vehicle> setMergedEntityRealiser() {
		return vehicle -> {
			vehicle.setVehicleNumber("mergingRemovedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("mergingRemovedEntityRealiser");
			em.flush();
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// remove any as both are same objects fetched from P.C.
			Vehicle mergedInstance = em.merge(vehicle);
			vehicle.setVehicleNumber("Address set in input entity merged");
			vehicle.setVechicleType(VechicleType.EIGHT_WHEELER);
			// set in merged returned instance .
			mergedInstance.setVehicleNumber("Address set in merged and managed entity");
			vehicle.setVechicleType(VechicleType.SIX_WHEELER);
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

	public Consumer<Vehicle> setMergedEntityRealiser_AlreadyPresentInPC() {
		return vehicle -> {
			Vehicle fromDb = em.find(Vehicle.class, 2);
			System.out.println(fromDb);
			vehicle.setVehicleNumber("mergingRemovedEntityRealiser");
			em.flush();
			vehicle.setVehicleNumber("mergingRemovedEntityRealiser");
			em.flush();
			vehicle.setVechicleType(VechicleType.SIX_WHEELER);
			// remove any as both are same objects fetched from P.C.
			Vehicle mergedInstance = em.merge(vehicle);
			vehicle.setVehicleNumber("Address set in input entity merged");
			vehicle.setVechicleType(VechicleType.SIX_WHEELER);
			// set in merged returned instance .
			mergedInstance.setVehicleNumber("Address set in merged and managed entity");
			vehicle.setVechicleType(VechicleType.TWO_WHEELER);
		};
	}

}
