package com.noarthedev.scaffold.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

  public String toEntity(Framework inUse) {
    // FIXME: transferer les template pour chaque type de fichier dans les BasePart
    // chid class

    /*
     * System.out.println( "test bogoss"+ inUse.getEntity()
     * .getTemplate());
     */
    inUse.getEntity().setTemplate(
        inUse.getEntity()
            .getTemplate()
            .replace("[imports]", inUse.getEntity().importsToDo()));

    inUse.getEntity().setTemplate(
        inUse.getEntity()
            .getTemplate()
            .replace("[package]", pSyntax.getPackageName() + " " + ENTITY_PACKAGE + ";"));

    String annotations = inUse.getEntity().entityAnnotations().isEmpty()
        ? ""
        : inUse.getEntity().entityAnnotations();
    String inheritance = inUse.getEntity().getInheritance().equals("none")
        ? ""
        : pSyntax.getInheritance() + " " + inUse.getEntity().getInheritance();

    inUse.getEntity().setTemplate(
        inUse.getEntity()
            .getTemplate()
            .replace("?@[annotation]", annotations));

    inUse.getEntity().setTemplate(
        inUse.getEntity()
            .getTemplate()
            .replace("?[inheritance]", inheritance));

    String temp = inUse.getEntity().getTemplate();
    // pSyntax.setTemplate(pSyntax.getTemplate()));
    // System.out.println(pSyntax.getTemplate());
    // System.out.println(temp.replace("[entityName]", entityName()));
    // System.out.println(primaryKey);

    if (primaryKey != null && !primaryKey.isEmpty()) {
      String idMarks = pSyntax
          .getAnnotation()
          .replace(":mark", inUse.getEntity().getIdMarks());
      temp = temp.replace("@?[id-marks]", idMarks);
      temp = temp.replace("?[Id]", primaryKey.get().toString());
    }

    temp = temp.replace("[columns]", allColumns(inUse));
    temp = temp.replace("[getters&&setters]", allGetterAndSetters());
    return temp.replace("[entityName]", entityName());
  }

  public String toRepository(Framework inUse) {
    
        
    inUse.getRepository().setTemplate(
        inUse.getRepository()
            .getTemplate()
            .replace("[package]", pSyntax.getPackageName() + " " + REPO_PACKAGE + ";"));

    String annotations = inUse.getRepository().entityAnnotations().isEmpty()
        ? ""
        : inUse.getRepository().entityAnnotations();
    String inheritance = inUse.getRepository().getInheritance().equals("none")
        ? ""
        : pSyntax.getInheritance() + " " + inUse.getRepository().getInheritance();

    inUse.getRepository().setTemplate(
        inUse.getRepository()
            .getTemplate()
            .replace("?[inheritance]", inheritance));

    inUse.getRepository().setTemplate(
        inUse.getRepository()
            .getTemplate()
            .replace("[annotation]", annotations));

    String temp = inUse.getRepository().getTemplate();
    String pkType = getPrimaryKey() != null ? getPrimaryKey().get().getType() : "Integer";

    temp = temp.replace("##primaryKeyType", pkType);
   temp = temp.replace("[imports]", inUse.getRepository().importsToDo()+String.format("\n%s %s.%s;", pSyntax.getImportName(),ENTITY_PACKAGE,entityName()));

    return temp.replace("##entityName", entityName());
  }

  public String entityName() {
    return Helper.toPascalCase(tableName);
  }

  public String allColumns(Framework inUse) {
    StringBuilder sb = new StringBuilder();
    final String marks = pSyntax.getAnnotation();
    int a = 0;
    List<Column> real = realCol();
    for (Column col : real) {
      if (!col.equals(primaryKey.get())) {
        String coln = marks.replace(":mark", inUse.getEntity().getColumnMark());
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

    return sb.toString();
  }
}
