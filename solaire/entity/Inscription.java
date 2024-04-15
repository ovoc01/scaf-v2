package com.solaire.solaire.solaire.entity;

import com.connection.database.BddObject;
import java.sql.Date;


public class Inscription extends BddObject {

    
    String idInscription;

    @ColumnName(name="teest")
    Date date;

    Etudiant etudiant;

    Semestre semestre;

    
    public Inscription (){
        setTable("inscription");
        setConnection("PostgreSQL");
        setPrefix("INS");
        setPrimaryKeyName("id_inscription");
        setFunctionPK("next_val('seq_inscription'");
        setCountPK(7);
    }


    
    public void setIdInscription(String idInscription){
        this.idInscription = idInscription;
    }

    public String getIdInscription(){
        return this.idInscription;
    }


    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }


    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }

    public Etudiant getEtudiant(){
        return this.etudiant;
    }


    public void setSemestre(Semestre semestre){
        this.semestre = semestre;
    }

    public Semestre getSemestre(){
        return this.semestre;
    }



}