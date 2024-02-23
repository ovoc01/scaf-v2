package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.SecteurRepository;

@Service

public class SecteurService  {
   @Autowired
   SecteurRepository secteurRepository;

   
   public List<Secteur> getAllEntities() {
        return secteurRepository.findAll();
    }


    public Optional<Secteur> getEntityById(String id) {
        return secteurRepository.findById(id);
    }


    public Secteur saveEntity(EntityName entityName) {
        return secteurRepository.save(entityName);
    }


    public Secteur updateEntity(Secteur secteur) {
        return secteurRepository.save(secteur);
    }

    public void deleteEntityById(String id) {
        secteurRepository.deleteById(id);
    }



}