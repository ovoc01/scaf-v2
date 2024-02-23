package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.SourceEnergieRepository;

@Service

public class SourceEnergieService  {
   @Autowired
   SourceEnergieRepository sourceEnergieRepository;

   
   public List<SourceEnergie> getAllEntities() {
        return sourceEnergieRepository.findAll();
    }


    public Optional<SourceEnergie> getEntityById(String id) {
        return sourceEnergieRepository.findById(id);
    }


    public SourceEnergie saveEntity(EntityName entityName) {
        return sourceEnergieRepository.save(entityName);
    }


    public SourceEnergie updateEntity(SourceEnergie sourceEnergie) {
        return sourceEnergieRepository.save(sourceEnergie);
    }

    public void deleteEntityById(String id) {
        sourceEnergieRepository.deleteById(id);
    }



}