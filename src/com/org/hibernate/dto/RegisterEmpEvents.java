package com.org.hibernate.dto;

/**
 * @author SDudi
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name="REGISTER_EMP_EVENTS")
public class RegisterEmpEvents {
	
	@Id
	@Column(name="ID")
	@GeneratedValue
	private int id;
		
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name="EVENT_ID")
	private int eventId;
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

}
