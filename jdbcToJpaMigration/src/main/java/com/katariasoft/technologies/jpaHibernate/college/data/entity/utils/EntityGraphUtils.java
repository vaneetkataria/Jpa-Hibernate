package com.katariasoft.technologies.jpaHibernate.college.data.entity.utils;

import java.util.Objects;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class EntityGraphUtils {

	public static final String FETCH_GRAPH = "javax.persistence.fetchgraph";

	@PersistenceContext
	private EntityManager em;

	public <T> EntityGraph<T> createGraph(Class<T> clazz, String... attributeNames) {
		Objects.requireNonNull(attributeNames);
		EntityGraph<T> graph = em.createEntityGraph(clazz);
		graph.addAttributeNodes(attributeNames);
		return graph;
	}

}
