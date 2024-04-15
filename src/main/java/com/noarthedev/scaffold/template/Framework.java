package com.noarthedev.scaffold.template;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.noarthedev.scaffold.App;
import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;
import com.noarthedev.scaffold.template.part.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Framework {
   ProgrammingLangSyntax pSyntax;
   String name;
   String[] fileGenerable;
   String cliProjectGeneration;
   EntityPart entity;
   RepositoryPart repository;
   ServicePart service;
   RestControllerPart restController;


   private static final HashMap<String,Class<? extends BasePart>> partMap = new HashMap<>(){
      {
         put("entity", EntityPart.class);
         put("repository", RepositoryPart.class);
         put("service", ServicePart.class);
         put("rest-controller", RestControllerPart.class);
      }
   };


   public static Framework build(String fileContent, ProgrammingLangSyntax pSyntax) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
      Framework framework = new Framework();
      
      framework.setPSyntax(pSyntax);
      ////System.out.println("lang in use"+pSyntax);
      
      Map<String, Object> map = Helper.stringToMap(fileContent);

      ////System.out.println(fileContent);
      
      
      ////System.out.println(map.get("cli-project-generation"));
      framework.retrieveFileGenerable(map.get("file-generable").toString());
      framework.setCliProjectGeneration(map.get("cli-project-generation").toString());


      instanceNeededGenerator(framework,fileContent,pSyntax);

      ////System.out.println(framework.getEntity().getTemplate());
      return framework;
   }


   public static void instanceNeededGenerator(Framework framework,String fileContent,ProgrammingLangSyntax pSyntax) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
      for(String generable: framework.getFileGenerable()){
         Class<?extends BasePart> base = Framework.partMap.get(generable);
         if(base!=null){
            Constructor<?>  constructor = base.getConstructor();
            BasePart basePart = (BasePart) constructor.newInstance();

            String camelCasedField = Helper.toCamelCase(generable.replace("-","_"));
            
            App.LOGGER.info(camelCasedField);
            ////System.out.println(camelCasedField);

            Field field = framework.getClass().getDeclaredField(camelCasedField);
            field.setAccessible(true);

            field.set(framework, basePart.fromFileContent(fileContent,pSyntax));

         }
      }
   }

   public void retrieveFileGenerable(String fileContent) {
      this.fileGenerable = fileContent.toLowerCase().split(",");
   }

}
