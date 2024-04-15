package com.noarthedev.factures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.noarthedev.factures.entity.TypeArticle;
import com.noarthedev.factures.service.TypeArticleService;

@RestController
@RequestMapping("/typeArticles")

public class TypeArticleController  {
    @Autowired  TypeArticleService typeArticleService;
    
    @GetMapping
    public ResponseEntity<Object> getAllTypeArticle() {
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<TypeArticle>typeArticles =  typeArticleService.getAllEntities();
            data.put("typeArticles",typeArticles);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createTypeArticle(@RequestBody TypeArticle typeArticle){
        HashMap<String,Object> data = new HashMap<>();
        try{
            typeArticleService.saveEntity(typeArticle);
            data.put("message","TypeArticle created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTypeArticle(@PathVariable("id")String id){
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