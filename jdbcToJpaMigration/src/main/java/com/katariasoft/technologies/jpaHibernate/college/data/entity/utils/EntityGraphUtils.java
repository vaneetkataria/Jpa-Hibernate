package com.katariasoft.technologies.jpaHibernate.college.data.entity.utils;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;

import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Student_;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Vehicle_;

@Repository
public class EntityGraphUtils {

	public static final String FETCH_GRAPH = "javax.persistence.fetchgraph";

	@PersistenceContext
	private EntityManager em;

	public <T> EntityGraph<T> createGraph(Class<T> clazz, String... attributeNames) {
		EntityGraph<T> graph = em.createEntityGraph(clazz);
		if (Objects.nonNull(attributeNames))
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

	@SuppressWarnings("unchecked")
	public EntityGraph<Instructor> fullEntityGraph() {
		EntityGraph<Instructor> entityGraph = em.createEntityGraph(Instructor.class);
		entityGraph.addAttributeNodes(Instructor_.idProof, Instructor_.vehicles);
		Subgraph<Student> studentSubgraph = entityGraph.addSubgraph(Instructor_.STUDENTS);
		studentSubgraph.addAttributeNodes(Student_.instructors);
		Subgraph<Vehicle> vehicleSubhraph = studentSubgraph.addSubgraph(Student_.VEHICLES);
		vehicleSubhraph.addAttributeNodes(Vehicle_.documents);
		return entityGraph;
	}

	@SuppressWarnings("unchecked")
	public EntityGraph<Instructor> partialEntityGraph() {
		EntityGraph<Instructor> entityGraph = em.createEntityGraph(Instructor.class);
		entityGraph.addAttributeNodes(Instructor_.idProof);
		Subgraph<Vehicle> insVehcile = entityGraph.addSubgraph(Instructor_.VEHICLES);
		insVehcile.addAttributeNodes(Vehicle_.documents);
		Subgraph<Student> studentSubgraph = entityGraph.addSubgraph(Instructor_.STUDENTS);
		Subgraph<Vehicle> vehicleSubhraph = studentSubgraph.addSubgraph(Student_.VEHICLES);
		return entityGraph;
	}

}
