package com.hexaware.EventManagement.service;

import java.util.List;

import com.hexaware.EventManagement.EventDTO.EventDTO;


public interface IEventService {

	public EventDTO createEvent(EventDTO eventDTO);
	//get
	public EventDTO getEvent(Long eventId);
	//Delete
	public void deleteEvent(Long playerId );
	//Get all
	public List<EventDTO> getAllEvent();
	//update
	EventDTO updateEvent(Long id, EventDTO EventDTO);
}


