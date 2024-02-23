package com.noarthedev.scaffolding.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.scaffolding.repository.PointageV2Repository;

@Service

public class PointageV2Service  {
   @Autowired
   PointageV2Repository pointageV2Repository;

   
   public List<PointageV2> getAllEntities() {
        return pointageV2Repository.findAll();
    }


    public Optional<PointageV2> getEntityById(String id) {
        return pointageV2Repository.findById(id);
    }


    public PointageV2 saveEntity(EntityName entityName) {
        return pointageV2Repository.save(entityName);
    }


    public PointageV2 updateEntity(PointageV2 pointageV2) {
        return pointageV2Repository.save(pointageV2);
    }

    public void deleteEntityById(String id) {
        pointageV2Repository.deleteById(id);
    }



}