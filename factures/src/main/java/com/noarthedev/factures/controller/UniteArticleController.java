package com.noarthedev.factures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.noarthedev.factures.entity.UniteArticle;
import com.noarthedev.factures.service.UniteArticleService;

@RestController
@RequestMapping("/uniteArticles")
@CrossOrigin("*")

public class UniteArticleController  {
    @Autowired  UniteArticleService uniteArticleService;
    
    @GetMapping
    public ResponseEntity<Object> getAllUniteArticle() {
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<UniteArticle>uniteArticles =  uniteArticleService.getAllEntities();
            data.put("uniteArticles",uniteArticles);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdUniteArticle(@PathVariable("id")String id){
        HashMap<String,Object> data = new HashMap<>();

        try{
            UniteArticle uniteArticle = uniteArticleService.getEntityById(id).get();
            data.put("uniteArticle",uniteArticle);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PostMapping
    public ResponseEntity<Object> createUniteArticle(@RequestBody UniteArticle uniteArticle){
        HashMap<String,Object> data = new HashMap<>();
        try{
            uniteArticleService.saveEntity(uniteArticle);
            data.put("message","UniteArticle created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUniteArticle(@PathVariable("id")String id){
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