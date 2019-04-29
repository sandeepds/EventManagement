package com.org.hibernate.dao;

/**
 * @author SDudi
 *
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.org.hibernate.dto.EmployeeDetails;
import com.org.hibernate.dto.EventDetails;

//DAO object to access Data alone
public class EventRegisterDao {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static final Logger logger = LogManager.getLogger(EventRegisterDao.class);
	
	/*static {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// These are existing data given in the project.
		logger.info("Start : Data for Employee");
		session.save(new EmployeeDetails("M100100", "Karthik Kumar", new Date(2013-02-05), "karthik_kumar"));
		session.save(new EmployeeDetails("M100101", "Ramesh Kulkarni", new Date(2013-02-05), "ramesh_kulkarni"));
		session.save(new EmployeeDetails("M100102", "Rohit Agarwal M", new Date(2013-03-22), "rohit_agarwal_m"));
		session.save(new EmployeeDetails("M100103", "Magesh Narayanan", new Date(2013-03-22), "mahesh_narayanan"));
		logger.info("End : Data for Employee");
		
		logger.info("Start : Data for Events");
		session.save(new EventDetails(1, "Trekking", "Held every month. More details from Manish Kumar"));
		session.save(new EventDetails(2, "Guitar Classes", "Weekly 3 sessions. Classes conducted by Daniel M"));
		session.save(new EventDetails(3, "Yoga Classes", "Saturdays and Sundays. Classes conducted by Yamini"));
		session.save(new EventDetails(4, "Health & Diet tips", "Every Friday 5 PM to 6 PM by Dr. Kishore Dutta"));
		logger.info("End : Data for Events");
		
		session.getTransaction().commit();
	}*/
	
	//Method registers employee to events
	public boolean registerEmployeeToEvents(String mid, Integer eventId) {
		logger.info("Start : registerEmployeeToEvents");
		boolean success = false;
		EmployeeDetails employee = null;
		EventDetails event = null;
		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();

			employee = (EmployeeDetails) session.get(EmployeeDetails.class, mid);
			event = (EventDetails) session.get(EventDetails.class, eventId);
			

			for (EmployeeDetails emp : event.getEmployees()) {
				if (mid != null && mid.equals(emp.getmID())) {
					success = false;
					System.out.println("Employee : " + mid + "is already registered to the event :" + eventId);
					logger.info("Employee : " + mid + "is already registered to the event :" + eventId);
					return success;
				}
			}
			
			employee.getEvents().add(event);
			event.getEmployees().add(employee);
			session.save(event);
			session.save(employee);
			session.getTransaction().commit();
			success = true;
		} catch (Exception e) {
			success = false;
			if (employee == null)
				System.out.println("Invalid Employee");
			else if (event == null)
				System.out.println("Invalid event");
			else
				System.out.println(
						"An exception occured while registering the employee for the event. Please try again.");
			return success;

		}
		logger.info("End : registerEmployeeToEvents");
		return success;

	}
		
		
	//This method retrieves all the events existing in Event Registration system.
	@SuppressWarnings("unchecked")
	public List<EventDetails> retrieveAllEvents() {
		List<EventDetails> events = new ArrayList<EventDetails>();
		logger.info("Start : retrieveAllEvents");
		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			// Use of HQL
			Query q = (Query) session.createQuery("from EventDetails");
			events = (List<EventDetails>) ((org.hibernate.Query) q).list();

		} catch (Exception e) {
			System.out.println("Exception occured while retrieving all the events");
		}
		logger.info("End : retrieveAllEvents");
		return events;

	}

	//This method retrieves all the employees existing in Event Registration system.
	public List<EmployeeDetails> retrieveAllEmployees() {
		logger.info("Start : retrieveAllEmployees");
		List<EmployeeDetails> employees = new ArrayList<EmployeeDetails>();

		try {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			// Use of HQL
			Query q = (Query) session.createQuery("from EmployeeDetails");
			employees = ((org.hibernate.Query) q).list();

		} catch (Exception e) {
			System.out.println("Exception occured while retrieving all the Employees of System");
			return null;
		}
		logger.info("End : retrieveAllEmployees");
		return employees;
	}
	
	private static SessionFactory buildSessionFactory() {
		try {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			return sessionFactory;
		} catch (Throwable ex) {
			System.out.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
