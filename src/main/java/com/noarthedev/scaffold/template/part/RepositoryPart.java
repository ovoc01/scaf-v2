package com.noarthedev.scaffold.template.part;



import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class RepositoryPart extends BasePart{
   public RepositoryPart(){
      super("repository");
   }


   @Override
   public RepositoryPart fromFileContent(String fileContent, ProgrammingLangSyntax pSyntax){
      RepositoryPart repositoryPart = new RepositoryPart();
      BasePart pBasePart = super.fromFileContent(fileContent, pSyntax);

      //Map<String, Object> map = Helper.stringToMap(fileContent);


      pBasePart.completeChild(repositoryPart);
      repositoryPart.setPSyntax(pSyntax);

      //additional stuff if needed

      return repositoryPart;
   }
}