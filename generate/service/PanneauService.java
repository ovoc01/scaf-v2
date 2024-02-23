package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.PanneauRepository;

@Service

public class PanneauService  {
   @Autowired
   PanneauRepository panneauRepository;

   
   public List<Panneau> getAllEntities() {
        return panneauRepository.findAll();
    }


    public Optional<Panneau> getEntityById(String id) {
        return panneauRepository.findById(id);
    }


    public Panneau saveEntity(EntityName entityName) {
        return panneauRepository.save(entityName);
    }


    public Panneau updateEntity(Panneau panneau) {
        return panneauRepository.save(panneau);
    }

    public void deleteEntityById(String id) {
        panneauRepository.deleteById(id);
    }



}