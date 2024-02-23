package com.noarthedev.scaffold.template.generator.impl;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;

public class RestControllerGenerator extends CodeGenerator{

   public RestControllerGenerator(TableSchema schema, Framework inUse, String BASE_PACKAGE) {
      super(schema, inUse, BASE_PACKAGE);
      
   }

   @Override
   protected String getTemplates() {
      return inUse.getRestControllerPart().getTemplate();
   }

   @Override
   protected String importsToDo() {
      
      StringBuilder sb = new StringBuilder();
      sb.append(inUse.getRestControllerPart().importsToDo()).append("\n");
      sb.append("import " + BASE_PACKAGE + ".entity.").append(schema.entityName()).append(";").append("\n");
      sb.append("import " + BASE_PACKAGE + ".service.").append(schema.entityName()+"Service").append(";");

      return sb.toString();
   }

   @Override
   protected String getPackageName() {
      return String.format("%s %s.controller;",inUse.getPSyntax().getPackageName(), BASE_PACKAGE);
   }

   @Override
   protected String getAnnotations() {
      return inUse.getRestControllerPart().entityAnnotations();
   }

   @Override
   protected String getInheritance() {
      return inUse.getRestControllerPart().getInheritance();
   }

   @Override
   protected String getInjectionAnnotation() {
      return String.format("%s", inUse.getPSyntax().getAnnotation().replace(":mark", inUse.getRestControllerPart().getInjectionAnnotation()));
   }

   @Override
   protected String implementationOfExtraReplacement(String temp) {
      temp = temp
      .replace("[controller-methods]", inUse.getRestControllerPart().getMethods());

      return temp;
   }
   
}
