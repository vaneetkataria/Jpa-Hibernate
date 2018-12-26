package com.katariasoft.technologies.jpaHibernate.college.data.dao;

import javax.persistence.EntityManager;
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

}
