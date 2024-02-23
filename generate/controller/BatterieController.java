package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.BatterieService;

@RestController
@RequestMapping("/batterie")

public class BatterieController {
    @Autowired
    BatterieService batterieService;
    
    @GetMapping
    public ResponseEntity<Object> getAllBatterie() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<Batterie>batteries =  batterieService.getAllEntities();
            data.put("batteries",batteries);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createBatterie(@RequestBody Batterie batterie){
        HasMap<String,Object> data = new HashMap();
        try{
            batterieService.saveEntity(batterie);
            data.put("message","Batterie created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBatterie(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}