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
import java.sql.Date;

@Entity
@Table(name="utilisateur")

public class Utilisateur  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    String idUtilisateur;

    @Column
    String username;
    @Column
    String password;
    @Column
    Date dateNaissance;
    @Column
    String email;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_role")
    Role role;

    

    
    public void setIdUtilisateur(String idUtilisateur){
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdUtilisateur(){
        return this.idUtilisateur;
    }


    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }


    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }


    public void setDateNaissance(Date dateNaissance){
        this.dateNaissance = dateNaissance;
    }

    public Date getDateNaissance(){
        return this.dateNaissance;
    }


    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }


    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole(){
        return this.role;
    }



}