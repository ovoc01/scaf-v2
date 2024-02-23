package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.LuminositeV2Repository;

@Service

public class LuminositeV2Service  {
   @Autowired
   LuminositeV2Repository luminositeV2Repository;

   
   public List<LuminositeV2> getAllEntities() {
        return luminositeV2Repository.findAll();
    }


    public Optional<LuminositeV2> getEntityById(String id) {
        return luminositeV2Repository.findById(id);
    }


    public LuminositeV2 saveEntity(EntityName entityName) {
        return luminositeV2Repository.save(entityName);
    }


    public LuminositeV2 updateEntity(LuminositeV2 luminositeV2) {
        return luminositeV2Repository.save(luminositeV2);
    }

    public void deleteEntityById(String id) {
        luminositeV2Repository.deleteById(id);
    }



}