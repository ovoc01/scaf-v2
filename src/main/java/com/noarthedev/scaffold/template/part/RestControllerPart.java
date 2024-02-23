package com.noarthedev.scaffold.template.part;

import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

public class RestControllerPart extends BasePart{

   public RestControllerPart() {
      super("rest-controller");
   }

   @Override
   public RestControllerPart fromFileContent(String fileContent, ProgrammingLangSyntax pSyntax){
      RestControllerPart restControllerPart = new RestControllerPart();
      final String partEnd = "!!";
      final String partBegin = "##";

      BasePart pBasePart = super.fromFileContent(fileContent, pSyntax);
      Map<String, Object> map = Helper.stringToMap(fileContent);

      restControllerPart.setMethods(
        Helper.retrieveFromString(
            fileContent,
            partBegin,
            partEnd,
            "controller-methods"));

      
      restControllerPart.setInjectionAnnotation(map.get("rest-controller.injection-annotation").toString());
      

      
      //System.out.println(methods);

      restControllerPart.setPSyntax(pSyntax);
      pBasePart.completeChild(restControllerPart);


      return restControllerPart;
   }
   
}
