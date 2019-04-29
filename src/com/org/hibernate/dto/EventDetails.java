package com.org.hibernate.dto;

/**
 * @author SDudi
 *
 */

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity 
@Table (name="EVENTS")
public class EventDetails {
	
	@Id
	@Column(name="EVENT_ID")
	@GeneratedValue
	private int eventId;
	
	@Column(name="EVENT_TITLE")
	private String eventTitle;
	
	@Column(name="DESCRIPTION")
	private String description;

	@ManyToMany
	@JoinTable(name="EVENT_EMPLOYEE_REGISTRATION", joinColumns=@JoinColumn(name="EVENT_ID", referencedColumnName="EVENT_ID"),
	inverseJoinColumns=@JoinColumn(name="EMPLOYEE_ID", referencedColumnName="MID"))
	private Set<EmployeeDetails> employees = new HashSet<EmployeeDetails>(); 
	
	
	/**
	 * Default contructor.
	 */
	public EventDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public EventDetails(int id, String eventTitle, String description) {
		// TODO Auto-generated constructor stub
		this.eventId = id;
		this.eventTitle = eventTitle;
		this.description = description;
	}


	public Set<EmployeeDetails> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeDetails> employees) {
		this.employees = employees;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
