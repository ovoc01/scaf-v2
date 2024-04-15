package com.solaire.solaire.solaire.controller;

import com.framework.annotation.*;
import com.framework.ModelView;
import com.solaire.solaire.solaire.entity.Classe;



public class ClasseController extends Classe {
    
    

    @restAPI
    @RequestBody
    @url(value = "classes.do", method = "POST", error = "error.do")
    public void insert() throws Exception{
        this.insert(null);
    }



    @restAPI
    @url(value = "classes.do",method = "GET", error = "error.do")
    public ModelView findAll() throws Exception{
        ModelView view = new ModelView();
        view.addItem("classes",this.findAll(null));
        return view;
    }

    @restAPI
    @RequestBody
    @url(value = "classes.do",method = "PUT", error = "error.do")
    public void update() throws Exception{
        this.update(null);
    }





}