package com.katariasoft.technologies.jpaHibernate.college.data.utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;

import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.utils.EntityGraphUtils;

@Repository
public class QueryExecutor {

	@PersistenceContext
	private EntityManager em;

	public <T> List<T> fetchList(String queryName, Map<String, Object> queryParams, Class<T> clazz) {
		try {
			return fetchList(em.createNamedQuery(queryName, clazz), queryParams);
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> T fetchValue(String queryName, Map<String, Object> queryParams, Class<T> clazz) {
		try {
			return fetchValue(em.createNamedQuery(queryName, clazz), queryParams);
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> List<T> fetchListForJpqlQuery(String queryString, Map<String, Object> queryParams, Class<T> clazz) {
		try {
			return fetchList(em.createQuery(queryString, clazz), queryParams);
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> T fetchSingleResultForJpqlQuery(String queryString, Map<String, Object> queryParams, Class<T> clazz) {
		try {
			return fetchValue(em.createQuery(queryString, clazz), queryParams);
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> List<T> fetchListForJpqlQuery(String queryString, Map<String, Object> queryParams, Class<T> clazz,
			EntityGraph<T> entityGraph) {
		try {
			Objects.requireNonNull(entityGraph, "EntityGraph must not be empty.");
			return fetchList(em.createQuery(queryString, clazz).setHint(EntityGraphUtils.FETCH_GRAPH, entityGraph),
					queryParams);
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> T fetchSingleResultForJpqlQuery(String queryString, Map<String, Object> queryParams, Class<T> clazz,
			EntityGraph<T> entityGraph) {
		try {
			Objects.requireNonNull(entityGraph, "EntityGraph must not be empty.");
			return fetchValue(em.createQuery(queryString, clazz).setHint(EntityGraphUtils.FETCH_GRAPH, entityGraph),
					queryParams);
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> List<T> fetchList(TypedQuery<T> typedQuery, Map<String, Object> queryParams) {
		applyParameters(typedQuery, queryParams);
		return typedQuery.getResultList();
	}

	public <T> T fetchValue(TypedQuery<T> typedQuery, Map<String, Object> queryParams) {
		applyParameters(typedQuery, queryParams);
		return typedQuery.getSingleResult();
	}

	public <T> List<T> fetchListForCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
		return fetchList(em.createQuery(criteriaQuery), null);
	}

	public <T> T fetchValueForCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
		return fetchValue(em.createQuery(criteriaQuery), null);
	}

	public <T> List<T> fetchListForCriteriaQuery(CriteriaQuery<T> criteriaQuery, EntityGraph<T> entityGraph) {
		return fetchList(em.createQuery(criteriaQuery).setHint(EntityGraphUtils.FETCH_GRAPH, entityGraph), null);
	}

	public <T> void executeUpdateWithCriteriaQuery(CriteriaUpdate<T> criteriaUpdate) {
		try {
			em.createQuery(criteriaUpdate).executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> void executeDeleteWithCriteriaQuery(CriteriaDelete<T> criteriaDelete) {
		try {
			em.createQuery(criteriaDelete).executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public void execute(String queryName, Map<String, Object> queryParams) {
		try {
			Query query = em.createNamedQuery(queryName);
			applyParameters(query, queryParams);
			query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	public void executeNativeQuery(String queryString, Map<String, Object> queryParams) {
		try {
			Query query = em.createNativeQuery(queryString);
			applyParameters(query, queryParams);
			query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> fetchListWithNativeQuery(String queryString, Map<String, Object> queryParams, Class<T> clazz) {
		try {
			Query typedQuery = em.createNativeQuery(queryString, clazz);
			applyParameters(typedQuery, queryParams);
			return typedQuery.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

	public static void applyParameters(Query query, Map<String, Object> queryParams) {
		if (Objects.nonNull(queryParams))
			queryParams.forEach((key, value) -> query.setParameter(key, value));
	}

}
