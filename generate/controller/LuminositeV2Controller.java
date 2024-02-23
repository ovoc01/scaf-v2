package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.LuminositeV2Service;

@RestController
@RequestMapping("/luminositeV2")

public class LuminositeV2Controller {
    @Autowired
    LuminositeV2Service luminositeV2Service;
    
    @GetMapping
    public ResponseEntity<Object> getAllLuminositeV2() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<LuminositeV2>luminositeV2s =  luminositeV2Service.getAllEntities();
            data.put("luminositeV2s",luminositeV2s);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createLuminositeV2(@RequestBody LuminositeV2 luminositeV2){
        HasMap<String,Object> data = new HashMap();
        try{
            luminositeV2Service.saveEntity(luminositeV2);
            data.put("message","LuminositeV2 created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLuminositeV2(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}