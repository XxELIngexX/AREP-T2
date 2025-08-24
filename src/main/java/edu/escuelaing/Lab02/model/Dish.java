package edu.escuelaing.Lab02.model;

public class Dish {

    private int id;
    private String name;
    private double price;
    private String category; // nueva categoría

    public Dish(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        setCategory(category); // valida la categoría al crear el objeto
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        if(category.equalsIgnoreCase("burger") || 
           category.equalsIgnoreCase("pizza") || 
           category.equalsIgnoreCase("hotdog")) {
            this.category = category.toLowerCase();
        } else {
            throw new IllegalArgumentException("Categoría inválida: debe ser 'burger', 'pizza' o 'hotdog'");
        }
    }

    public String toText() {
        return "{"
                + "\"name\":\"" + name + "\","
                + "\"price\":" + price + ","
                + "\"category\":\"" + category + "\""
                + "}";
    }
}
