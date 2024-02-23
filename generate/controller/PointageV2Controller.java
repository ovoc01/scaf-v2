package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.PointageV2Service;

@RestController
@RequestMapping("/pointageV2")

public class PointageV2Controller {
    @Autowired
    PointageV2Service pointageV2Service;
    
    @GetMapping
    public ResponseEntity<Object> getAllPointageV2() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<PointageV2>pointageV2s =  pointageV2Service.getAllEntities();
            data.put("pointageV2s",pointageV2s);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createPointageV2(@RequestBody PointageV2 pointageV2){
        HasMap<String,Object> data = new HashMap();
        try{
            pointageV2Service.saveEntity(pointageV2);
            data.put("message","PointageV2 created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePointageV2(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}