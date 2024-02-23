package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.CoupureRepository;

@Service

public class CoupureService  {
   @Autowired
   CoupureRepository coupureRepository;

   
   public List<Coupure> getAllEntities() {
        return coupureRepository.findAll();
    }


    public Optional<Coupure> getEntityById(String id) {
        return coupureRepository.findById(id);
    }


    public Coupure saveEntity(EntityName entityName) {
        return coupureRepository.save(entityName);
    }


    public Coupure updateEntity(Coupure coupure) {
        return coupureRepository.save(coupure);
    }

    public void deleteEntityById(String id) {
        coupureRepository.deleteById(id);
    }



}