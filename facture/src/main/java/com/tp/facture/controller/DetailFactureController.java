package com.tp.facture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.tp.facture.entity.DetailFacture;
import com.tp.facture.service.DetailFactureService;

@RestController
@RequestMapping("/detailFactures")
@CrossOrigin("*")

public class DetailFactureController  {
    @Autowired  DetailFactureService detailFactureService;
    
    @GetMapping
    public ResponseEntity<Object> getAllDetailFacture() {
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<DetailFacture>detailFactures =  detailFactureService.getAllEntities();
            data.put("detailFactures",detailFactures);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdDetailFacture(@PathVariable("id")String id){
        HashMap<String,Object> data = new HashMap<>();

        try{
            DetailFacture detailFacture = detailFactureService.getEntityById(id).get();
            data.put("detailFacture",detailFacture);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PostMapping
    public ResponseEntity<Object> createDetailFacture(@RequestBody DetailFacture detailFacture){
        HashMap<String,Object> data = new HashMap<>();
        try{
            detailFactureService.saveEntity(detailFacture);
            data.put("message","DetailFacture created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetailFacture(@PathVariable("id")String id){
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