package com.noarthedev.scaffold.mapping;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.googlejavaformat.java.FormatterException;
import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class DatabaseSchema {

    List<TableSchema> tables;

    public void toObject(Connection c, ProgrammingLangSyntax pLangSyntax, Framework frameworkInUse, String groupId,
                         String projectName, String fileToGenerate, String tableToGenerate, boolean isCliGenerationPresent)
            throws SQLException, FormatterException {
        final String BASE_PACKAGE = groupId + "." + projectName;
        boolean generateAllFiles = fileToGenerate.equals("*");
        final String FILE_EXTENSION = pLangSyntax.getFileExtension();
        String outputDir = "";
        String viewOutputDir = "";
        if (isCliGenerationPresent) {
            outputDir = String.format("%s/src/main/java/%s/%s/", projectName, groupId.replace(".", "/"), projectName);
            viewOutputDir = String.format("%s/src/main/webapp/WEB-INF/jsp/", projectName);
        } else {
            outputDir = String.format("%s/%s/", projectName, groupId.replace(".", "/"));
        }


        tables = new ArrayList<>();
        DatabaseMetaData databaseMetaData = c.getMetaData();

        ResultSet tablesResultSet = databaseMetaData.getTables(
                null,
                null,
                null,
                new String[]{"TABLE"});


        try {

            while (tablesResultSet.next()) {
                TableSchema tableSchema = new TableSchema(tablesResultSet, pLangSyntax, BASE_PACKAGE + ".entity", null, c.getCatalog());


                if (generateAllFiles || tableToGenerate.contains(tableSchema.getTableName())) {
                    // //System.out.println(tableSchema.getTableName());

                    ResultSet otherColResultSet = databaseMetaData.getColumns(
                            null,
                            null,
                            tableSchema.getTableName(),
                            null);

                    tableSchema.intOtherColumn(otherColResultSet);

                    // ResultSet exportedKeysRs =
                    // databaseMetaData.getExportedKeys(null,null,tableSchema.getTableName());

                    tableSchema.initForeignKey(databaseMetaData);

                    ResultSet primaryKeysResultSet = databaseMetaData.getPrimaryKeys(
                            null,
                            null,
                            tableSchema.getTableName());

                    tableSchema.initOptionalPrimaryKey(primaryKeysResultSet);

                    ArrayList<CodeGenerator> toGenerate = CodeGenerator.generate(tableSchema, frameworkInUse, BASE_PACKAGE,
                            fileToGenerate);

                    if (!toGenerate.isEmpty()) {
                        for (CodeGenerator codeGenerator : toGenerate) {
                            Helper.generateFile(codeGenerator.getFileToGenerateName(), pLangSyntax.getFileExtension(),
                                    codeGenerator.generate(), outputDir + codeGenerator.getType());
                        }
                    }

                    Helper.generateFile(String.format("%s", "index"), "jsp",
                            tableSchema.generateIndex(), viewOutputDir + "/" + Helper.toCamelCase(tableSchema.getTableName()));

                    Helper.generateFile(String.format("%s", "form"), "jsp",
                            tableSchema.generateForm(), viewOutputDir + "/" + Helper.toCamelCase(tableSchema.getTableName()));

                    Helper.generateFile(String.format("%s", "edit"), "jsp",
                            tableSchema.generateEdit(), viewOutputDir + "/" + Helper.toCamelCase(tableSchema.getTableName()));

                    // generator = new RepositoryGenerator(tableSchema, frameworkInUse,
                    // BASE_PACKAGE);
                    // System.out.println(tableSchema.generateRoute());
                    tables.add(tableSchema);

                }
                // break;
            }

            //Helper.generateFile("route", "js", generateRoute(), outputDir);

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                 | IllegalArgumentException | InvocationTargetException | SQLException e) {

            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateRoute() {
        StringBuilder sb = new StringBuilder();
        sb.append("const routes = [").append("\n");
        for (TableSchema schema : tables) {
            sb.append(schema.generateRoute());
        }
        sb.append("]");
        return sb.toString();
    }

}
