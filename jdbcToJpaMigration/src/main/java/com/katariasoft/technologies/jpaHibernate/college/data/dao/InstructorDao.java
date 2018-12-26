package com.katariasoft.technologies.jpaHibernate.college.data.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;

@Repository
public class InstructorDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Instructor getInstructor(int id) {
		try {
			Instructor instructor = em.find(Instructor.class, id);
			instructor.setAddress("Updated in Requires new Transaction");
			return instructor;
		} catch (Exception e) {
			throw e;
		}
	}

	public Optional<List<Instructor>> fetchAllInstructors() {
		try {
			return Optional.ofNullable(em.createNamedQuery("fetchAllInstructors", Instructor.class).getResultList());
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	public boolean deleteAllInstructors(List<Integer> ids) {
		try {
			em.createQuery(Instructor.DELETE_INSTRICTORS_HAVING_IDS).setParameter("ids", ids).executeUpdate();
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

}
