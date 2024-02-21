package com.noarthedev.scaffold.template.part;

import java.util.Map;
import java.util.Optional;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityPart extends BasePart {
   String idMarks;
   String columnMark;

   public EntityPart() {
      super("entity");
   }

   @Override
   public EntityPart fromFileContent(String fileContent, ProgrammingLangSyntax pSyntax) {
      EntityPart part = new EntityPart();
      part.setPSyntax(pSyntax);

      //
      BasePart basePart = super.fromFileContent(fileContent, pSyntax);
      Map<String, Object> map = Helper.stringToMap(fileContent);

      
      basePart.completeChild(part);


      part.setIdMarks(Optional.ofNullable(map.get("entity.id-marks")).orElse("").toString());
      part.setColumnMark(map.get("entity.column-mark").toString());

      return part;
   }

   public String importsToDo() {
      StringBuilder sb = new StringBuilder();
      for (String d : imports) {

         sb.append(pSyntax.getImportName() + " " + d + ";").append("\n");
      }
      sb.deleteCharAt(sb.length() - 1);
      return sb.toString();
   }

   public String entityAnnotations() {
      StringBuilder sb = new StringBuilder();
      for (String a : annotations) {
         String column = pSyntax.getAnnotation().replace(":mark", a);
         sb.append(column).append("\n");
      }

      return sb.toString();
   }

}
