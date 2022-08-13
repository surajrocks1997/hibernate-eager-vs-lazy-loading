package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.demo.entity.Instructor;
import com.demo.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Instructor tempInstructor = new Instructor("MD", "Patil", "mp@email.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("mdChannel", "Coding!");
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			session.beginTransaction();
			
//			one save will store both object because of CascadeType.ALL
			session.save(tempInstructor);

			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
		}
	}

}
