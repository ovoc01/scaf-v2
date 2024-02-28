package com.noarthedev.scaffold.template.generator.impl;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;

public class RestControllerGenerator extends CodeGenerator {

   public RestControllerGenerator(TableSchema schema, Framework inUse, String BASE_PACKAGE) {
      super(schema, inUse, BASE_PACKAGE, "controller");

   }

   @Override
   protected String getTemplates() {
      return inUse.getRestController().getTemplate();
   }

   @Override
   protected String importsToDo() {

      StringBuilder sb = new StringBuilder();
      sb.append(inUse.getRestController().importsToDo()).append("\n");
      sb.append("import " + BASE_PACKAGE + ".entity.").append(schema.entityName()).append(";").append("\n");

      System.out.println(inUse.getRestController().getInjectionAnnotation());
      if(!inUse.getRestController().getInjectionAnnotation().isEmpty()){
         sb.append("import " + BASE_PACKAGE + ".service.").append(schema.entityName() + "Service").append(";");
      }

      return sb.toString();
   }

   @Override
   protected String getPackageName() {
      return String.format("%s %s.controller;", inUse.getPSyntax().getPackageName(), BASE_PACKAGE);
   }

   @Override
   protected String getAnnotations() {
      return inUse.getRestController().entityAnnotations();
   }

   @Override
   protected String getInheritance() {
      return inUse.getRestController().getInheritance();
   }

   @Override
   protected String getInjectionAnnotation() {
      if(inUse.getRestController().getInjectionAnnotation().equals("none") || inUse.getRestController().getInjectionAnnotation().isEmpty()){
         return "";
      }
      return String.format("%s  %sService %sService;", inUse.getPSyntax().getAnnotation().replace(":mark",
              inUse.getRestController().getInjectionAnnotation()),schema.entityName(),schema.tableNameToCamelCase());
   }

   @Override
   protected String implementationOfExtraReplacement(String temp) {
      temp = temp
            .replace("[controller-methods]", inUse.getRestController().getMethods());
      return temp;
   }

}
