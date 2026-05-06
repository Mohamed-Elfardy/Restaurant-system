package main;

import config.AppConfig;
import entity.Admin;
import helper.IdGenerator;
import service.core.AuthService;

public class Main {
    public static void main(String[] args) {
        
        // Initialize Services
        AuthService authService = new AuthService();
        
        // Create Admin
        Admin admin = new Admin("Mohamed Yasser", "01000000001", "admin@restaurant.com", "admin", "admin123");
        authService.registerUser(admin);
        
        System.out.println("System started successfully.");
        System.out.println("TAX RATE: " + AppConfig.TAX_RATE);
    }
}