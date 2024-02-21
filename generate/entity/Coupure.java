package com.noarthedev.scaffolding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table

public class Coupure  {

    @Id
    String idCoupure;

    @Column
    String idSecteur;
    @Column
    Date date;
    @Column
    Time heureDebut;


    
    public void setIdCoupure(String idCoupure){
        this.idCoupure = idCoupure;
    }

    public String getIdCoupure(){
        return this.idCoupure;
    }


    public void setIdSecteur(String idSecteur){
        this.idSecteur = idSecteur;
    }

    public String getIdSecteur(){
        return this.idSecteur;
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



}