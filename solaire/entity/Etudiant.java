package com.solaire.solaire.solaire.entity;

import com.connection.database.BddObject;
import java.sql.Date;


public class Etudiant extends BddObject {

    
    String etu;

    @ColumnName(name="teest")
    String nom;
    @ColumnName(name="teest")
    String prenom;
    @ColumnName(name="teest")
    Date dateNaissance;

    
    public Etudiant (){
        setTable("etudiant");
        setConnection("PostgreSQL");
        setPrefix("ETU");
        setPrimaryKeyName("etu");
        setFunctionPK("next_val('seq_etudiant'");
        setCountPK(7);
    }


    
    public void setEtu(String etu){
        this.etu = etu;
    }

    public String getEtu(){
        return this.etu;
    }


    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return this.nom;
    }


    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getPrenom(){
        return this.prenom;
    }


    public void setDateNaissance(Date dateNaissance){
        this.dateNaissance = dateNaissance;
    }

    public Date getDateNaissance(){
        return this.dateNaissance;
    }



}