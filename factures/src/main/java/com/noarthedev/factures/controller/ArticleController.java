package com.noarthedev.factures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.noarthedev.factures.entity.Article;
import com.noarthedev.factures.service.ArticleService;

@RestController
@RequestMapping("/articles")
@CrossOrigin("*")

public class ArticleController  {
    @Autowired  ArticleService articleService;
    
    @GetMapping
    public ResponseEntity<Object> getAllArticle() {
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<Article>articles =  articleService.getAllEntities();
            data.put("articles",articles);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdArticle(@PathVariable("id")String id){
        HashMap<String,Object> data = new HashMap<>();

        try{
            Article article = articleService.getEntityById(id).get();
            data.put("article",article);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PostMapping
    public ResponseEntity<Object> createArticle(@RequestBody Article article){
        HashMap<String,Object> data = new HashMap<>();
        try{
            articleService.saveEntity(article);
            data.put("message","Article created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable("id")String id){
        HashMap<String,Object> data = new HashMap<>();

        try{
            //TODO
            return ResponseEntity.ok("success");
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }




}