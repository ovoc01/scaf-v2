package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.PointageRepository;

@Service

public class PointageService  {
   @Autowired
   PointageRepository pointageRepository;

   
   public List<Pointage> getAllEntities() {
        return pointageRepository.findAll();
    }


    public Optional<Pointage> getEntityById(String id) {
        return pointageRepository.findById(id);
    }


    public Pointage saveEntity(EntityName entityName) {
        return pointageRepository.save(entityName);
    }


    public Pointage updateEntity(Pointage pointage) {
        return pointageRepository.save(pointage);
    }

    public void deleteEntityById(String id) {
        pointageRepository.deleteById(id);
    }



}