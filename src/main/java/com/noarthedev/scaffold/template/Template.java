package com.noarthedev.scaffold.template;

import java.io.InputStream;
import java.util.Map;

import com.noarthedev.scaffold.App;
import com.noarthedev.scaffold.helper.Helper;

public class Template {

  public final String readTemplateAs(String language, String templateType) {

    InputStream templateInputStream = App.class.getClassLoader()
        .getResourceAsStream(String.format("template/%s.temp", templateType));

    InputStream langInputStream = App.class.getClassLoader()
        .getResourceAsStream(
            String.format("configuration/%s.temp", language));

    //System.out.println("atooo");
    String template = Helper.readInputStream(templateInputStream);
    String lang = Helper.readInputStream(langInputStream);
    Map<String, Object> map = Helper.stringToMap(lang);

    return template;
  }

  /* private String replaceWithMap(String template, Map<String, Object> data) {
    String result = template;
    for (String key : data.keySet()) {
      result = result.replace(String.format("[%s]", key), data.get(key).toString());
    }
    return result;
  } */
}
