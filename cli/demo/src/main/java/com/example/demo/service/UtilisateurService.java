package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;

@Service

public class UtilisateurService  {
   @Autowired
   UtilisateurRepository utilisateurRepository;

   
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }


    public Optional<Utilisateur> findById(String id) {
        return utilisateurRepository.findById(id);
    }


    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }


    public Utilisateur update(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(String id) {
        utilisateurRepository.deleteById(id);
    }



}