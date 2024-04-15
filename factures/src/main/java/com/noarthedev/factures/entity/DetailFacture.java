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

@Entity
@Table(name="detail_facture")

public class DetailFacture  {

    @Id
    String id;

    @Column
    Double quantite;
    @Column
    Double pu;
    @Column
    Double montant;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="article")
    @JsonBackReference
    Article article;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idfacture")
    @JsonBackReference
    Facture facture;

    

    
    public void setQuantite(Double quantite){
        this.quantite = quantite;
    }

    public Double getQuantite(){
        return this.quantite;
    }


    public void setPu(Double pu){
        this.pu = pu;
    }

    public Double getPu(){
        return this.pu;
    }


    public void setMontant(Double montant){
        this.montant = montant;
    }

    public Double getMontant(){
        return this.montant;
    }


    public void setArticle(Article article){
        this.article = article;
    }

    public Article getArticle(){
        return this.article;
    }


    public void setFacture(Facture facture){
        this.facture = facture;
    }

    public Facture getFacture(){
        return this.facture;
    }



}