package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class Panneau  {

    @Id
    String idPanneau;

    @Column
    Double puissanceMax;
    @Column
    String idBatterie;


    
    public void setIdPanneau(String idPanneau){
        this.idPanneau = idPanneau;
    }

    public String getIdPanneau(){
        return this.idPanneau;
    }


    public void setPuissanceMax(Double puissanceMax){
        this.puissanceMax = puissanceMax;
    }

    public Double getPuissanceMax(){
        return this.puissanceMax;
    }


    public void setIdBatterie(String idBatterie){
        this.idBatterie = idBatterie;
    }

    public String getIdBatterie(){
        return this.idBatterie;
    }



}