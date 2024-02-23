package com.noarthedev.scaffold.template.generator;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class CodeGenerator {
   protected final TableSchema schema ;
   protected final Framework inUse;


   protected abstract String getTemplates();
   

   protected  String replacePlaceHolder(String template,String... placeHeloder){
      //TODO:
      return null;
   }

   protected abstract String importsToDo();

   public String generate (){
      
      //TODO:
      return null;
   }


}
