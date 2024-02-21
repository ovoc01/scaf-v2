package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class LuminositeV2  {

    @Id
    String idLuminosite;

    @Column
    Date date;
    @Column
    Time heureDebut;
    @Column
    Time heureFin;
    @Column
    Double luminosite;


    
    public void setIdLuminosite(String idLuminosite){
        this.idLuminosite = idLuminosite;
    }

    public String getIdLuminosite(){
        return this.idLuminosite;
    }


    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }


    public void setHeureDebut(Time heureDebut){
        this.heureDebut = heureDebut;
    }

    public Time getHeureDebut(){
        return this.heureDebut;
    }


    public void setHeureFin(Time heureFin){
        this.heureFin = heureFin;
    }

    public Time getHeureFin(){
        return this.heureFin;
    }


    public void setLuminosite(Double luminosite){
        this.luminosite = luminosite;
    }

    public Double getLuminosite(){
        return this.luminosite;
    }



}