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
@Table(name="type_article")

public class TypeArticle  {

    @Id
    String id;

    @Column
    String description;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="typeArticle", cascade=CascadeType.ALL)
    @JsonBackReference    
    List<Article>article;

    

    
    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }


    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }



}