package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class SourceEnergie  {

    @Id
    String idSource;

    @Column
    String idSecteur;
    @Column
    String idPanneau;


    
    public void setIdSource(String idSource){
        this.idSource = idSource;
    }

    public String getIdSource(){
        return this.idSource;
    }


    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }

    public String getIdSecteur(){
        return this.idSecteur;
    }


    public void setIdPanneau(String idPanneau){
        this.idPanneau = idPanneau;
    }

    public String getIdPanneau(){
        return this.idPanneau;
    }



}