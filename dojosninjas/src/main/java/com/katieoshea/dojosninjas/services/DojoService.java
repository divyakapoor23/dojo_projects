package com.katieoshea.dojosninjas.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.katieoshea.dojosninjas.models.Dojo;
import com.katieoshea.dojosninjas.repositories.DojoRepository;

@Service
public class DojoService {
    private DojoRepository dojoRepo;
    public DojoService(DojoRepository dojoRepo) {
        this.dojoRepo = dojoRepo;
    }

    public List<Dojo> all() {
        return (List<Dojo>) dojoRepo.findAll();
    }

    public Dojo findDojo(Long id) {
        return (Dojo) dojoRepo.findOne(id);
    }

    public void create(Dojo dojo) {
        dojoRepo.save(dojo);
    }
}