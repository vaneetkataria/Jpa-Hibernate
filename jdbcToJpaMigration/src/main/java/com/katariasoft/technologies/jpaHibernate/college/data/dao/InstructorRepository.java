package com.katariasoft.technologies.jpaHibernate.college.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;

@Repository
public class InstructorRepository {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	public Instructor findById(int id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Instructor in = em.find(Instructor.class, id);
		in.setAddress("K block");
		em.getTransaction().commit();
		return in;
	}

}
