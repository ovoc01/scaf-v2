package com.noarthedev.scaffold.mapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class DatabaseSchema {

  List<TableSchema> tables;

  public void toObject(Connection c, ProgrammingLangSyntax pLangSyntax, Framework frameworkInUse, String groupId,
      String projectName,String fileToGenerate,boolean isCliGenerationPresent) throws SQLException {
    final String BASE_PACKAGE = groupId;
    final String FILE_EXTENSION = pLangSyntax.getFileExtension();
    String outputDir = "";
    if(!isCliGenerationPresent)outputDir = String.format("%s/src/main/java/%s/%s/", projectName, groupId.replace(".", "/"), projectName);
    else outputDir = String.format("%s/", projectName);

    tables = new ArrayList<>();
    DatabaseMetaData databaseMetaData = c.getMetaData();
    ResultSet tablesResultSet = databaseMetaData.getTables(
        null,
        null,
        null,
        new String[] { "TABLE" });

    try {
      while (tablesResultSet.next()) {
        TableSchema tableSchema = new TableSchema(tablesResultSet, pLangSyntax, null, null);

        // System.out.println(tableSchema.getTableName());
        ResultSet primaryKeysResultSet = databaseMetaData.getPrimaryKeys(
            null,
            null,
            tableSchema.getTableName());
        tableSchema.initOptionalPrimaryKey(primaryKeysResultSet);

        ResultSet otherColResultSet = databaseMetaData.getColumns(
            null,
            null,
            tableSchema.getTableName(),
            null);

        tableSchema.intOtherColumn(otherColResultSet);

        ArrayList<CodeGenerator> toGenerate = CodeGenerator.generate(tableSchema, frameworkInUse, BASE_PACKAGE,
            fileToGenerate);

        if (!toGenerate.isEmpty()) {
          for (CodeGenerator codeGenerator : toGenerate) {
            Helper.generateFile(codeGenerator.getFileToGenerateName(), pLangSyntax.getFileExtension(),
                codeGenerator.generate(), outputDir + codeGenerator.getType());
          }
        }
        // generator = new RepositoryGenerator(tableSchema, frameworkInUse,
        // BASE_PACKAGE);

        tables.add(tableSchema);
        // break;
      }
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException | SQLException | IOException e) {

      e.printStackTrace();
    }

  }
}
