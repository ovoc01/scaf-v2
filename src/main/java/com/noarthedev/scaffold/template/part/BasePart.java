package com.noarthedev.scaffold.template.part;

import java.util.Map;

import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.template.Template;
import com.noarthedev.scaffold.template.lang.ProgrammingLangSyntax;

import lombok.Data;

@Data
public class BasePart {

    protected ProgrammingLangSyntax pSyntax;
    protected final String CLASS_PART;
    protected String template;


    protected String[] imports;

    protected String[] annotations;
    protected String inheritance;
    protected String methods;
    protected String injectionAnnotation;
    protected String declaration;


    public BasePart(String part) {
        CLASS_PART = part.toLowerCase();
    }

    public BasePart fromFileContent(String fileContent, ProgrammingLangSyntax pSyntax) {
        //Map<String,Object> map = null;

        Map<String, Object> map = Helper.stringToMap(fileContent);
        BasePart part = new BasePart(CLASS_PART);

        try {
            part.setPSyntax(pSyntax);
            part.setAnnotations(map.get(CLASS_PART + ".annotation").toString().split(","));
            part.setImports(map.get(CLASS_PART + ".imports").toString().split(","));
            part.setInheritance(map.get(CLASS_PART + ".inheritance").toString());
            part.setDeclaration(map.get(CLASS_PART+".declaration").toString());
        } catch (NullPointerException p) {
            p.printStackTrace();
        }


        ////System.out.println(pSyntax.getLang());
        part.getTemplate(pSyntax.getLang());
        part.prepareTemplate();

        ////System.out.println(part.getTemplate());

        return part;
    }

    public void prepareTemplate() {
        template = template.replace("[open-brackets]", pSyntax.getOpenBrackets());
        template = template.replace("[close-brackets]", pSyntax.getCloseBrackets());
        template = template.replace("[class-declaration]", pSyntax.getClassDeclaration());
        template = template.replace("[interface-declaration]", pSyntax.getInterfaceDeclaration());
    }

    public void getTemplate(String lang) {
        setTemplate(new Template().readTemplateAs(lang, CLASS_PART));
    }

    public String importsToDo() {
        StringBuilder sb = new StringBuilder();
        for (String d : imports) {

            sb.append(pSyntax.getImportName() + " " + d + ";").append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String entityAnnotations() {
        StringBuilder sb = new StringBuilder();
        for (String a : annotations) {
            if ((!a.equals("none")&& !a.isEmpty())) {
                String column = pSyntax.getAnnotation().replace(":mark", a);
                sb.append(column).append("\n");
            }
        }

        return sb.toString();
    }

    public void completeChild(BasePart childPart) {
        childPart.setAnnotations(annotations);
        childPart.setInheritance(getInheritance());
        childPart.setImports(getImports());
        childPart.setTemplate(getTemplate());
    }


    public String getInheritance() {
        return defaultValue(this.inheritance);
    }

    public String getMethods() {
        return defaultValue(this.methods);
    }

    public String getInjectionAnnotation() {
        return defaultValue(this.injectionAnnotation);
    }


    public void setTemplate(Object obj) {
        this.template = stringNullityVerifier(obj);
    }

    public void setInheritance(Object obj) {
        this.inheritance = stringNullityVerifier(obj);
    }

    public void setMethods(Object obj) {
        this.methods = stringNullityVerifier(obj);
    }

    public void setInjectionAnnotation(Object obj) {
        this.injectionAnnotation = stringNullityVerifier(obj);
    }


    private String defaultValue(String string) {
        try {
            String inheritance = string.equals("none")
                    ? ""
                    : string;

            return inheritance;
        } catch (NullPointerException e) {
            return "";
        }
    }


    private String stringNullityVerifier(Object obj) {
        if (obj == null) return "none";
        return obj.toString();
    }


}
