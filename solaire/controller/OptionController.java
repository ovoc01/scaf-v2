package com.solaire.solaire.solaire.controller;

import com.framework.annotation.*;
import com.framework.ModelView;
import com.solaire.solaire.solaire.entity.Option;



public class OptionController extends Option {
    
    

    @restAPI
    @RequestBody
    @url(value = "options.do", method = "POST", error = "error.do")
    public void insert() throws Exception{
        this.insert(null);
    }



    @restAPI
    @url(value = "options.do",method = "GET", error = "error.do")
    public ModelView findAll() throws Exception{
        ModelView view = new ModelView();
        view.addItem("options",this.findAll(null));
        return view;
    }

    @restAPI
    @RequestBody
    @url(value = "options.do",method = "PUT", error = "error.do")
    public void update() throws Exception{
        this.update(null);
    }





}