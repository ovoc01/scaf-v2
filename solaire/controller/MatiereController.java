package com.solaire.solaire.solaire.controller;

import com.framework.annotation.*;
import com.framework.ModelView;
import com.solaire.solaire.solaire.entity.Matiere;



public class MatiereController extends Matiere {
    
    

    @restAPI
    @RequestBody
    @url(value = "matieres.do", method = "POST", error = "error.do")
    public void insert() throws Exception{
        this.insert(null);
    }



    @restAPI
    @url(value = "matieres.do",method = "GET", error = "error.do")
    public ModelView findAll() throws Exception{
        ModelView view = new ModelView();
        view.addItem("matieres",this.findAll(null));
        return view;
    }

    @restAPI
    @RequestBody
    @url(value = "matieres.do",method = "PUT", error = "error.do")
    public void update() throws Exception{
        this.update(null);
    }





}