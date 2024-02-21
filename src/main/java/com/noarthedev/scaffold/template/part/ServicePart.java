package com.noarthedev.scaffold.template.part;

import java.util.Map;

import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicePart extends BasePart{
   private String crudTemplate;

   public ServicePart() {
      super("service");
      
   }

   @Override
   public ServicePart fromFileContent(Map<String, Object> map, ProgrammingLangSyntax pSyntax){
      ServicePart servicePart = new ServicePart();
      BasePart pBasePart = super.fromFileContent(map, pSyntax);


      pBasePart.completeChild(servicePart);
      servicePart.setPSyntax(pSyntax);

      //additional stuff if needed

      return servicePart;
   }


   
   
}
