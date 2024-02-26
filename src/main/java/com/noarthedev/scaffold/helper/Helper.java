package com.noarthedev.scaffold.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.text.CaseUtils;
import org.apache.commons.text.WordUtils;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.noarthedev.scaffold.template.Framework;

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

  public static boolean initSpringProject(String build,String groupId,String projectName,Framework inUse){
    return true;
  }

  public static void zipDirectory(File dir, String zipDirName) throws IOException{
      if(!dir.isDirectory()) throw new IllegalStateException("File must be a directory");
      List<String> filesListInDir = new ArrayList<>();
      populateFilesList(dir,filesListInDir);
      
      try { 
            //now zip files one by one
            //create ZipOutputStream to write to the zip file
            FileOutputStream fos = new FileOutputStream(zipDirName);
            ZipOutputStream zos = new ZipOutputStream(fos);
            for(String filePath : filesListInDir){
                System.out.println("Zipping "+filePath);
                //for ZipEntry we need to keep only relative file path, so we used substring on absolute path
                ZipEntry ze = new ZipEntry(filePath.substring(dir.getAbsolutePath().length()+1, filePath.length()));
                zos.putNextEntry(ze);
                //read the file and write to ZipOutputStream
                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }
                zos.closeEntry();
                fis.close();
            }
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
  }

  /**
     * This method populates all the files in a directory to a List
     * @param dir
     * @throws IOException
     */
    private static void populateFilesList(File dir,List<String> filesListInDir) throws IOException {
      
      File[] files = dir.listFiles();
      //System.out.println(files);
      
      for(File file : files){
          if(file.isFile()) filesListInDir.add(file.getAbsolutePath());
          else populateFilesList(file,filesListInDir);
      }
      
  }

}
