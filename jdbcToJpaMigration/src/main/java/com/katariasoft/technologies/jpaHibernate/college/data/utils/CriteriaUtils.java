package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class CriteriaUtils {

	@PersistenceContext
	private EntityManager em;

	public CriteriaBuilder criteriaBuilder() {
		return em.getCriteriaBuilder();
	}

	public <T> CriteriaQuery<T> criteriaQuery(Class<T> clazz) {
		return criteriaBuilder().createQuery(clazz);
	}

	public <T> Root<T> criteriaRoot(Class<T> clazz) {
		return criteriaQuery(clazz).from(clazz);
	}

}
