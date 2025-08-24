package edu.escuelaing.Lab02.service;
import edu.escuelaing.Lab02.model.Dish;

public class DishService {
    private static Dish[] dishes = {
        new Dish(1, "Beef Burger", 12.99,"burger"),
        new Dish(2, "Cheese Burger", 10.99,"burger"),
        new Dish(3, "Chicken Burger", 8.99,"burger"),
        new Dish(4, "Personal Pizza", 15.99,"pizza"),
        new Dish(5, "Medium Pizza", 6.99,"pizza"),
        new Dish(6, "Family Pizza", 6.99,"pizza"),
        new Dish(7, "Simple Hot Dog", 6.99,"hotdog"),
        new Dish(8, "American Hot Dog", 6.99,"hotdog"),
        new Dish(9, "Special Hot Dog", 6.99,"hotdog")
    };
    public Dish[] getDishes() {
        return dishes;
    }
    public Dish getDishById(int id) {
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                return dish;
            }
        }
        return null;
    }
    

}
