package com.noarthedev.factures.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.noarthedev.factures.entity.Fac;
import com.noarthedev.factures.service.FacService;

@RestController
@RequestMapping("/facs")

public class FacController  {
    @Autowired  FacService facService;
    
    @GetMapping
    public ResponseEntity<Object> getAllFac() {
        HashMap<String,Object> data = new HashMap<>();
        try{
            List<Fac>facs =  facService.getAllEntities();
            data.put("facs",facs);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createFac(@RequestBody Fac fac){
        HashMap<String,Object> data = new HashMap<>();
        try{
            facService.saveEntity(fac);
            data.put("message","Fac created successfully");
            return new ResponseEntity<>(data, HttpStatus.CREATED);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFac(@PathVariable("id")Long id){
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