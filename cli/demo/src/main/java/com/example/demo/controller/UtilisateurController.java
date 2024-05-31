package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.entity.Utilisateur;
import com.example.demo.service.UtilisateurService;

@Controller
@RequestMapping("/utilisateurs")

public class UtilisateurController  {
    @Autowired  UtilisateurService utilisateurService;
    
    @GetMapping
    public String listUtilisateur(Model model) {
        model.addAttribute("utilisateur", utilisateurService.findAll());
        return "utilisateur/index"; // View name: bookList.jsp
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateur/form"; // View name: bookForm.jsp
    }


    @PostMapping
    public String createUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.save(utilisateur);
        return "redirect:/utilisateurs"; // Redirect to book list
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Utilisateur utilisateur = utilisateurService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid utilisateur Id:" + id));
        model.addAttribute("utilisateurs", utilisateur);
        return "utilisateur/edit"; 
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable String id, @ModelAttribute Utilisateur utilisateur,Model model) {
        utilisateurService.save(utilisateur);
        model.addAttribute("message","New Utilisateur saved");
        return "redirect:/utilisateurs"; // Redirect to book list
    }


    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        utilisateurService.deleteById(id);
        return "redirect:/utilisateurs"; // Redirect to book list
    }




}