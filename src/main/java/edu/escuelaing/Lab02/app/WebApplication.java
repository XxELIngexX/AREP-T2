package edu.escuelaing.Lab02.app;

import static edu.escuelaing.Lab02.http.HttpServer.get;
import static edu.escuelaing.Lab02.http.HttpServer.staticFiles;

import java.util.ArrayList;

import edu.escuelaing.Lab02.model.*;
import edu.escuelaing.Lab02.service.*;
import edu.escuelaing.Lab02.util.JsonUtil;

import static edu.escuelaing.Lab02.http.HttpServer.start;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles("/");
        
        DishService dishService = new DishService();
        UserService userService = new UserService();

        get("/menu", (req, resp) -> {
            resp.setContentType("application/json;charset=UTF-8");
            return JsonUtil.toJson(dishService.getDishes());
        });

        get("/adduser", (req, resp) -> { 
            
            String userName = req.getValue("name");
            userService.addUser(new User(userName));
            System.out.println("@@@@@@@@@@");
            return ("usuario "+ userName + " Creado");
        });
        get("/getUser", (req,resp) ->{
            User currentUser = userService.getUserByName(req.getValue("name"));
            return JsonUtil.toJson(currentUser);
        });

        
        get("/hello", (req, resp) -> "Hello " + req.getValue("name"));

        start(args);
    }
}
