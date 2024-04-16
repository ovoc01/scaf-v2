package com.tp.facture.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.tp.facture.entity.Article;
import com.tp.facture.repository.ArticleRepository;

@Service

public class ArticleService  {
   @Autowired
   ArticleRepository articleRepository;

   
   public List<Article> getAllEntities() {
        return articleRepository.findAll();
    }


    public Optional<Article> getEntityById(String id) {
        return articleRepository.findById(id);
    }


    public Article saveEntity(Article article) {
        return articleRepository.save(article);
    }


    public Article updateEntity(Article article) {
        return articleRepository.save(article);
    }

    public void deleteEntityById(String id) {
        articleRepository.deleteById(id);
    }



}