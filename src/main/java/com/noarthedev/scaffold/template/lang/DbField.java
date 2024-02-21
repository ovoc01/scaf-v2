package com.noarthedev.scaffold.template.lang;

import java.util.Optional;

import lombok.Data;

@Data
public class DbField {
    String name;
    Optional<String> imports;

    public static DbField initDbField(String unsplittedDbField) {
        
        DbField dbField = new DbField();
        String[] splitText = unsplittedDbField.split(":");
        if (splitText != null && splitText.length > 1) {
            dbField.setName(splitText[0]);
            dbField.setImports(Optional.of(splitText[1]));
            return dbField;
        }

        dbField.setName(unsplittedDbField);
        return dbField;
    }
}
