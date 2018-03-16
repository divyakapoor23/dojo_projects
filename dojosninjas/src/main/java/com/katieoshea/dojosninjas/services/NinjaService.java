package com.katieoshea.dojosninjas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.katieoshea.dojosninjas.models.Ninja;
import com.katieoshea.dojosninjas.repositories.NinjaRepository;

@Service
public class NinjaService {
    private NinjaRepository ninjaRepo;
    public NinjaService(NinjaRepository ninjaRepo) {
        this.ninjaRepo = ninjaRepo;
    }

    public List<Ninja> all() {
		return (List<Ninja>) ninjaRepo.findAll();
	}

	public Ninja findNinja(Long id) {
		return (Ninja) ninjaRepo.findOne(id);
    }
    
    public void create(Ninja ninja) {
		ninjaRepo.save(ninja);
	}
}

