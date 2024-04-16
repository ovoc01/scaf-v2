package com.tp.facture.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tp.facture.entity.Facture;
import com.tp.facture.repository.FactureRepository;

@Service

public class FactureService  {
   @Autowired
   FactureRepository factureRepository;

   
   public List<Facture> getAllEntities() {
        return factureRepository.findAll();
    }


    public Optional<Facture> getEntityById(String id) {
        return factureRepository.findById(id);
    }


    public Facture saveEntity(Facture facture) {
        return factureRepository.save(facture);
    }


    public Facture updateEntity(Facture facture) {
        return factureRepository.save(facture);
    }

    public void deleteEntityById(String id) {
        factureRepository.deleteById(id);
    }



}