package com.katariasoft.technologies.jpaHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.katariasoft.technologies.jpaHibernate.college.data.dao.InstructorRepository;
import com.katariasoft.technologies.jpaHibernate.college.data.entity.Instructor;

@SpringBootApplication
public class JpaHibernateApplication implements CommandLineRunner {

	@Autowired
	private InstructorRepository instructorDao;

	public static void main(String[] args) {
		SpringApplication.run(JpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		instructorDao.findById(1);

	}
}
