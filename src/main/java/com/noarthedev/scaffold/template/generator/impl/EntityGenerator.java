package com.noarthedev.scaffold.template.generator.impl;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.mapping.ForeignKey;
import com.noarthedev.scaffold.mapping.TableSchema;
import com.noarthedev.scaffold.template.Framework;
import com.noarthedev.scaffold.template.generator.CodeGenerator;

public class EntityGenerator extends CodeGenerator {

    public EntityGenerator(TableSchema schema, Framework inUse, String pack) {
        super(schema, inUse, pack, "entity");
    }

    @Override
    protected String importsToDo() {
        String importNeed = schema.columnImports();
        if (importNeed != null && !importNeed.isEmpty()) {
            return inUse.getEntity().importsToDo(importNeed);
        }


        return inUse.getEntity().importsToDo();
    }


    protected String foreignKeyImport() {
        StringBuilder sb = new StringBuilder();
        if (!schema.getForeignKeys().isEmpty()) {

            for (ForeignKey fk : schema.getForeignKeys()) {
                sb.append("\n").append(schema.getPSyntax().getImportName()).append(" ").append(BASE_PACKAGE).append(".entity.").append(fk.getType()).append(";");
            }
        }
        return sb.toString();
    }

    @Override
    protected String getTemplates() {
        return inUse.getEntity().getTemplate();
    }

    @Override
    protected String getPackageName() {
        return String.format("%s %s.entity;", inUse.getPSyntax().getPackageName(), BASE_PACKAGE);
    }

    @Override
    protected String getAnnotations() {
        return inUse.getEntity().entityAnnotations();
    }

    @Override
    protected String getInheritance() {
        return inUse.getEntity().getInheritance();
    }

    @Override
    protected String getInjectionAnnotation() {
        // Return an empty string because for now there is no Entity in our framework
        // who need an field injection
        // FIXME: Later
        return "";
    }

    @Override
    protected String implementationOfExtraReplacement(String temp) {
        if (schema.isPrimaryKeyPresent()) {
            String [] marks = null;
            String idMarks = "";
            if (!inUse.getEntity().getIdMarks().equals("none"))
            marks = inUse.getEntity().getIdMarks().split(",");
            for(String m : marks){
                idMarks += inUse.getPSyntax().getAnnotation().replace(":mark", m)+"\n"+Helper.identation();
                
            }
            
            temp = temp.replace("@?[id-marks]", idMarks);
            temp = temp.replace("?[Id]", schema.getPrimaryKey().get().toString());
        }

        temp = temp.replace("[columns]", schema.allColumns(inUse));
        temp = temp.replace("[getters&&setters]", schema.allGetterAndSetters());
        temp = temp.replace("[noArgsConstructor]", inUse.getEntity().getMethods());

        return temp;
    }

    @Override
    public String getFileToGenerateName() {
        return String.format("%s", Helper.toPascalCase(schema.getTableName()));
    }

}
