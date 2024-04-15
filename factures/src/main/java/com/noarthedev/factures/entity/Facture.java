package com.noarthedev.factures.entity;

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
@Table(name="facture")

public class Facture  {

    @Id
    String id;

    @Column
    Date daty;
    @Column
    String designation;
    @Column
    Double etat;
    @Column
    Double montanttotal;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="facture", cascade=CascadeType.ALL)
    @JsonManagedReference    
    List<DetailFacture>detailFacture;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="facture", cascade=CascadeType.ALL)
    @JsonManagedReference    
    List<Fac>fac;

    

    
    public void setDaty(Date daty){
        this.daty = daty;
    }

    public Date getDaty(){
        return this.daty;
    }


    public void setDesignation(String designation){
        this.designation = designation;
    }

    public String getDesignation(){
        return this.designation;
    }


    public void setEtat(Double etat){
        this.etat = etat;
    }

    public Double getEtat(){
        return this.etat;
    }


    public void setMontanttotal(Double montanttotal){
        this.montanttotal = montanttotal;
    }

    public Double getMontanttotal(){
        return this.montanttotal;
    }



}