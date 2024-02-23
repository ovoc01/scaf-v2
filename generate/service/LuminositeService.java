package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.LuminositeRepository;

@Service

public class LuminositeService  {
   @Autowired
   LuminositeRepository luminositeRepository;

   
   public List<Luminosite> getAllEntities() {
        return luminositeRepository.findAll();
    }


    public Optional<Luminosite> getEntityById(String id) {
        return luminositeRepository.findById(id);
    }


    public Luminosite saveEntity(EntityName entityName) {
        return luminositeRepository.save(entityName);
    }


    public Luminosite updateEntity(Luminosite luminosite) {
        return luminositeRepository.save(luminosite);
    }

    public void deleteEntityById(String id) {
        luminositeRepository.deleteById(id);
    }



}