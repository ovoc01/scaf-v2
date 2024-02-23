package com.noarthedev.scaffold.run;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import com.noarthedev.scaffold.App;
import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.mapping.DatabaseSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class GenerationSession {

  ProgrammingLangSyntax langInUse;
  Framework frameworkInUse;
  DatabaseSchema db;
  final String JDBC_URL = "jdbc:postgresql://localhost:5432/panneau";
  final String USER = "postgres";
  final String PASSWORD = "pixel";

  public void run(String outputDir) throws SQLException, IOException {
    Connection c = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    final String lang_template = Helper.readInputStream(
        App.class.getClassLoader().getResourceAsStream("configuration/java.temp"));


    final String frameworkTemplate = Helper.readInputStream(
        App.class.getClassLoader()
            .getResourceAsStream("configuration/framework/spring.properties"));

    
    //System.out.println(frameworkTemplate);
    
    //TODO: prendre les informations en plus des templates (services,repository,controller)
    Map<String,String> extraTemplateInformation = null;


    db = new DatabaseSchema();
    langInUse = new ProgrammingLangSyntax().build(lang_template);
    langInUse.setLang("java");

    

    
    frameworkInUse = Framework.build(frameworkTemplate, langInUse);
    db.toObject(c, langInUse, frameworkInUse);


  }
}
