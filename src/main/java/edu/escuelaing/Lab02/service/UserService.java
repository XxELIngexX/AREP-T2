package edu.escuelaing.Lab02.service;

import java.util.ArrayList;

import edu.escuelaing.Lab02.model.Dish;
import edu.escuelaing.Lab02.model.User;

public class UserService {
    private ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }
    public boolean userExists(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public boolean userExistsByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    public void removeUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }
    public User getUserByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<Dish> getUserCar(int id) {
        User user = getUserById(id);
        if (user != null) {
            return user.getCar();
        }
        return null;
    }
    public void addDishToUserCar(int userId, Dish dish) {
        User user = getUserById(userId);
        if (user != null) {
            user.getCar().add(dish);
        }
    }
}
