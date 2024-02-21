package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class Batterie  {

    @Id
    String idBatterie;

    @Column
    Double capacite;


    
    public void setIdBatterie(String idBatterie){
        this.idBatterie = idBatterie;
    }

    public String getIdBatterie(){
        return this.idBatterie;
    }


    public void setCapacite(Double capacite){
        this.capacite = capacite;
    }

    public Double getCapacite(){
        return this.capacite;
    }



}