package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.demo.entity.Course;
import com.demo.hibernate.demo.entity.Instructor;
import com.demo.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			Instructor tempInstructor = new Instructor("Sussanne", "Pari", "supa@email.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("sussanne", "Gaming!");

			tempInstructor.setInstructorDetail(tempInstructorDetail);

			session.beginTransaction();

//			one save will store both object because of CascadeType.ALL
			session.save(tempInstructor);

			session.getTransaction().commit();

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
