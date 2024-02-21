package com.noarthedev.scaffold.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.text.CaseUtils;
import org.apache.commons.text.WordUtils;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class Helper {

  public static String readInputStream(InputStream is) {
    String result = new BufferedReader(new InputStreamReader(is))
        .lines()
        .collect(Collectors.joining("\n"));
    return result;
  }

  public static Map<String, Object> stringToMap(String string) {
    Map<String, Object> map = new HashMap<>();

    // Split the input string by newline character
    String[] lines = string.split("\\r?\\n");

    // Iterate over each line
    for (String line : lines) {
      // Split each line by '=' character
      String[] keyValue = line.split("=");
      if (keyValue.length == 2) {
        // Add key-value pair to the map
        map.put(keyValue[0].trim(), keyValue[1].trim());
      }
    }

    return map;
  }

  public static String retrieveFromString(
      String template,
      String partBegin,
      String partEnd,
      String reference) {
    String beginString = partBegin + reference;

    int beginIndex = template.indexOf(beginString);
    if (beginIndex == -1) {
      // If partBegin is not found in the template, return null or throw an exception
      // as desired
      return null;
    }

    String endString = partEnd + reference;
    int endIndex = template.indexOf(endString, beginIndex + partBegin.length());
    if (endIndex == -1) {
      // If partEnd is not found after partBegin in the template, return null or throw
      // an exception as desired
      return null;
    }

    return template.substring(beginIndex + beginString.length(), endIndex);
  }

  public static String toCamelCase(String input) {
    return CaseUtils.toCamelCase(input, false, '_');
  }

  public static String toPascalCase(String input) {
    return WordUtils.capitalizeFully(input, '_').replaceAll("_", "");
  }

  public static String reformatCode(String code) throws FormatterException {
    return new Formatter().formatSource(code);
  }

  public static String identation() {
    return " ".repeat(4);
  }

  public static void generateFile(String filename, String ext, String fileContent, String outputDir) throws IOException {
    final String fileOutputName = String.format("%s/%s.%s",outputDir, filename, ext);
    File file = new File(outputDir);


    if(!file.exists()){
      
      System.out.println(file.mkdirs());
    }
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutputName))) {
      writer.write(fileContent);
      writer.close();
    } catch (IOException e) {
      throw e;
    }

  }

}
