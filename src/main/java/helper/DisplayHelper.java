package helper;

import entity.Meal;  
import java.util.ArrayList; 

public class DisplayHelper {

    public static void displayMeals(ArrayList<Meal> meals) {  // For Meals
        if (meals == null || meals.isEmpty()) {
            System.out.println("\n[!] No Meals For Now.");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        // Format
        System.out.printf("%-10s | %-20s | %-12s | %-8s | %-10s\n", 
                          "ID", "Meal Name", "Category", "Price", "Status");
        System.out.println("-".repeat(65));

        for (Meal meal : meals) {
            System.out.printf("%-10s | %-20s | %-12s | %-8.2f | %-10s\n", 
                meal.getMealId(), 
                meal.getName(), 
                meal.getCategory(), 
                meal.getPrice(), 
                meal.isAvailable() ? "Available" : "Sold Out"
            );
        }
        System.out.println("=".repeat(65) + "\n");
    }
}