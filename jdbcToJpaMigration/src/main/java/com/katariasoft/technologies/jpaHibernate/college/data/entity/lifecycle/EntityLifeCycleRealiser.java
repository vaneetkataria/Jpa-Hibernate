package com.katariasoft.technologies.jpaHibernate.college.data.entity.lifecycle;

import java.util.function.Consumer;

public abstract class EntityLifeCycleRealiser<T> {

	private Consumer<T> updateRealiser = setUpdateRealiser();
	private Consumer<T> updateAndDeleteRealiser = setUpdateAndDeleteRealiser();
	private Consumer<T> updateDeleteFlushRealiser = setUpdateDeleteFlushRealiser();
	private Consumer<T> updateFlushDetachRealiser = setUpdateFlushDetachRealiser();
	private Consumer<T> removingDetachedEntityRealiser = setRemovingDetachedEntityRealiser();
	private Consumer<T> persistingDetachedEntityRealiser = setPersistingDetachedEntityRealiser();
	private Consumer<T> mergingDetachedEntityRealiser = setMergingDetachedEntityRealiser();
	private Consumer<T> detachingRemovedEntityRealiser = setDetachingRemovedEntityRealiser();
	private Consumer<T> persistingRemovedEntityRealiser = setPersistingRemovedEntityRealiser();
	private Consumer<T> detachingRemovedAndFlushedEntityRealiser = setDetachingRemovedAndFlushedEntityRealiser();
	private Consumer<T> persistingRemovedAndFlushedEntityRealiser = setPersistingRemovedAndFlushedEntityRealiser();
	private Consumer<T> mergingRemovedEntityRealiser = setMergingRemovedEntityRealiser();
	private Consumer<T> mergedEntityRealiser = setMergedEntityRealiser();
	private Consumer<T> mergedEntityRealiser_AlreadyPresentInPC = setMergedEntityRealiser_AlreadyPresentInPC();

	public Consumer<T> updateRealiser() {
		return updateRealiser;
	}

	public Consumer<T> updateAndDeleteRealiser() {
		return updateAndDeleteRealiser;
	}

	public Consumer<T> updateDeleteFlushRealiser() {
		return updateDeleteFlushRealiser;
	}

	public Consumer<T> updateFlushDetachRealiser() {
		return updateFlushDetachRealiser;
	}

	public Consumer<T> removingDetachedEntityRealiser() {
		return removingDetachedEntityRealiser;
	}

	public Consumer<T> persistingDetachedEntityRealiser() {
		return persistingDetachedEntityRealiser;
	}

	public Consumer<T> mergingDetachedEntityRealiser() {
		return mergingDetachedEntityRealiser;
	}

	public Consumer<T> detachingRemovedEntityRealiser() {
		return detachingRemovedEntityRealiser;
	}

	public Consumer<T> persistingRemovedEntityRealiser() {
		return persistingRemovedEntityRealiser;
	}

	public Consumer<T> detachingRemovedAndFlushedEntityRealiser() {
		return detachingRemovedAndFlushedEntityRealiser;
	}

	public Consumer<T> persistingRemovedAndFlushedEntityRealiser() {
		return persistingRemovedAndFlushedEntityRealiser;
	}

	public Consumer<T> mergingRemovedEntityRealiser() {
		return mergingRemovedEntityRealiser;
	}

	public Consumer<T> mergedEntityRealiser() {
		return mergedEntityRealiser;
	}

	public Consumer<T> mergedEntityRealiser_AlreadyPresentInPC() {
		return mergedEntityRealiser_AlreadyPresentInPC;
	}

	public abstract Consumer<T> setUpdateRealiser();

	public abstract Consumer<T> setUpdateAndDeleteRealiser();

	public abstract Consumer<T> setUpdateDeleteFlushRealiser();

	public abstract Consumer<T> setUpdateFlushDetachRealiser();

	public abstract Consumer<T> setRemovingDetachedEntityRealiser();

	public abstract Consumer<T> setPersistingDetachedEntityRealiser();

	public abstract Consumer<T> setMergingDetachedEntityRealiser();

	public abstract Consumer<T> setDetachingRemovedEntityRealiser();

	public abstract Consumer<T> setPersistingRemovedEntityRealiser();

	public abstract Consumer<T> setDetachingRemovedAndFlushedEntityRealiser();

	public abstract Consumer<T> setPersistingRemovedAndFlushedEntityRealiser();

	public abstract Consumer<T> setMergingRemovedEntityRealiser();

	public abstract Consumer<T> setMergedEntityRealiser();

	public abstract Consumer<T> setMergedEntityRealiser_AlreadyPresentInPC();

}
