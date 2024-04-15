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
@Table(name="article")

public class Article  {

    @Id
    String id;

    @Column
    String code;
    @Column
    String designation;
    @Column
    Double pu;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="article", cascade=CascadeType.ALL)
    @JsonBackReference    
    List<DetailFacture>detailFacture;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="type")
    @JsonManagedReference
    TypeArticle typeArticle;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="unite")
    @JsonManagedReference
    UniteArticle uniteArticle;

    

    
    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }


    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }


    public void setDesignation(String designation){
        this.designation = designation;
    }

    public String getDesignation(){
        return this.designation;
    }


    public void setPu(Double pu){
        this.pu = pu;
    }

    public Double getPu(){
        return this.pu;
    }


    public void setTypeArticle(TypeArticle typeArticle){
        this.typeArticle = typeArticle;
    }

    public TypeArticle getTypeArticle(){
        return this.typeArticle;
    }


    public void setUniteArticle(UniteArticle uniteArticle){
        this.uniteArticle = uniteArticle;
    }

    public UniteArticle getUniteArticle(){
        return this.uniteArticle;
    }



}