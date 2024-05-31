package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@Service

public class RoleService  {
   @Autowired
   RoleRepository roleRepository;

   
    public List<Role> findAll() {
        return roleRepository.findAll();
    }


    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }


    public Role save(Role role) {
        return roleRepository.save(role);
    }


    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }



}