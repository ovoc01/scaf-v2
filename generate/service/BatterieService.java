package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.BatterieRepository;

@Service

public class BatterieService  {
   @Autowired
   BatterieRepository batterieRepository;

   
   public List<Batterie> getAllEntities() {
        return batterieRepository.findAll();
    }


    public Optional<Batterie> getEntityById(String id) {
        return batterieRepository.findById(id);
    }


    public Batterie saveEntity(EntityName entityName) {
        return batterieRepository.save(entityName);
    }


    public Batterie updateEntity(Batterie batterie) {
        return batterieRepository.save(batterie);
    }

    public void deleteEntityById(String id) {
        batterieRepository.deleteById(id);
    }



}