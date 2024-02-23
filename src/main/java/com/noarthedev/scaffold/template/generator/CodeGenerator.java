package com.noarthedev.scaffold.template.generator;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class CodeGenerator {
   private final TableSchema schema ;
   private final String template;
   

   protected abstract String replacePlaceHolder(String template,String... placeHeloder);

   protected abstract String importsToDo();

   public String generate (Framework inUse){
      
      //TODO:
      return null;
   }


}
