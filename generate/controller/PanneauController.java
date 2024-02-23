package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.PanneauService;

@RestController
@RequestMapping("/panneau")

public class PanneauController {
    @Autowired
    PanneauService panneauService;
    
    @GetMapping
    public ResponseEntity<Object> getAllPanneau() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<Panneau>panneaus =  panneauService.getAllEntities();
            data.put("panneaus",panneaus);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createPanneau(@RequestBody Panneau panneau){
        HasMap<String,Object> data = new HashMap();
        try{
            panneauService.saveEntity(panneau);
            data.put("message","Panneau created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePanneau(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}