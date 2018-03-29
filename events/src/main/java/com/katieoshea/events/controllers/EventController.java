package com.katieoshea.events.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.katieoshea.events.models.Event;
import com.katieoshea.events.models.Message;
import com.katieoshea.events.models.User;
import com.katieoshea.events.services.EventServ;
import com.katieoshea.events.services.MessageServ;
import com.katieoshea.events.services.UserServ;

@Controller
public class EventController {
    private UserServ uServ;
    private EventServ eServ;
    private MessageServ mServ;
    public EventController(UserServ uServ, EventServ eServ, MessageServ mServ) {
        this.uServ = uServ;
        this.eServ = eServ;
        this.mServ = mServ;
    }
	private ArrayList<String> states = new ArrayList<String>(Arrays.asList("AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"));

    @RequestMapping("/events")
    public String home(Principal principal, Model model, @Valid @ModelAttribute("event") Event event) {
        String email = principal.getName();
        User user = uServ.findByEmail(email);
        model.addAttribute("user", user);
        List<Event> events = eServ.allEvents();
        List<Event> instate = new ArrayList<Event>();
        List<Event> outofstate = new ArrayList<Event>();
        model.addAttribute("today", new Date());
        for(Event partay: events) {
        	if(partay.getState().equals(user.getState())) {
        		instate.add(partay);
        	}
        	else {
        		outofstate.add(partay);
        	}
        }
        model.addAttribute("instate", instate);
        model.addAttribute("outofstate", outofstate);
        model.addAttribute("states", states);
        return "events.jsp";
    } 
    @PostMapping("/event/create")
    public String createEvent(@Valid @ModelAttribute("event") Event event, Principal principal, Model model, BindingResult result) {
        if(result.hasErrors()) {
        	
            return "events.jsp";
        }
        else {
        	Date today = new Date();
        	if(event.getEventDate() == null || event.getEventDate().before(today)) {
        		event.setEventDate(today);
        	}
            String email = principal.getName();
            User user = uServ.findByEmail(email);
            event.setHostess(user);
            List<User> goers = new ArrayList<User>();
            goers.add(user);
            event.setUsersAttending(goers);
            eServ.create(event);
            return "redirect:/events";
        }
    }
    
    @RequestMapping("/events/{id}/join")
    public String join(@PathVariable("id") Long id, Principal principal) {
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
    	Event event = eServ.findById(id);
    	List<User> goers = event.getUsersAttending();
        goers.add(user);
        event.setUsersAttending(goers);
        uServ.update(user);
    	return "redirect:/events";
    }
    @RequestMapping("/events/{id}/bail")
    public String bail(@PathVariable("id") Long id, Principal principal) {
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
    	Event event = eServ.findById(id);
    	List<User> goers = event.getUsersAttending();
        for(int i=0; i<goers.size(); i++) {
            if(goers.get(i).getId() == user.getId()) {
            	goers.remove(i);
            }
        }
        event.setUsersAttending(goers);
        uServ.update(user);
    	return "redirect:/events";
    }	
    	
    @RequestMapping("/events/{id}")
    public String show(@PathVariable("id") Long id, Principal principal, Model model, @Valid @ModelAttribute("msg") Message msg, BindingResult result) {
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
    	Event event = eServ.findById(id);
        model.addAttribute("event", event);
        model.addAttribute("user", user);
        model.addAttribute("users", event.getUsersAttending());
        model.addAttribute("messages", event.getMessages());
        return "show.jsp";
    }
    
    @PostMapping("/events/{id}/addmsg")
    public String message(@PathVariable("id") Long id, @Valid @ModelAttribute("msg") Message msg, BindingResult result, Principal principal, Model model) {
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
    	Event event = eServ.findById(id);
        model.addAttribute("event", event);
        model.addAttribute("user", user);
        model.addAttribute("users", event.getUsersAttending());
        List<Message> messages = event.getMessages();
        model.addAttribute("messages", messages);
    	if(result.hasErrors()) {
    		System.out.println(result.getAllErrors());
            return "show.jsp";
        }
        else {
        	mServ.create(msg);
        	msg.setUser(user);
        	msg.setEvent(event);
            mServ.update(msg);
            messages.add(msg);
            event.setMessages(messages);
            user.setMessages(messages);
            return "redirect:/events/{id}";
        }
    }

    @RequestMapping("/events/{id}/edit")
    public String edit(@PathVariable("id") Long id, Principal principal, Model model, @Valid @ModelAttribute("emptyevent") Event emptyevent, BindingResult result) {
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
        model.addAttribute("event", eServ.findById(id));
        model.addAttribute("user", user);
        model.addAttribute("states", states);
        return "edit.jsp";
    }
    @PostMapping("/events/{id}/edit")
    public String update(Model model, @PathVariable("id") Long id, @Valid @ModelAttribute("emptyevent") Event emptyevent, BindingResult result, Principal principal) {
    	model.addAttribute("states", states);
    	Event event = eServ.findById(id);
    	String email = principal.getName();
    	User user = uServ.findByEmail(email);
        model.addAttribute("event", event);
        model.addAttribute("user", user);
    	if(result.hasErrors()) {
    		return "edit.jsp";
        }
        else {
            emptyevent.setHostess(event.getHostess());
            emptyevent.setUsersAttending(event.getUsersAttending());
            emptyevent.setCreatedAt(event.getCreatedAt());
            if(emptyevent.getEventDate() == null) {
            	emptyevent.setEventDate(event.getEventDate());
            }
            eServ.update(emptyevent);
            return "redirect:/events";
        }
    }
    
    @RequestMapping("/events/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
    	Event event = eServ.findById(id);
    	for(User user: event.getUsersAttending()) {
    		List<Event> myevents= user.getEventsJoined();
    		myevents.remove(event);
    		user.setEventsJoined(myevents);
    		uServ.save(user);
    	}
    	for(Message message: mServ.allMessages()) {
    		if(message.getEvent() == event) {
    			mServ.delete(message.getId());
    		}
    	}
    	eServ.delete(id);
    	return "redirect:/events";
    }
}