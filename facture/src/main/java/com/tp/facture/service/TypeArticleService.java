package com.tp.facture.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tp.facture.entity.TypeArticle;
import com.tp.facture.repository.TypeArticleRepository;

@Service

public class TypeArticleService  {
   @Autowired
   TypeArticleRepository typeArticleRepository;

   
   public List<TypeArticle> getAllEntities() {
        return typeArticleRepository.findAll();
    }


    public Optional<TypeArticle> getEntityById(String id) {
        return typeArticleRepository.findById(id);
    }


    public TypeArticle saveEntity(TypeArticle typeArticle) {
        return typeArticleRepository.save(typeArticle);
    }


    public TypeArticle updateEntity(TypeArticle typeArticle) {
        return typeArticleRepository.save(typeArticle);
    }

    public void deleteEntityById(String id) {
        typeArticleRepository.deleteById(id);
    }



}