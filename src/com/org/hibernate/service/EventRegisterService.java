package com.org.hibernate.service;

/**
 * @author SDudi
 *
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.org.hibernate.dao.EventRegisterDao;
import com.org.hibernate.dto.EmployeeDetails;
import com.org.hibernate.dto.EventDetails;

public class EventRegisterService {

	private static final Logger logger = LogManager.getLogger(EventRegisterService.class);
	List<EventDetails> events = new ArrayList<EventDetails>();
	List<EmployeeDetails> employees = new ArrayList<EmployeeDetails>();

	EventRegisterDao eventRegisterDao = new EventRegisterDao();	
	
	public void displayAllEvents()
	{
		
		events = eventRegisterDao.retrieveAllEvents();
		System.out.println("-----------------------------------------------------------------------------");
	    System.out.printf("%10s %20s %50s ", "Event Number", "Event Title", "Event Description");
	    System.out.println();
	    for(EventDetails event : events){
	        System.out.format("%10s %30s %60s", 
	                event.getEventId(), event.getEventTitle(), event.getDescription());
	        System.out.println();
	    }
	    System.out.println("-----------------------------------------------------------------------------");
	    //logger.info("Display all events");
	    
	}
	
	public int getTotalNumberOfEvents ()
	{
		return events.size();
	}
	
	
	public boolean registerEvent(String mid, Integer eventNumber) {
		boolean success = eventRegisterDao.registerEmployeeToEvents(mid, eventNumber);
		return success;
	}
	
	public boolean displayAllEmployees()
	{
		employees = eventRegisterDao.retrieveAllEmployees();
		
		if(employees == null)
		{
			System.out.println("Invalid Employee");
			return false;
		}
		System.out.println("-----------------------------------------------------------------------------");
	    System.out.printf("%20s %20s %20s %40s", "MID", "Name", "Joining Date", "Email");
	    System.out.println();
	    for(EmployeeDetails employee : employees){
	        System.out.format("%20s %20s %20s %40s", 
	                employee.getmID(), employee.getName(), employee.getJoinDate(), employee.getEmailId());
	        System.out.println();
	    }
	    System.out.println("-----------------------------------------------------------------------------");
		return true;
	}
	
}
