package com.katariasoft.technologies.jpaHibernate.college.data.entity.utils;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;

import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.IdProof;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;

@Repository
public class EntityGraphUtils {

	public static final String FETCH_GRAPH = "javax.persistence.fetchgraph";

	@PersistenceContext
	private EntityManager em;

	public <T> EntityGraph<T> createGraph(Class<T> clazz, String... attributeNames) {
		EntityGraph<T> graph = em.createEntityGraph(clazz);
		graph.addAttributeNodes(attributeNames);
		return graph;
	}

	public EntityGraph<Instructor> createGraph(String... attributeNames) {
		EntityGraph<Instructor> graph = em.createEntityGraph(Instructor.class);
		graph.addSubgraph("idProof");
		graph.addSubgraph("vehicles");
		Subgraph<Student> studentSubGraph = graph.addSubgraph("students");
		studentSubGraph.addSubgraph("instructors");
		return graph;
	}

}
