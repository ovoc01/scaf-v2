package com.noarthedev.scaffold.template.lang;

import java.util.HashMap;
import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProgrammingLangSyntax {

  private String lang;

  String packageName;
  String importName;
  String classDeclaration;
  String interfaceDeclaration;
  String openBrackets;
  String closeBrackets;
  String getterAndSetter;
  Map<String, DbField> dbType;
  String inheritance;
  String annotation;
  String fileExtension;

  final String SYNTAX_CASE = "CAMEL_CASE";

  public ProgrammingLangSyntax build(String bruteTemplate) {
    final String partEnd = "!!";
    final String partBegin = "##";

    Map<String, Object> dataMap = Helper.stringToMap(bruteTemplate);
    ProgrammingLangSyntax pgls = new ProgrammingLangSyntax();
    pgls.setGetterAndSetter(
        Helper.retrieveFromString(
            bruteTemplate,
            partBegin,
            partEnd,
            "getters&&setters"));

    pgls.setClassDeclaration(dataMap.get("class-declaration").toString());
    pgls.setInterfaceDeclaration(dataMap.get("interface-declaration").toString());
    pgls.setPackageName(dataMap.get("package").toString());
    pgls.setImportName(dataMap.get("import").toString());
    pgls.setOpenBrackets(dataMap.get("open-brackets").toString());
    pgls.setCloseBrackets(dataMap.get("close-brackets").toString());
    pgls.setInheritance(dataMap.get("inheritance-syntax").toString());
    pgls.setAnnotation(dataMap.get("annotation-syntax").toString());
    pgls.setFileExtension(dataMap.get("file-extension").toString());

    // initialize db type

    String typeSplited = Helper.retrieveFromString(
        bruteTemplate,
        partBegin,
        partEnd,
        "dbFieldCorrespondance");
    pgls.setDbType(pgls.initDbType(typeSplited));

    // System.out.println(pgls.toString());
    return pgls;
  }

  public Map<String, DbField> initDbType(String typeSplited) {
    Map<String, DbField> dbField = new HashMap<>();
    Map<String, Object> stringToMap = Helper.stringToMap(typeSplited);

    for (String key : stringToMap.keySet()) {
      dbField.put(key, DbField.initDbField((String) stringToMap.get(key)));
    }

    return dbField;
  }

  /* public void prepareTemplate() {
    template = template.replace("[open-brackets]", openBrackets);
    template = template.replace("[close-brackets]", closeBrackets);
    template = template.replace("[class-declaration]", classDeclaration);
  } */
}
