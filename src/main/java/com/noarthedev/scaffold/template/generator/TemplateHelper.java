package com.noarthedev.scaffold.template.generator;

import java.util.Map;

public class TemplateHelper {
   public static String replacePlaceholders(String template,Map<String,String> placeHolder) {
    String result = template;
    //System.out.println(template);
    for(String key: placeHolder.keySet()){
      //System.out.println(key);
       result =  result.replace(key, placeHolder.get(key));
      }
    return result;
  }
  public static String formatInheritance(String inheritance) {
      //TODO:
      return null;
  }
  public static String formatAnnotation(String annotationMarks) {
      //TODO:
      return null;
  }
}
