package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class PointageV2  {

    @Id
    String idPointage;

    @Column
    String idSalle;
    @Column
    Date date;
    @Column
    Time heureDebut;
    @Column
    Time heureFin;
    @Column
    Integer nbrEtudiant;


    
    public void setIdPointage(String idPointage){
        this.idPointage = idPointage;
    }

    public String getIdPointage(){
        return this.idPointage;
    }


    public void setIdSalle(String idSalle){
        this.idSalle = idSalle;
    }

    public String getIdSalle(){
        return this.idSalle;
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


    public void setNbrEtudiant(Integer nbrEtudiant){
        this.nbrEtudiant = nbrEtudiant;
    }

    public Integer getNbrEtudiant(){
        return this.nbrEtudiant;
    }



}