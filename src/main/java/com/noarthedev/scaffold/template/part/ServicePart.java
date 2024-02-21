package com.noarthedev.scaffold.template.part;

import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicePart extends BasePart {

   public ServicePart() {
      super("service");

   }

   @Override
   public ServicePart fromFileContent(String fileContent, ProgrammingLangSyntax pSyntax) {
      ServicePart servicePart = new ServicePart();
      final String partEnd = "!!";
      final String partBegin = "##";

      BasePart pBasePart = super.fromFileContent(fileContent, pSyntax);

       servicePart.setMethods(
        Helper.retrieveFromString(
            fileContent,
            partBegin,
            partEnd,
            "service-methods"));

      
      

      
      //System.out.println(methods);

      pBasePart.completeChild(servicePart);
      servicePart.setPSyntax(pSyntax);

      //setInheritance("");
      // additional stuff if needed

      return servicePart;
   }

}
