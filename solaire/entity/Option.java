package com.solaire.solaire.solaire.entity;

import com.connection.database.BddObject;


public class Option extends BddObject {

    
    String idOption;

    @ColumnName(name="teest")
    String nom;

    
    public Option (){
        setTable("option");
        setConnection("PostgreSQL");
        setPrefix("OPT");
        setPrimaryKeyName("id_option");
        setFunctionPK("next_val('seq_option'");
        setCountPK(7);
    }


    
    public void setIdOption(String idOption){
        this.idOption = idOption;
    }

    public String getIdOption(){
        return this.idOption;
    }


    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }



}