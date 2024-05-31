package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

@Controller
@RequestMapping("/roles")

public class RoleController  {
    @Autowired  RoleService roleService;
    
    @GetMapping
    public String listRole(Model model) {
        model.addAttribute("role", roleService.findAll());
        return "role/index"; // View name: bookList.jsp
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("role", new Role());
        return "role/form"; // View name: bookForm.jsp
    }


    @PostMapping
    public String createRole(@ModelAttribute Role role) {
        roleService.save(role);
        return "redirect:/roles"; // Redirect to book list
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Role role = roleService.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
        model.addAttribute("roles", role);
        return "role/edit"; 
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Role role,Model model) {
        roleService.save(role);
        model.addAttribute("message","New Role saved");
        return "redirect:/roles"; // Redirect to book list
    }


    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        roleService.deleteById(id);
        return "redirect:/roles"; // Redirect to book list
    }




}