package com.noarthedev.factures.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.noarthedev.factures.entity.UniteArticle;
import com.noarthedev.factures.repository.UniteArticleRepository;

@Service

public class UniteArticleService  {
   @Autowired
   UniteArticleRepository uniteArticleRepository;

   
   public List<UniteArticle> getAllEntities() {
        return uniteArticleRepository.findAll();
    }


    public Optional<UniteArticle> getEntityById(String id) {
        return uniteArticleRepository.findById(id);
    }


    public UniteArticle saveEntity(UniteArticle uniteArticle) {
        return uniteArticleRepository.save(uniteArticle);
    }


    public UniteArticle updateEntity(UniteArticle uniteArticle) {
        return uniteArticleRepository.save(uniteArticle);
    }

    public void deleteEntityById(String id) {
        uniteArticleRepository.deleteById(id);
    }



}