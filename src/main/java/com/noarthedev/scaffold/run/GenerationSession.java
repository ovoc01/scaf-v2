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
import com.noarthedev.scaffold.props.ScaffoldProps;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class GenerationSession {

  ProgrammingLangSyntax langInUse;
  Framework frameworkInUse;
  DatabaseSchema db;

  public void run(ScaffoldProps props)
      throws SQLException, IOException, InterruptedException, NoSuchMethodException, SecurityException,
      InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
    Connection c = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPwd());
    final String lang_template = Helper.readInputStream(
        App.class.getClassLoader().getResourceAsStream(String.format("configuration/%s.temp", props.getLang())));

    final String frameworkTemplate = Helper.readInputStream(
        App.class.getClassLoader()
            .getResourceAsStream(String.format("configuration/framework/%s.properties", props.getFramework())));

    // System.out.println(frameworkTemplate);

    // TODO: prendre les informations en plus des templates
    // (services,repository,controller)
    Map<String, String> extraTemplateInformation = null;

    db = new DatabaseSchema();
    langInUse = new ProgrammingLangSyntax().build(lang_template);
    langInUse.setLang(props.getLang());

    frameworkInUse = Framework.build(frameworkTemplate, langInUse);

    generateNewProject(frameworkInUse.getCliProjectGeneration(), props.getProjectName(), props.getGroupId(),
        props.getBuild());
    db.toObject(c, langInUse, frameworkInUse, props.getGroupId(), props.getProjectName(), props.getFileToGenerate());
  }

  public void generateNewProject(String command, String projectName, String groupId, String buildTools)
      throws IOException, InterruptedException {
    command = command.replace("##build", buildTools).replace("##basePackage", groupId).replace("##projectName",
        projectName);
    ProcessBuilder processBuilder = new ProcessBuilder();
    boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    if (isWindows) {
      processBuilder.command("cmd", "/c", command);
    } else {
      processBuilder.command("bash", "-c", command);
    }

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
