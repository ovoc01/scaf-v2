package com.solaire.solaire.solaire.entity;

import com.connection.database.BddObject;
import java.sql.Date;


public class Note extends BddObject {

    
    String idNote;

    @ColumnName(name="teest")
    Date date;
    @ColumnName(name="teest")
    Double valeurNote;

    Etudiant etudiant;

    Matiere matiere;

    
    public Note (){
        setTable("note");
        setConnection("PostgreSQL");
        setPrefix("NO");
        setPrimaryKeyName("id_note");
        setFunctionPK("next_val('seq_note'");
        setCountPK(7);
    }


    
    public void setIdNote(String idNote){
        this.idNote = idNote;
    }

    public String getIdNote(){
        return this.idNote;
    }


    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    }


    public void setValeurNote(Double valeurNote){
        this.valeurNote = valeurNote;
    }

    public Double getValeurNote(){
        return this.valeurNote;
    }


    public void setEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
    }

    public Etudiant getEtudiant(){
        return this.etudiant;
    }


    public void setMatiere(Matiere matiere){
        this.matiere = matiere;
    }

    public Matiere getMatiere(){
        return this.matiere;
    }



}