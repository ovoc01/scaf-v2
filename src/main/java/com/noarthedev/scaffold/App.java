package com.noarthedev.scaffold;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.noarthedev.scaffold.helper.Helper;
import com.noarthedev.scaffold.props.ScaffoldProps;
import com.noarthedev.scaffold.run.GenerationSession;

import static spark.Spark.*;

public class App {
    public static Logger LOGGER = LoggerFactory.getLogger(App.class); 
    public static void main(String[] args)
            throws SecurityException, IllegalArgumentException {

        String fileToGenerate = "entity,repository";
        final String JDBC_URL = "jdbc:postgresql://localhost:5432/db_tp";
        final String USER = "postgres";
        final String PASSWORD = "pixel";

        /*
         * try {
         * Connection c = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
         * DatabaseMetaData databaseMetaData = c.getMetaData();
         * 
         * ResultSet tablesResultSet =
         * databaseMetaData.getExportedKeys(c.getCatalog(),c.getSchema(),"facture");
         * 
         * while (tablesResultSet.next()){
         * //System.out.println(tablesResultSet);
         * }
         * 
         * } catch (SQLException e) {
         * throw new RuntimeException(e);
         * }
         */

        port(3456);

        enableCORS("*", "*", "*", "*");

        get("/hello", (req, res) -> "Hello, world");

        post("/skfflod", (request, response) -> {
            //System.out.println("atoo");
            response.type("application/json");
            ScaffoldProps sProps = new Gson().fromJson(request.body(), ScaffoldProps.class);

            System.out.println(sProps);

            new GenerationSession().run(sProps);

            File projectDirectory = new File(sProps.getProjectName());

            String zipFileName = sProps.getProjectName() + ".zip";
            Helper.zipDirectory(projectDirectory, zipFileName);
            File zipFile = new File(zipFileName);

            if (zipFile.exists()) {
                response.type("application/zip");
                response.status(200);

                // Write the file content to the response output stream
                response.header("Content-Disposition", "attachment; filename=\"" + zipFileName + "\"");

                try (OutputStream outputStream = response.raw().getOutputStream();
                        FileInputStream fileInputStream = new FileInputStream(zipFile)) {
                    byte[] buffer = new byte[4096];
                    int length;
                    while ((length = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, length);
                    }
                    projectDirectory.delete();
                    outputStream.flush();
                } catch (IOException e) {
                    response.status(500);
                    return "Internal Server Error";
                }finally {
                    Helper.deleteDirectory(zipFile);
                    Helper.deleteDirectory(projectDirectory);
                }

                return null;
            } else {
                response.status(404);
                return "File Not Found";
            }
        });

    } 

    private static void enableCORS(final String origin, final String methods, final String headers,
            final String exposedHeaders) {
        options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Allow-Methods", methods);
            response.header("Access-Control-Allow-Headers", headers);
            response.header("Access-Control-Expose-Headers", exposedHeaders);
            response.header("Access-Control-Allow-Credentials", "true");
        });
    }
}
