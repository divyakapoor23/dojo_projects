package com.katieoshea.events.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.katieoshea.events.models.Message;
import com.katieoshea.events.repositories.MessageRepo;

@Service
public class MessageServ {
    private MessageRepo mRepo;
    public MessageServ(MessageRepo mRepo) {
        this.mRepo = mRepo;
    }

    public void create(Message message) {
        mRepo.save(message);
    }
    public Message findById(Long id) {
        return mRepo.findOne(id);
    }
    public List<Message> allMessages() {
    	return (List<Message>) mRepo.findAll();
    }
    public void delete(Long id) {
        mRepo.delete(id);
    }
    public void update(Message message) {
    	mRepo.save(message);
    }

}