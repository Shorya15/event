package com.hexaware.EventManagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.EventManagement.EventDTO.EventDTO;
import com.hexaware.EventManagement.entity.Event;
import com.hexaware.EventManagement.repository.EventRepository;
import com.hexaware.EventManagement.service.IEventService;



@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public EventDTO createEvent(EventDTO eventDTO) {
		Event event=modelMapper.map(eventDTO,Event.class);
		eventRepository.save(event);
		EventDTO EventDTO=modelMapper.map(event,EventDTO.class);//Event->EventDTO  copying data from one object to another
		return EventDTO;
	}

	@Override
	public EventDTO getEvent(Long eventId) {
		Event event=eventRepository.findById(eventId).orElse(null);
		EventDTO eventDTO=null;
		if(event!=null)
		{
			eventDTO=modelMapper.map(event,EventDTO.class);
		}
		return eventDTO;
	}

	@Override
	public EventDTO updateEvent(Long id, EventDTO EventDTO) {
		Event getEvent=eventRepository.findById(id).orElse(null);
		System.out.println(getEvent);
		Event event=modelMapper.map(getEvent,Event.class);
		System.out.println(event);
		EventDTO updatedEventDTO=null;
		if(event!=null)
		{
			event.setDescription(EventDTO.getDescription());
			event.setLocation(EventDTO.getLocation());
			event.setMaxAttendees(EventDTO.getMaxAttendees());
			event.setRegistrationFees(EventDTO.getRegistrationFees());
			event.setTitle(EventDTO.getTitle());
			eventRepository.save(event);
			updatedEventDTO=modelMapper.map(event,EventDTO.class);
			System.out.println(event);
		}
		return updatedEventDTO;
	}

	@Override
	public void deleteEvent(Long EventId) {
		Event Event=eventRepository.findById(EventId).orElse(null);
		if(Event!=null)
		{
			eventRepository.deleteById(EventId);
		}
	}

	@Override
	public List<EventDTO> getAllEvent() {
		List<Event> Event=eventRepository.findAll();
		List<EventDTO> EventDTO=Event.stream().map(p->modelMapper.map(p,EventDTO.class)).collect(Collectors.toList());
		return EventDTO;
	}


		
	

}