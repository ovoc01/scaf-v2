package com.solaire.solaire.solaire.controller;

import com.framework.annotation.*;
import com.framework.ModelView;
import com.solaire.solaire.solaire.entity.Etudiant;



public class EtudiantController extends Etudiant {
    
    

    @restAPI
    @RequestBody
    @url(value = "etudiants.do", method = "POST", error = "error.do")
    public void insert() throws Exception{
        this.insert(null);
    }



    @restAPI
    @url(value = "etudiants.do",method = "GET", error = "error.do")
    public ModelView findAll() throws Exception{
        ModelView view = new ModelView();
        view.addItem("etudiants",this.findAll(null));
        return view;
    }

    @restAPI
    @RequestBody
    @url(value = "etudiants.do",method = "PUT", error = "error.do")
    public void update() throws Exception{
        this.update(null);
    }





}