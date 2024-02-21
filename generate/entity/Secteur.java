package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class Secteur  {

    @Id
    String idSecteur;

    @Column
    String libelle;


    
    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }

    public String getIdSecteur(){
        return this.idSecteur;
    }


    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    public String getLibelle(){
        return this.libelle;
    }



}