package edu.escuelaing.Lab02.model;

import java.util.ArrayList;
import edu.escuelaing.Lab02.model.Dish;

public class User {
    private int id;
    private String name;
    private ArrayList<Dish> car;

    public User(String name) {
        this.name = name;
        car = new ArrayList<Dish>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Dish> getCar() {
        return car;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCar(ArrayList<Dish> car) {
        this.car = car;
    }
    public String toText() {
        return "{"
                + "\"name\":\"" + name + "\","
                + "}";
    }

}
