package com.noarthedev.scaffold.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
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

  public void run(String buildTools, String lang, String framework, String groupId, String projectName,String fileToGenerate)
      throws SQLException, IOException, InterruptedException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    Connection c = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    final String lang_template = Helper.readInputStream(
        App.class.getClassLoader().getResourceAsStream(String.format("configuration/%s.temp", lang)));

    final String frameworkTemplate = Helper.readInputStream(
        App.class.getClassLoader()
            .getResourceAsStream(String.format("configuration/framework/%s.properties", framework)));

    // System.out.println(frameworkTemplate);

    // TODO: prendre les informations en plus des templates
    // (services,repository,controller)
    Map<String, String> extraTemplateInformation = null;

    db = new DatabaseSchema();
    langInUse = new ProgrammingLangSyntax().build(lang_template);
    langInUse.setLang(lang);

    frameworkInUse = Framework.build(frameworkTemplate, langInUse);

    generateNewProject(frameworkInUse.getCliProjectGeneration(),projectName,groupId,buildTools);

    db.toObject(c, langInUse, frameworkInUse, groupId, projectName,fileToGenerate);
  }

  public void generateNewProject(String command,String projectName,String groupId,String buildTools) throws IOException, InterruptedException {
    command = command.replace("##build", buildTools).replace("##basePackage", groupId).replace("##projectName", projectName);
    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.command("bash", "-c", command);

    Process process = processBuilder.start();

    // Read output from the command
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }

    int exitCode = process.waitFor();
    System.out.println("Exited with error code " + exitCode);

  }
}
