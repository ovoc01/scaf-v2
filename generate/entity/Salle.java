package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class Salle  {

    @Id
    String idSalle;

    @Column
    String libelle;
    @Column
    String idSecteur;


    
    public void setIdSalle(String idSalle){
        this.idSalle = idSalle;
    }

    public String getIdSalle(){
        return this.idSalle;
    }


    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    public String getLibelle(){
        return this.libelle;
    }


    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }

    public String getIdSecteur(){
        return this.idSecteur;
    }



}