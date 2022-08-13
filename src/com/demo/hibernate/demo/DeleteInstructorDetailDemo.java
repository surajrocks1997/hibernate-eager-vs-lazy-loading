package com.demo.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.hibernate.demo.entity.Instructor;
import com.demo.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			
			int theId = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

//			delete instructor detail
			System.out.println("deleting: " + tempInstructorDetail);
			
//			remove the associated object reference
//			break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
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
