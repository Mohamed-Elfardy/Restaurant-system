package main;

import config.*;
import entity.*;
import helper.*;
import core.*;
import database.DBConnection;

public class Main {

    public static void main(String[] args) {
        
        // 1. محاولة الاتصال بقاعدة البيانات والتحقق من وجود الملف
        if (DBConnection.connect() == null) {
            System.out.println("Error: the restaurant.db file could not be opened or found!");
            return; // إنهاء البرنامج إذا فشل الاتصال
        }

        // 2. تعريف الخدمات (Services)
        AuthService authService = new AuthService();
        BillingService billingService = new BillingService();
        CustomerService customerService = new CustomerService();
        EmployeeService employeeService = new EmployeeService();
        MealService mealService = new MealService();
        OrderService orderService = new OrderService();

        // 3. تجهيز البيانات الأولية (Data Seeder)
        DataSeeder seeder = new DataSeeder(
                authService,
                employeeService,
                customerService,
                mealService
        );

        seeder.seedAll(); // ملء البيانات في القاعدة أو القوائم

        // 4. تشغيل واجهة القائمة الرئيسية (Menu)
        MenuUtil menu = new MenuUtil(
                authService,
                billingService,
                customerService,
                employeeService,
                mealService,
                orderService
        );

        menu.Show_Main_Menu();
    }
}