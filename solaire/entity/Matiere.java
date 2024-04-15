package com.solaire.solaire.solaire.entity;

import com.connection.database.BddObject;


public class Matiere extends BddObject {

    
    String idMatiere;

    @ColumnName(name="teest")
    String nom;
    @ColumnName(name="teest")
    Integer coefficient;

    
    public Matiere (){
        setTable("matiere");
        setConnection("PostgreSQL");
        setPrefix("MAT");
        setPrimaryKeyName("id_matiere");
        setFunctionPK("next_val('seq_matiere'");
        setCountPK(7);
    }


    
    public void setIdMatiere(String idMatiere){
        this.idMatiere = idMatiere;
    }

    public String getIdMatiere(){
        return this.idMatiere;
    }


    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }


    public void setCoefficient(Integer coefficient){
        this.coefficient = coefficient;
    }

    public Integer getCoefficient(){
        return this.coefficient;
    }



}