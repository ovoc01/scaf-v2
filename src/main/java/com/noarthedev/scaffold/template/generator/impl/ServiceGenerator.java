package com.noarthedev.scaffold.template.generator.impl;

import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;

public class ServiceGenerator extends CodeGenerator {

   public ServiceGenerator(TableSchema schema, Framework inUse, String BASE_PACKAGE) {
      super(schema, inUse, BASE_PACKAGE,"service");
   }

   @Override
   protected String getTemplates() {
      return inUse.getService().getTemplate();
   }

   @Override
   protected String importsToDo() {
      StringBuilder sb = new StringBuilder();
      sb.append(inUse.getService().importsToDo()).append("\n");
      sb.append("import " + BASE_PACKAGE + ".entity.").append(schema.entityName()).append(";").append("\n");
      sb.append("import " + BASE_PACKAGE + ".repository.").append(schema.entityName()+"Repository").append(";");

      return sb.toString();
   }

   @Override
   protected String getPackageName() {
      return String.format("%s %s.service;",inUse.getPSyntax().getPackageName(), BASE_PACKAGE);
   }

   @Override
   protected String getAnnotations() {
      return inUse.getService().entityAnnotations();
   }

   @Override
   protected String getInheritance() {
      return inUse.getService().getInheritance();
   }

   @Override
   protected String getInjectionAnnotation() {
      return String.format("%s", inUse.getPSyntax().getAnnotation().replace(":mark", inUse.getService().getInjectionAnnotation()));
   }

   @Override
   protected String implementationOfExtraReplacement(String temp) {
      temp = temp
      .replace("[service-methods]", inUse.getService().getMethods());

      return temp;
   }

}
