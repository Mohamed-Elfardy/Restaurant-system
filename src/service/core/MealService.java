package service;

import constants.MealCategory;
import entity.Meal;
import helper.IdGenerator;   // Marwan

import java.util.ArrayList;

public class MealService {
    private ArrayList<Meal> meals;

    public MealService() {
        this.meals = new ArrayList<>();
    }

    // ── ADD 
    public Meal addMeal(String name, String description, MealCategory category, double price) {
        String id = IdGenerator.generateMealId();
        Meal meal = new Meal(id, name, description, category, price);
        meals.add(meal);
        System.out.println("Meal added: " + meal);
        return meal;
    }

    // ── REMOVE 
    public boolean removeMeal(String mealId) {
        Meal meal = getMealById(mealId);
        if (meal == null) return false;
        meals.remove(meal);
        System.out.println("Meal removed: " + mealId);
        return true;
    }

    // ── UPDATE 
    public boolean updateMeal(String mealId, String newName, String newDescription,
                              MealCategory newCategory, double newPrice, boolean available) {
        Meal meal = getMealById(mealId);
        if (meal == null) return false;

        if (newName        != null) meal.setName(newName);
        if (newDescription != null) meal.setDescription(newDescription);
        if (newCategory    != null) meal.setCategory(newCategory);
        if (newPrice       >  0)    meal.setPrice(newPrice);
        meal.setAvailable(available);

        System.out.println("Meal updated: " + meal);
        return true;
    }

    // ── LIST
    public void listAllMeals() {
        if (meals.isEmpty()) {
            System.out.println("No meals available.");
            return;
        }
        System.out.println("=== Meal List ===");
        for (Meal m : meals) {
            System.out.println(m);
        }
    }

    public ArrayList<Meal> getAvailableMeals() {
        ArrayList<Meal> result = new ArrayList<>();
        for (Meal m : meals) {
            if (m.isAvailable()) result.add(m);
        }
        return result;
    }

    // ── SEARCH 
    public Meal getMealById(String mealId) {
        for (Meal m : meals) {
            if (m.getMealId().equals(mealId)) return m;
        }
        System.out.println("Meal not found: " + mealId);
        return null;
    }

    public ArrayList<Meal> searchByName(String keyword) {
        ArrayList<Meal> result = new ArrayList<>();
        for (Meal m : meals) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(m);
            }
        }
        return result;
    }

    public ArrayList<Meal> searchByCategory(MealCategory category) {
        ArrayList<Meal> result = new ArrayList<>();
        for (Meal m : meals) {
            if (m.getCategory() == category) result.add(m);
        }
        return result;
    }

    // ── GETTER 
    public ArrayList<Meal> getMeals() { return meals; }
}