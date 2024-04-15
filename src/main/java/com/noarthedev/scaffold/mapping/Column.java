package com.noarthedev.scaffold.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.text.WordUtils;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.DbField;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class Column {

  String name;
  String type;
  String importToDo;

  public String getNameToCamelCase() {
    return Helper.toCamelCase(this.getName());
  }

  public String getLabel() {
    return WordUtils.capitalizeFully(this.getName(), '_').replaceAll("_", " ");
  }

  public void init(ResultSet rs, Map<String, DbField> dbField)
      throws SQLException {
    setName(rs.getString("COLUMN_NAME"));
    DbField dB = dbField.get(rs.getString("TYPE_NAME"));

    ////System.out.println("this is the db "+dB);
    System.out.printf("this is dB %s && this is the original type %s",dB,rs.getString("TYPE_NAME"));
    setType(dB.getName());

    setImportToDo(dB.getImports() == null ? null : dB.getImports().get());
    // //System.out.println(getType());
  }

  public String toString() {
    return String.format("%s %s;", getType(), Helper.toCamelCase(getName()));
  }

  public String toGetterAndSetter(ProgrammingLangSyntax pSyntax) {
    String temp = pSyntax.getGetterAndSetter();

    String setterName = String.format("set%s", Helper.toPascalCase(name));
    String getterName = String.format("get%s", Helper.toPascalCase(name));

    temp = temp.replaceAll("##fieldName", Helper.toCamelCase(name));
    temp = temp.replaceAll("##argsName", Helper.toCamelCase(name));
    temp = temp.replaceAll("##setterName", setterName);
    temp = temp.replaceAll("##getterName", getterName);
    temp = temp.replaceAll("##returnType", type);
    temp = temp.replaceAll("##argsType", type);
    temp = temp.replaceAll("##upperFieldName", Helper.toPascalCase(name));

    return temp;
  }
}
