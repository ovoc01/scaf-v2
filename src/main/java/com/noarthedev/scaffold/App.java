package com.noarthedev.scaffold;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.concurrent.Future;

import org.eclipse.jetty.server.Authentication.User;

import com.google.gson.Gson;
import com.noarthedev.scaffold.props.ScaffoldProps;
import com.noarthedev.scaffold.run.GenerationSession;
import static spark.Spark.*;

public class App {

  public static void main(String[] args)
      throws SQLException, IOException, InterruptedException, NoSuchMethodException, SecurityException,
      InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    String fileToGenerate = "entity,repository";
    final String JDBC_URL = "jdbc:postgresql://localhost:5432/panneau";
    final String USER = "postgres";
    final String PASSWORD = "pixel";
    // new GenerationSession().run("maven", "java", "spring",
    // "com.noarthedev.panneausolaire",
    // "panneausolaire",fileToGenerate,JDBC_URL,USER,PASSWORD);

    // Language , Framework , DatabaseConnection , FileToGenerate,

    //port(1234);
    before((req, res) -> {
      res.header("Access-Control-Allow-Origin", "*"); // Allow requests from any
      res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Allow specific methods
      res.header("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Allow specific headers
    });

    get("/hello", (req, res) -> "Hello, world");

    post("/skfflod", (request, response) -> {
    response.type("application/json");
    ScaffoldProps sProps = new Gson().fromJson(request.body(), ScaffoldProps.class);
    new GenerationSession().run(sProps);
    File file = new File("solaire.zip");

    if (file.exists()) {
        response.type("application/octet-stream");
        response.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        response.status(201);

        // Write the file content to the response output stream
        try (OutputStream outputStream = response.raw().getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            response.status(500);
            return "Internal Server Error";
        }

        return response.raw();
    } else {
        response.status(404);
        return "File Not Found";
    }
});


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
