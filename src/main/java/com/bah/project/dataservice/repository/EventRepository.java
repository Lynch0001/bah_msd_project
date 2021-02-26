package com.bah.project.dataservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bah.project.dataservice.domain.Event;


@Repository
public interface EventRepository extends CrudRepository<Event, Integer>{
	

}
