package com.tp.facture.entity;

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

@Entity
@Table(name="fac")

public class Fac  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    Long id;

    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_facture")
    Facture facture;

    

    
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }


    public void setFacture(Facture facture){
        this.facture = facture;
    }

    public Facture getFacture(){
        return this.facture;
    }



}