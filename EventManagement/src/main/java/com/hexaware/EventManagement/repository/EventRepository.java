package com.hexaware.EventManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.EventManagement.entity.Event;
@Repository	
public interface EventRepository extends JpaRepository<Event ,Long> {

}
