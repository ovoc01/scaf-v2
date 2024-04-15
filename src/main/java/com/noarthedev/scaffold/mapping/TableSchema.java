package com.noarthedev.scaffold.mapping;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.mapping.keys.ExportedKey;
import com.noarthedev.scaffold.mapping.keys.ImportedKey;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class TableSchema {

    String tableName;
    final String database;
    List<Column> columns;
    List<ForeignKey> foreignKeys;
    List<ExportedKey> exportedKeys;
    List<ImportedKey> importedKeys;
    Optional<PrimaryKey> primaryKey;

    ProgrammingLangSyntax pSyntax;
    final String ENTITY_PACKAGE;
    final String REPO_PACKAGE;

    public TableSchema(ResultSet rSet, ProgrammingLangSyntax programmingLangSyntax, String entity_pack,
            String repo_pack,
            String database)
            throws SQLException {
        setTableName(rSet.getString("TABLE_NAME"));
        // //System.out.println(getTableName());
        setPSyntax(programmingLangSyntax);
        ENTITY_PACKAGE = entity_pack;
        REPO_PACKAGE = repo_pack;
        this.database = database;
        foreignKeys = new ArrayList<>();

        // //System.out.println(programmingLangSyntax.getTemplate());
    }

    public void initOptionalPrimaryKey(ResultSet rSet) throws SQLException {
        while (rSet.next()) {
            PrimaryKey pk = new PrimaryKey();
            pk.init(rSet, pSyntax.getDbType());
            primaryKey = Optional.of(pk);
            // //System.out.println();
            // //System.out.println("riatako ny parole = "+primaryKey.get());
            // //System.out.println(columns);
            for (Column col : columns) {
                if (col.getName().equals(primaryKey.get().getName())) {
                    primaryKey.get().setType(col.getType());
                    primaryKey.get().setImportToDo(col.getImportToDo());
                }
            }
        }
    }

    public void initForeignKey(DatabaseMetaData dbMeta) throws SQLException {
        exportedKeys = new ArrayList<>();
        // TODO: Initialiser les foreigns key (Exported & Imported key)
        ResultSet tablesResultSet = dbMeta.getExportedKeys(null, null, getTableName());
        while (tablesResultSet.next()) {
            ExportedKey exKey = new ExportedKey();
            exKey.init(tablesResultSet);
            exportedKeys.add(exKey);
        }

        importedKeys = new ArrayList<>();

        ResultSet importedKeyResultSet = dbMeta.getImportedKeys(null, null, getTableName());

        while (importedKeyResultSet.next()) {
            ImportedKey importedKey = new ImportedKey();
            importedKey.init(importedKeyResultSet);
            importedKeys.add(importedKey);
        }

        //// System.out.println("fdjfkdjfdkfjdkfjdkfjdkfjdkfjdk");
        System.out.println(importedKeys);

    }

    public void intOtherColumn(ResultSet rSet) throws SQLException {
        columns = new ArrayList<>();
        while (rSet.next()) {
            Column column = new Column();
            column.init(rSet, getPSyntax().getDbType());
            columns.add(column);
        }
    }

    public boolean isPrimaryKeyPresent() {
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
                if (!inUse.getEntity().getColumnMark().equals("none")) {
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

        if (!inUse.getEntity().getExportedKeysMark().equals("none")){
            for (ExportedKey key : exportedKeys) {
                String realAn, listKind = "";
                String [] annotation = null;
                annotation = inUse.getEntity().getExportedKeysMark().split(";");
    
                for(String d : annotation){
                    sb.append("\n");
                    realAn = marks.replace(":mark", d.replace("##table_exporter",Helper.toCamelCase(getTableName())));
                    sb.append(Helper.identation());
                    sb.append(realAn);
                    
                }
                //sb.append("\n");
                listKind = inUse.getEntity().getExportedKeysList();
                sb.append(Helper.identation());
                //sb.append(annotation);
                sb.append("\n");
                sb.append(Helper.identation());
                sb.append(listKind.replace("##referenceTable", key.getType()))
                        .append(Helper.toCamelCase(key.getReferenceTable())).append(";");
                
                        sb.append("\n");
            }
        }


        if (!inUse.getEntity().getImportedKeysMark().equals("none")){
            for (ImportedKey key : importedKeys) {
                String annotation[] = null;
                String realAn = "";
                annotation = inUse.getEntity().getImportedKeysMark().split(";");
                if(annotation.length>1){
                    for (String d : annotation) {
                        sb.append("\n");
                        realAn = marks.replace(":mark", d.replace("##fkcolumn_name", key.getFKCOLUMN_NAME()));
                        sb.append(Helper.identation());
                        sb.append(realAn);
                    }
                }
                sb.append("\n");
                sb.append(Helper.identation());
                sb.append(key.getType() + " ").append(Helper.toCamelCase(key.getReferenceTable()) + ";");
                sb.append("\n");
    
            }
        }

        return sb.toString();
    }

    /*
     * fonction pour recuperer les colonnes qui ne sont pas des clé etrangères
     * comparer le nom
     */
    public List<Column> realCol() {
        List<Column> filteredColumns = new ArrayList<>(columns);

        if (importedKeys != null && !importedKeys.isEmpty()) {
            for (ImportedKey key : importedKeys) {
                for (Column column : filteredColumns) {
                    if (key.getFKCOLUMN_NAME().equals(column.getName())) {
                        filteredColumns.remove(column);
                        break;
                    }
                }
            }
        }

        if (primaryKey != null) {
            primaryKey.ifPresent(pk -> filteredColumns.remove(pk));
        }

        return filteredColumns;
    }

    public String allGetterAndSetters() {
        StringBuilder sb = new StringBuilder();
        List<Column> real = realCol();
        for (Column col : real) {
            sb.append(col.toGetterAndSetter(pSyntax));
        }

        for (ImportedKey key : importedKeys) {
            sb.append(key.toGetterAndSetter(pSyntax));
        }
        PrimaryKey pk = primaryKey.get();
        if(pk!=null){
            sb.append(pk.toGetterAndSetter(pSyntax));
        }

        
        return sb.toString();
    }

    public String columnImports() {
        StringBuilder sb = new StringBuilder();
        Set<String> importedColumns = new HashSet<>(); // Set to track duplicate strings

        List<Column> real = realCol();

        for (Column col : real) {
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

    public String tableNameToCamelCase() {
        return Helper.toCamelCase(tableName);
    }

    public String first3Letters() {
        String let = this.tableNameToCamelCase();
        int val = (let.length() / 2);
        return let.substring(0, Math.min(val, 3)).toUpperCase();
    }
}
