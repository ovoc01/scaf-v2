package com.noarthedev.scaffold.template.generator.impl;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;

public class RepositoryGenerator extends CodeGenerator {

   public RepositoryGenerator(TableSchema schema, Framework inUse, String BASE_PACKAGE) {
      super(schema, inUse, BASE_PACKAGE);

   }

   @Override
   protected String getTemplates() {
      return inUse.getRepository().getTemplate();
   }

   @Override
   protected String importsToDo() {

      
      StringBuilder sb = new StringBuilder();
      sb.append(inUse.getRepository().importsToDo()).append("\n");
      sb.append("import " + BASE_PACKAGE + ".entity.").append(schema.entityName()).append(";");

      return sb.toString();
   }

   @Override
   protected String getPackageName() {
      return String.format("%s %s.repository;",inUse.getPSyntax().getPackageName(), BASE_PACKAGE);
   }

   

   @Override
   protected String getAnnotations() {
      return inUse.getRepository().entityAnnotations();
   }

   @Override
   protected String getInheritance() {
      return inUse.getRepository().getInheritance();
   }

   @Override
   protected String getInjectionAnnotation() {
      // Return an empty string because for now there is no Entity in our framework
      // who need an field injection
      // FIXME: Later
      return "";
   }



}
