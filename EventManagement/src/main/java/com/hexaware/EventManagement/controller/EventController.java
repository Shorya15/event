package com.hexaware.EventManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.EventManagement.EventDTO.EventDTO;
import com.hexaware.EventManagement.exception.ResourceNotFoundException;
import com.hexaware.EventManagement.service.IEventService;
@CrossOrigin("*")
@RestController// to make controller
@RequestMapping("/api/events")
public class EventController {

	@Autowired
	private IEventService eventService;

	@GetMapping
	public ResponseEntity<List<EventDTO>> getAll() throws ResourceNotFoundException {
		List<EventDTO> eventDtos = eventService.getAllEvent();
		if (!eventDtos.isEmpty()) {
			return ResponseEntity.ok(eventDtos);
		}

		{
			throw new ResourceNotFoundException("users  not found");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
		EventDTO updatedEventDTO = eventService.getEvent(id);
		if (updatedEventDTO != null) {
			return ResponseEntity.ok(updatedEventDTO);
		} else {
			throw new ResourceNotFoundException("user not found" + id);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		eventService.deleteEvent(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO eventDto)
			throws ResourceNotFoundException {
		EventDTO updatedEventDTO = eventService.updateEvent(id, eventDto);
		if (updatedEventDTO != null) {
			return ResponseEntity.ok(updatedEventDTO);
		} else {
			throw new ResourceNotFoundException("user not found " + id);
		}

	}

	@PostMapping
	public ResponseEntity<EventDTO> post(@RequestBody EventDTO eventDto) {
		EventDTO updatedEventDTO = eventService.createEvent(eventDto);
		return new ResponseEntity<>(updatedEventDTO, HttpStatus.CREATED);

	}
}