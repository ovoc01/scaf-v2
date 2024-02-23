package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.PointageService;

@RestController
@RequestMapping("/pointage")

public class PointageController {
    @Autowired
    PointageService pointageService;
    
    @GetMapping
    public ResponseEntity<Object> getAllPointage() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<Pointage>pointages =  pointageService.getAllEntities();
            data.put("pointages",pointages);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createPointage(@RequestBody Pointage pointage){
        HasMap<String,Object> data = new HashMap();
        try{
            pointageService.saveEntity(pointage);
            data.put("message","Pointage created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePointage(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}