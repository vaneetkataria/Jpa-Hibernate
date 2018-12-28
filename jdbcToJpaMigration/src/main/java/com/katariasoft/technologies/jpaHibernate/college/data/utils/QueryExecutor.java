package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class QueryExecutor {

	@PersistenceContext
	private EntityManager em;

	public <T> List<T> fetchList(String queryName, Map<String, Object> queryParams, Class<T> clazz) {
		try {
			TypedQuery<T> typedQuery = em.createNamedQuery(queryName, clazz);
			applyParameters(typedQuery, queryParams);
			return typedQuery.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> T fetchValue(String queryName, Map<String, Object> queryParams, Class<T> clazz) {
		try {
			TypedQuery<T> typedQuery = em.createNamedQuery(queryName, clazz);
			applyParameters(typedQuery, queryParams);
			return typedQuery.getSingleResult();
		} catch (Exception e) {
			throw e;
		}
	}

	public void executeQuery(String queryName, Map<String, Object> queryParams) {
		try {
			Query query = em.createNamedQuery(queryName);
			applyParameters(query, queryParams);
			query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void applyParameters(Query query, Map<String, Object> queryParams) {
		if (Objects.nonNull(queryParams))
			queryParams.forEach((key, value) -> query.setParameter(key, value));
	}

}
