package com.noarthedev.scaffold.mapping;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class DatabaseSchema {

  List<TableSchema> tables;

  public void toObject(Connection c, ProgrammingLangSyntax pLangSyntax, Framework frameworkInUse) throws SQLException, IOException {
    final String ENTIY_PACKAGE = "com.noarthedev.scaffolding.entity";
    final String REPO_PACKAGE = "com.noarthedev.scaffolding.repository";
    final String FILE_EXTENSION = "java";


    tables = new ArrayList<>();
    DatabaseMetaData databaseMetaData = c.getMetaData();
    ResultSet tablesResultSet = databaseMetaData.getTables(
        null,
        null,
        null,
        new String[] { "TABLE" });
    while (tablesResultSet.next()) {
      TableSchema tableSchema = new TableSchema(tablesResultSet, pLangSyntax,ENTIY_PACKAGE,REPO_PACKAGE);
      
      System.out.println(tableSchema.getTableName());
      ResultSet primaryKeysResultSet = databaseMetaData.getPrimaryKeys(
          null,
          null,
          tableSchema.getTableName());
      tableSchema.initOptionalPrimaryKey(primaryKeysResultSet);

      /*
       * ResultSet foreignKeysResultSet = databaseMetaData.getImportedKeys(
       * null,
       * null,
       * tableSchema.getTableName()
       * );
       */

      ResultSet otherColResultSet = databaseMetaData.getColumns(
          null,
          null,
          tableSchema.getTableName(),
          null);

      tableSchema.intOtherColumn(otherColResultSet);
      //System.out.println(tableSchema.toEntity(frameworkInUse,ENTIY_PACKAGE));
      
      //Helper.generateFile(tableSchema.entityName(),FILE_EXTENSION, tableSchema.toEntity(frameworkInUse), String.format("%s/%s","generate","entity"));

      //Helper.generateFile(tableSchema.repositoryName(),FILE_EXTENSION, tableSchema.toRepository(frameworkInUse), String.format("%s/%s","generate","repository"));

      tableSchema.toService(frameworkInUse);

      tables.add(tableSchema);
      //break;
    }
  }
}
