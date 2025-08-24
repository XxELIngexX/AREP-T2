package edu.escuelaing.Lab02.app;

import static edu.escuelaing.Lab02.http.HttpServer.get;
import static edu.escuelaing.Lab02.http.HttpServer.staticFiles;

import edu.escuelaing.Lab02.service.DishService;
import edu.escuelaing.Lab02.service.UserService;
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


        
        get("/hello", (req, resp) -> "Hello " + req.getValue("name"));
        get("/pi", (req, resp) -> {
            return String.valueOf(Math.PI);
        });
        start(args);
    }
}
