package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.SourceEnergieService;

@RestController
@RequestMapping("/sourceEnergie")

public class SourceEnergieController {
    @Autowired
    SourceEnergieService sourceEnergieService;
    
    @GetMapping
    public ResponseEntity<Object> getAllSourceEnergie() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<SourceEnergie>sourceEnergies =  sourceEnergieService.getAllEntities();
            data.put("sourceEnergies",sourceEnergies);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createSourceEnergie(@RequestBody SourceEnergie sourceEnergie){
        HasMap<String,Object> data = new HashMap();
        try{
            sourceEnergieService.saveEntity(sourceEnergie);
            data.put("message","SourceEnergie created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSourceEnergie(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}