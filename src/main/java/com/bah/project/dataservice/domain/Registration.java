package com.bah.project.dataservice.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="registrations")
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customer_name;
	private String event_name;
	private Date date;
	private String notes;
	
	public Registration() {
		super();
	}

	public Registration(Long id, String customer_name, String event_name, Date date, String notes) {
		super();
		this.id = id;
		this.customer_name = customer_name;
		this.event_name = event_name;
		this.date = date;
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", customer_name=" + customer_name + ", event_name=" + event_name + ", date="
				+ date + ", notes=" + notes + "]";
	}
	
	
	
}
