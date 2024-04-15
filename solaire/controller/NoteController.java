package com.solaire.solaire.solaire.controller;

import com.framework.annotation.*;
import com.framework.ModelView;
import com.solaire.solaire.solaire.entity.Note;



public class NoteController extends Note {
    
    

    @restAPI
    @RequestBody
    @url(value = "notes.do", method = "POST", error = "error.do")
    public void insert() throws Exception{
        this.insert(null);
    }



    @restAPI
    @url(value = "notes.do",method = "GET", error = "error.do")
    public ModelView findAll() throws Exception{
        ModelView view = new ModelView();
        view.addItem("notes",this.findAll(null));
        return view;
    }

    @restAPI
    @RequestBody
    @url(value = "notes.do",method = "PUT", error = "error.do")
    public void update() throws Exception{
        this.update(null);
    }





}