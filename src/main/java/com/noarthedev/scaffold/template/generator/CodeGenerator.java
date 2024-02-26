package com.noarthedev.scaffold.template.generator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.impl.EntityGenerator;
import com.noarthedev.scaffold.template.generator.impl.RepositoryGenerator;
import com.noarthedev.scaffold.template.generator.impl.RestControllerGenerator;
import com.noarthedev.scaffold.template.generator.impl.ServiceGenerator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class CodeGenerator {
   protected final TableSchema schema;
   protected final Framework inUse;
   protected final String BASE_PACKAGE;
   protected final String type;

   private static final HashMap<String, Class<? extends CodeGenerator>> matching = new HashMap<>() {
      {
         put("entity", EntityGenerator.class);
         put("repository", RepositoryGenerator.class);
         put("service", ServiceGenerator.class);
         put("rest-controller", RestControllerGenerator.class);
      }
   };

   protected abstract String getTemplates();

   protected abstract String importsToDo();

   protected abstract String getPackageName();

   protected abstract String getAnnotations();

   protected abstract String getInheritance();

   protected abstract String getInjectionAnnotation();

   protected String implementationOfExtraReplacement(String template) {
      return template;
   }

   public String generate() {
      String template = getTemplates();

      Map<String, String> placeholder = new HashMap<>();

      placeholder.put("[annotation]", getAnnotations());
      placeholder.put("[inheritance]", inheritance());
      placeholder.put("[imports]", importsToDo());
      placeholder.put("[package]", getPackageName());
      placeholder.put("[entityName]", schema.entityName());
      placeholder.put("[tableName]", schema.tableNameToCamelCase());

      placeholder.put("##injection-annotation", getInjectionAnnotation());

      // Optional may throws an exception in case that the table may not have a
      // primary key

      // Those primary key related field should be null typely safe

      // TODO: update service, controller template to be able to render conditionally
      // method that include primaryKey

      placeholder.put("##primaryKeyType", schema.getPrimaryKey().get().getType());
      placeholder.put("##primaryKeyName", schema.getPrimaryKey().get().getName());

      template = implementationOfExtraReplacement(template);
      String generate = TemplateHelper.replacePlaceholders(template, placeholder);

      return generate;
   }

   private String inheritance() {
      String val = getInheritance();
      if (val == null || val.isEmpty() || val.equals("none"))
         return "";
      return String.format("%s %s", inUse.getPSyntax().getInheritance(), val);
   }

   
   public static ArrayList<CodeGenerator> generate(TableSchema schema, Framework framework, String basePackage,
         String fileToGenerate) throws NoSuchMethodException, SecurityException, InstantiationException,
         IllegalAccessException, IllegalArgumentException, InvocationTargetException {
      String[] fileToGen = fileToGenerate.trim().split(",");

      // FIXME: Check if duplicate fileToGen

      ArrayList<CodeGenerator> toGenerate = new ArrayList<>();
      int count = 0;
      for (String file : fileToGen) {
         Class<? extends CodeGenerator> clz = matching.get(file);
         if (clz != null) {
            Constructor<?> constructor = clz.getConstructor(TableSchema.class, Framework.class, String.class);
            CodeGenerator codeGenerator = (CodeGenerator) constructor.newInstance(schema, framework, basePackage);
            toGenerate.add(codeGenerator);
            count++;
         }
      }

      if (count == 0)
         throw new IllegalArgumentException("Unable to  generate file");

      return toGenerate;
   }

   public String getFileToGenerateName() {
      return String.format("%s", Helper.toPascalCase(schema.getTableName()) + Helper.toPascalCase(type));
   }

}
