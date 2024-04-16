package com.tp.facture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.tp.facture.entity.Facture;
import com.tp.facture.service.FactureService;

@RestController
@RequestMapping("/factures")
@CrossOrigin("*")

public class FactureController  {
    @Autowired  FactureService factureService;
    
    @GetMapping
    public ResponseEntity<Object> getAllFacture() {
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<Facture>factures =  factureService.getAllEntities();
            data.put("factures",factures);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdFacture(@PathVariable("id")String id){
        HashMap<String,Object> data = new HashMap<>();

        try{
            Facture facture = factureService.getEntityById(id).get();
            data.put("facture",facture);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PostMapping
    public ResponseEntity<Object> createFacture(@RequestBody Facture facture){
        HashMap<String,Object> data = new HashMap<>();
        try{
            factureService.saveEntity(facture);
            data.put("message","Facture created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFacture(@PathVariable("id")String id){
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