package com.noarthedev.factures.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.factures.entity.Fac;
import com.noarthedev.factures.repository.FacRepository;

@Service

public class FacService  {
   @Autowired
   FacRepository facRepository;

   
   public List<Fac> getAllEntities() {
        return facRepository.findAll();
    }


    public Optional<Fac> getEntityById(Long id) {
        return facRepository.findById(id);
    }


    public Fac saveEntity(Fac fac) {
        return facRepository.save(fac);
    }


    public Fac updateEntity(Fac fac) {
        return facRepository.save(fac);
    }

    public void deleteEntityById(Long id) {
        facRepository.deleteById(id);
    }



}