package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.SalleRepository;

@Service

public class SalleService  {
   @Autowired
   SalleRepository salleRepository;

   
   public List<Salle> getAllEntities() {
        return salleRepository.findAll();
    }


    public Optional<Salle> getEntityById(String id) {
        return salleRepository.findById(id);
    }


    public Salle saveEntity(EntityName entityName) {
        return salleRepository.save(entityName);
    }


    public Salle updateEntity(Salle salle) {
        return salleRepository.save(salle);
    }

    public void deleteEntityById(String id) {
        salleRepository.deleteById(id);
    }



}