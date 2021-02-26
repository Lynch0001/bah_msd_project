package com.bah.project.dataservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="events")
public class Event {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)	
private Integer id;
@Column(name="event_code")
private String code;

private String description;

private String title;






public Event(Integer id, String code, String description, String title) {
	super();
	this.id = id;
	this.code = code;
	this.description = description;
	this.title = title;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

@Override
public String toString() {
	return "Event [id=" + id + ", code=" + code + ", description=" + description + ", title=" + title + "]";
}

public Event() {
	super();
}




}
