package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.demo.entity.Course;
import com.demo.hibernate.demo.entity.Instructor;
import com.demo.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			Course tempCourse1 = new Course("Guitar For Beginners");
			Course tempCourse2 = new Course("Pinball Masterclass");
			
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			session.getTransaction().commit();

			System.out.println("Done!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
