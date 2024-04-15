package com.noarthedev.scaffold.template.part;

import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityPart extends BasePart {
    String idMarks;
    String columnMark;
    String exportedKeysMark;
    String exportedKeysList;
    String importedKeysMark;

    public EntityPart() {
        super("entity");
    }

    @Override
    public EntityPart fromFileContent(String fileContent, ProgrammingLangSyntax pSyntax) {
        EntityPart part = new EntityPart();
        part.setPSyntax(pSyntax);

        final String partEnd = "!!";
        final String partBegin = "##";
        //
        BasePart basePart = super.fromFileContent(fileContent, pSyntax);
        Map<String, Object> map = Helper.stringToMap(fileContent);


        part.setMethods(
                Helper.retrieveFromString(
                        fileContent,
                        partBegin,
                        partEnd,
                        "noArgsConstructor")
        );

        ////System.out.println(map.get("entity.id-marks"));
        part.setIdMarks(map.get("entity.id-marks"));
        part.setColumnMark(map.get("entity.column-mark").toString());


        ////System.out.println(map);
        part.setExportedKeysMark(map.get("entity.exported-keys-mark").toString());
        part.setImportedKeysMark(map.get("entity.imported-keys-mark").toString());
        part.setExportedKeysList(map.get("entity.exported-keys-list").toString());
        basePart.completeChild(part);

        return part;
    }

    public void setIdMarks(Object object) {
        ////System.out.println("eto asika " + object);
        if (object == null) this.idMarks = "";

        this.idMarks = object.toString();
    }


    public String importsToDo() {
        StringBuilder sb = new StringBuilder();
        for (String d : imports) {
            sb.append(pSyntax.getImportName() + " " + d + ";").append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    public String importsToDo(String extraImport){
        StringBuilder sb = new StringBuilder();
        String[] imports = extraImport.split(",");

        for (String d : imports) {
            sb.append(pSyntax.getImportName() + " " + d + ";").append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        return importsToDo()+"\n"+sb.toString();
    }

    public String entityAnnotations() {
        StringBuilder sb = new StringBuilder();
        for (String a : annotations) {
            if (!a.equals("none")) {
                String column = pSyntax.getAnnotation().replace(":mark", a);
                sb.append(column).append("\n");
            }
        }

        return sb.toString();
    }

}
