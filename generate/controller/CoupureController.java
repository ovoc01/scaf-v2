package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.CoupureService;

@RestController
@RequestMapping("/coupure")

public class CoupureController {
    @Autowired
    CoupureService coupureService;
    
    @GetMapping
    public ResponseEntity<Object> getAllCoupure() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<Coupure>coupures =  coupureService.getAllEntities();
            data.put("coupures",coupures);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createCoupure(@RequestBody Coupure coupure){
        HasMap<String,Object> data = new HashMap();
        try{
            coupureService.saveEntity(coupure);
            data.put("message","Coupure created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCoupure(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}