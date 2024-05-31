package com.example.demo.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="role")

public class Role  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    Long idRole;

    @Column
    String designation;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="role", cascade=CascadeType.ALL)    
    List<Utilisateur>utilisateur;

    

    
    public void setIdRole(Long idRole){
        this.idRole = idRole;
    }

    public Long getIdRole(){
        return this.idRole;
    }


    public void setDesignation(String designation){
        this.designation = designation;
    }

    public String getDesignation(){
        return this.designation;
    }



}