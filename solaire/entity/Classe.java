package com.solaire.solaire.solaire.entity;

import com.connection.database.BddObject;


public class Classe extends BddObject {

    
    String idClasse;

    @ColumnName(name="teest")
    String nom;

    
    public Classe (){
        setTable("classe");
        setConnection("PostgreSQL");
        setPrefix("CLA");
        setPrimaryKeyName("id_classe");
        setFunctionPK("next_val('seq_classe'");
        setCountPK(7);
    }


    
    public void setIdClasse(String idClasse){
        this.idClasse = idClasse;
    }

    public String getIdClasse(){
        return this.idClasse;
    }


    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }



}