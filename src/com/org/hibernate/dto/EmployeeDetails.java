package com.org.hibernate.dto;

/**
 * @author SDudi
 *
 */
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table (name="EMPLOYEES", 
uniqueConstraints={@UniqueConstraint(columnNames={"MID"})})
public class EmployeeDetails {
	
	@Id
	@Column(name="MID")
	private String mID;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="JOIN_DATE")
	@Temporal(TemporalType.DATE)
	private Date joinDate;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="employees", targetEntity = EventDetails.class)
	private Collection<EventDetails> events = new HashSet<EventDetails>();
	
	public EmployeeDetails() {
		// TODO Auto-generated constructor stub
	}


	public EmployeeDetails(String mId, String name, Date joinDate, String emailId) {
		// TODO Auto-generated constructor stub
		this.mID = mId;
		this.name = name;
		this.joinDate = joinDate;
		this.emailId = emailId;
	}

	

	public Collection<EventDetails> getEvents() {
		return events;
	}



	public void setEvents(Collection<EventDetails> events) {
		this.events = events;
	}



	public String getmID() {
		return mID;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
