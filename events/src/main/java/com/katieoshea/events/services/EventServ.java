package com.katieoshea.events.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.katieoshea.events.models.Event;
import com.katieoshea.events.repositories.EventRepo;

@Service
public class EventServ {
    private EventRepo eRepo;
    public EventServ(EventRepo eRepo) {
        this.eRepo = eRepo;
    }

    public void create(Event event) {
        eRepo.save(event);
    }
    public Event findById(Long id) {
        return eRepo.findOne(id);
    }
    public List<Event> allEvents() {
    	return (List<Event>) eRepo.findAll();
    }
    public void update(Event event) {

        eRepo.save(event);
    }
    public void delete(Long id) {
        eRepo.delete(id);
    }
}
