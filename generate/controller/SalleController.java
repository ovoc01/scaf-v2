package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.SalleService;

@RestController
@RequestMapping("/salle")

public class SalleController {
    @Autowired
    SalleService salleService;
    
    @GetMapping
    public ResponseEntity<Object> getAllSalle() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<Salle>salles =  salleService.getAllEntities();
            data.put("salles",salles);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createSalle(@RequestBody Salle salle){
        HasMap<String,Object> data = new HashMap();
        try{
            salleService.saveEntity(salle);
            data.put("message","Salle created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSalle(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}