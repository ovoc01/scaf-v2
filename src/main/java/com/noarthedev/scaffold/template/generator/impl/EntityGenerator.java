package com.noarthedev.scaffold.template.generator.impl;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.generator.CodeGenerator;

public class EntityGenerator extends CodeGenerator {

   public EntityGenerator(TableSchema schema,String template) {
      super(schema,template);
   }

   

   @Override
   protected String replacePlaceHolder(String template, String... placeHeloder) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'replacePlaceHolder'");
   }

   @Override
   protected String importsToDo() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'importsToDo'");
   }
   
}
