package edu.escuelaing.Lab02;

import static edu.escuelaing.Lab02.HttpServer.get;
import static edu.escuelaing.Lab02.HttpServer.staticFiles;
import static edu.escuelaing.Lab02.HttpServer.start;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles("/webroot");
        get("/hello", (req, resp) -> "Hello " + req.getValue("name"));
        get("/pi", (req, resp) -> {
            return String.valueOf(Math.PI);
        });
        start(args);
    }
}
