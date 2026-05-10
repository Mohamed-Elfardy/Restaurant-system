package entity;

import constants.MealCategory;  // Marwan 

public class Meal {
    private String mealId;
    private String name;
    private String description;
    private MealCategory category;
    private double price;
    private boolean available;

    public Meal(String mealId, String name, String description, MealCategory category, double price) {
        this.mealId      = mealId;
        this.name        = name;
        this.description = description;
        this.category    = category;
        this.price       = price;
        this.available   = true;
    }

    // Getters
    public String getMealId()         { return mealId; }
    public String getName()           { return name; }
    public String getDescription()    { return description; }
    public MealCategory getCategory() { return category; }
    public double getPrice()          { return price; }
    public boolean isAvailable()      { return available; }

    // Setters
    public void setName(String name)               { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCategory(MealCategory category) { this.category = category; }
    public void setPrice(double price)             { this.price = price; }
    public void setAvailable(boolean available)    { this.available = available; }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | $%.2f | %s",
                mealId, name, category, price,
                available ? "Available" : "Unavailable");
    }
}