package com.noarthedev.scaffold.template.part;



import java.util.Map;

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
   public RepositoryPart fromFileContent(Map<String, Object> map, ProgrammingLangSyntax pSyntax){
      RepositoryPart repositoryPart = new RepositoryPart();
      BasePart pBasePart = super.fromFileContent(map, pSyntax);


      pBasePart.completeChild(repositoryPart);
      repositoryPart.setPSyntax(pSyntax);

      //additional stuff if needed

      return repositoryPart;
   }
}