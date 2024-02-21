package com.noarthedev.scaffold.template;

import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;
import com.noarthedev.scaffold.template.part.EntityPart;
import com.noarthedev.scaffold.template.part.RepositoryPart;
import com.noarthedev.scaffold.template.part.ServicePart;

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
   EntityPart entity;
   RepositoryPart repository;
   ServicePart service;

   public static Framework build(String fileContent, ProgrammingLangSyntax pSyntax) {
      Framework framework = new Framework();
      
      framework.setPSyntax(pSyntax);
      //System.out.println("lang in use"+pSyntax);
      
      Map<String, Object> map = Helper.stringToMap(fileContent);
      
      framework.retrieveFileGenerable(map.get("file-generable").toString());
      framework.setEntity(new EntityPart().fromFileContent(fileContent, pSyntax));
      framework.setRepository(new RepositoryPart().fromFileContent(fileContent, pSyntax));

      framework.setService(new ServicePart().fromFileContent(fileContent, pSyntax));
      
      //System.out.println(framework.getEntity().getTemplate());
      return framework;
   }

   public void retrieveFileGenerable(String fileContent) {
      this.fileGenerable = fileContent.split(",");
   }

}
