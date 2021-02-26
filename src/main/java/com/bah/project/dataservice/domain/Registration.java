package com.bah.project.dataservice.domain;

import java.util.Date;

import javax.persistence.Column;
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
	private Integer id;
	
	private Integer customer_id;
	
	private Integer event_id;
	
	@Column(name="registration_date")
	private Date date;
	
	private String notes;
	
	public Registration() {
		super();
	}

	public Registration(Integer id, Integer customer_id, Integer event_id, Date date, String notes) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.event_id = event_id;
		this.date = date;
		this.notes = notes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
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
		return "Registration [id=" + id + ", customer_id=" + customer_id + ", event_id=" + event_id + ", date="
				+ date + ", notes=" + notes + "]";
	}
	
	
	
}
