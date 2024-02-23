package com.noarthedev.scaffold.template.generator.impl;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;

public class EntityGenerator extends CodeGenerator {

   public EntityGenerator(TableSchema schema,Framework inUse) {
      super(schema,inUse);
   }


   @Override
   protected String importsToDo() {
      return inUse.getEntity().importsToDo();
   }


   @Override
   protected String getTemplates() {
      return inUse.getEntity().getTemplate();
   }



   
}
