package com.noarthedev.scaffold.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data

public class TableSchema {

  String tableName;
  List<Column> columns;
  List<ForeignKey> foreignKeys;
  Optional<PrimaryKey> primaryKey;
  ProgrammingLangSyntax pSyntax;
  final String ENTITY_PACKAGE;
  final String REPO_PACKAGE;

  public TableSchema(ResultSet rSet, ProgrammingLangSyntax programmingLangSyntax, String entity_pack, String repo_pack)
      throws SQLException {
    setTableName(rSet.getString("TABLE_NAME"));
    // System.out.println(getTableName());
    setPSyntax(programmingLangSyntax);
    ENTITY_PACKAGE = entity_pack;
    REPO_PACKAGE = repo_pack;
    // System.out.println(programmingLangSyntax.getTemplate());
  }

  public void initOptionalPrimaryKey(ResultSet rSet) throws SQLException {
    while (rSet.next()) {
      PrimaryKey pk = new PrimaryKey();
      pk.init(rSet, pSyntax.getDbType());
      primaryKey = Optional.of(pk);
    }
  }

  public void initForeignKey(ResultSet rSet) {
    // TODO: initialiser les clés etrangère
  }

  public void intOtherColumn(ResultSet rSet) throws SQLException {
    columns = new ArrayList<>();
    while (rSet.next()) {
      Column column = new Column();
      column.init(rSet, getPSyntax().getDbType());
      columns.add(column);
    }
  }


  public boolean isPrimaryKeyPresent(){
    return primaryKey != null && !primaryKey.isEmpty();
  }




  public String entityName() {
    return Helper.toPascalCase(tableName);
  }

  public String repositoryName() {
    return entityName() + "Repository";
  }

  public String serviceName() {
    return entityName() + "Service";
  }

  public String controllerName() {
    return entityName() + "Controller";
  }

  public String allColumns(Framework inUse) {
    StringBuilder sb = new StringBuilder();
    final String marks = pSyntax.getAnnotation();
    int a = 0;
    List<Column> real = realCol();
    for (Column col : real) {
      if (!col.equals(primaryKey.get())) {
        String coln = "";
        if(!inUse.getEntity().getColumnMark().equals("none")) {
          coln = marks.replace(":mark", inUse.getEntity().getColumnMark());
        }
        if (a > 0) {
          sb.append(Helper.identation());
        }
        sb
            .append(coln)
            .append("\n")
            .append(Helper.identation())
            .append(col.toString())
            .append("\n");
        a++;
      }
    }
    return sb.toString();
  }

  public List<Column> realCol() {
    List<Column> filteredColumns = new ArrayList<>(columns);
    if (foreignKeys != null) {
      for (ForeignKey foreignKey : foreignKeys) {
        filteredColumns.remove(foreignKey);
      }

    }

    if (primaryKey != null) {
      primaryKey.ifPresent(pk -> filteredColumns.remove(pk));
    }

    return filteredColumns;
  }

  public String allGetterAndSetters() {
    StringBuilder sb = new StringBuilder();
    // List<Column> real = realCol();
    for (Column col : columns) {
      sb.append(col.toGetterAndSetter(pSyntax));
    }
    return sb.toString();
  }

  public String columnImports() {
      StringBuilder sb = new StringBuilder();
      Set<String> importedColumns = new HashSet<>(); // Set to track duplicate strings

      for (Column col : columns) {
          if (col.getImportToDo() != null && !importedColumns.contains(col.getImportToDo())) {
              sb.append(col.getImportToDo()).append(",");
              importedColumns.add(col.getImportToDo());
          }
      }

      if (sb.length() > 0) {
          sb.setLength(sb.length() - 1); // Remove the last comma
      }

      return sb.toString();
  }


  public String tableNameToCamelCase(){
    return Helper.toCamelCase(tableName);
  }
}
