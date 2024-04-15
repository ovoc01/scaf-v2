package com.noarthedev.factures.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.factures.entity.DetailFacture;
import com.noarthedev.factures.repository.DetailFactureRepository;

@Service

public class DetailFactureService  {
   @Autowired
   DetailFactureRepository detailFactureRepository;

   
   public List<DetailFacture> getAllEntities() {
        return detailFactureRepository.findAll();
    }


    public Optional<DetailFacture> getEntityById(String id) {
        return detailFactureRepository.findById(id);
    }


    public DetailFacture saveEntity(DetailFacture detailFacture) {
        return detailFactureRepository.save(detailFacture);
    }


    public DetailFacture updateEntity(DetailFacture detailFacture) {
        return detailFactureRepository.save(detailFacture);
    }

    public void deleteEntityById(String id) {
        detailFactureRepository.deleteById(id);
    }



}