package com.noarthedev.scaffold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.concurrent.Future;

import com.noarthedev.scaffold.run.GenerationSession;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) throws SQLException, IOException, InterruptedException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    String fileToGenerate = "entity,repository";
    new GenerationSession().run("maven","java","spring","com.noarthedev.panneausolaire","panneausolaire",fileToGenerate);


    // Language , Framework , DatabaseConnection , FileToGenerate,


    /*
     * before((req, res) -> {
     * res.header("Access-Control-Allow-Origin", "*"); // Allow requests from any
     * origin
     * res.header("Access-Control-Allow-Methods",
     * "GET, POST, PUT, DELETE, OPTIONS"); // Allow specific methods
     * res.header("Access-Control-Allow-Headers", "Content-Type, Authorization"); //
     * Allow specific headers
     * });
     * 
     * exception(Exception.class, (e,req,res)->{
     * e.printStackTrace();
     * });
     * 
     * get("/hello", (req, res) -> "Hello, world");
     * 
     * get("/hello/:name", (req, res) -> {
     * //req.body();
     * return "Hello, " + req.params(":name");
     * });
     */

  }
}
