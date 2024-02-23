package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.LuminositeService;

@RestController
@RequestMapping("/luminosite")

public class LuminositeController {
    @Autowired
    LuminositeService luminositeService;
    
    @GetMapping
    public ResponseEntity<Object> getAllLuminosite() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<Luminosite>luminosites =  luminositeService.getAllEntities();
            data.put("luminosites",luminosites);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createLuminosite(@RequestBody Luminosite luminosite){
        HasMap<String,Object> data = new HashMap();
        try{
            luminositeService.saveEntity(luminosite);
            data.put("message","Luminosite created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLuminosite(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}