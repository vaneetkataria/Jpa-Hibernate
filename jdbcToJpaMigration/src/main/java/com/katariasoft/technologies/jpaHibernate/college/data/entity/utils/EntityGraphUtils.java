package com.katariasoft.technologies.jpaHibernate.college.data.entity.utils;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;

import org.springframework.stereotype.Repository;

@Repository
public class EntityGraphUtils {

	public static final String FETCH_GRAPH = "javax.persistence.fetchgraph";

	@PersistenceContext
	private EntityManager em;

	public <T> EntityGraph<T> createGraph(Class<T> clazz, String... attributeNames) {
		EntityGraph<T> graph = em.createEntityGraph(clazz);
		Stream.of(attributeNames).forEach(attributeName -> {
			String[] feilds = attributeName.split("\\.");
			Objects.requireNonNull(feilds.length == 0);
			Subgraph<?> subgraph = graph.addSubgraph(feilds[0]);
			if (feilds.length > 1)
				for (int i = 1; i < feilds.length; i++)
					subgraph = subgraph.addSubgraph(feilds[i]);
		});
		return graph;
	}

}
