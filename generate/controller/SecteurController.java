package com.noarthedev.scaffolding.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.HasMap;
import com.noarthedev.scaffolding.repository.SecteurService;

@RestController
@RequestMapping("/secteur")

public class SecteurController {
    @Autowired
    SecteurService secteurService;
    
    @GetMapping
    public ResponseEntity<Object> getAllSecteur() {
        HasMap<String,Object> data = new HashMap();
        try{
            List<Secteur>secteurs =  secteurService.getAllEntities();
            data.put("secteurs",secteurs);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }

    }

    @PostMapping
    public ResponseEntity<Object> createSecteur(@RequestBody Secteur secteur){
        HasMap<String,Object> data = new HashMap();
        try{
            secteurService.saveEntity(secteur);
            data.put("message","Secteur created successfully");
            return ResponseEntity.created().body(data);
        }catch(Exception e){
            data.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(data);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSecteur(@PathVariable("id")String id){
        HasMap<String,Object> data = new HashMap();

        try{
            
        }
    }






}