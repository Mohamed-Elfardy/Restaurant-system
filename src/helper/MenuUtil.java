package helper;

import service.core.*;
import helper.InputValidator;
import helper.DisplayHelper;
import service.support.*;
import entity.*;

public class MenuUtil {

    private CustomerService customerservice;

    private final AuthService authService;
    private final BillingService billingService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    // Errors
    // private final MealService mealService;
    // private final OrderService orderService;

    // From main.java
    public MenuUtil (AuthService authService, BillingService billingService, CustomerService customerService, 
     EmployeeService employeeService, MealService mealService,  OrderService orderService)
    {
        this.authService = new AuthService();
        this.billingService = new BillingService();
        this.customerService = new CustomerService();
        this.employeeService = new EmployeeService();

        // Errors
        // this.mealService = new MealServices();
        // this.orderService = new OrderService();
    }
     
    // UI
    public void Show_Main_Menu() {
        while (true) {
            System.out.println("Restaurant Management System ");
            System.out.println("1. Admin Login");
            System.out.println("1. Employee Login");
            System.out.println("1. Exit");
            int choise = InputValidator.Read_Int("Enter Your Choise: ");

            switch (choise) {
                case 1:
                    HandleLogin("Admin");
                    break;
                case 2:
                    HandleLogin("Employee");
                    break;
                case 3:
                    System.out.println("Exiting.");
                    return;
                default:
                    break;
            }
        }
    }

    public void HandleLogin(String role) {

        System.out.println(role + " Login");
        String name = InputValidator.Read_String("Username: ");
        String pass = InputValidator.Read_String("Password: ");

        User auth = authService.login(name, pass);

        if (auth != null) {
            if (auth instanceof Admin) {
                System.out.println("Welcome Admin: " + auth.getName());
                Show_Admin_Menu();
            } else if (auth instanceof Employee) {
                System.out.println("Welcome Employee: " + auth.getName());
                Show_Employee_Menu();
            }
            else {
                System.out.println("Error: Check your credentials.");
            }
        }

    }
    
    
    // Adim Menu
    public void Show_Admin_Menu() {
        boolean check = false;
        while (!check) {
            System.out.println("Admin Menu");
            System.out.println("1. Manage Employees");
            System.out.println("2. Manage Meals");
            System.out.println("3. System Reports");
            System.out.println("4. Out");

            int choise = InputValidator.Read_Int("Enter Your Choise: ");

            switch (choise) {
               case 1:
                   manageEmployees();
                   braek;
                case 2:
                    manageMeaks();
                    break;
                case 3:
                    showReports();
                    break;

                case 4:
                    authService.logout();;
                    check=true;
                    break;
                default:
                    System.out.println("Invalid Choise");
                    break;
            }
        }
    }


    // Employee Menu
    public void Show_Employee_Menu() {
        boolean check = false;
        while (!check) {
            System.out.println("Employee Menu");
            System.out.println("1. Manage/Register Customers")
            System.out.println("2. Create New Order")
            System.out.println("3. Track Order Status")
            System.out.println("4. Out")

            int choise = InputValidator.Read_Int("Enter Choise: ");

            switch (choise) {
                case 1:
                    manageCustomer();
                    break;
                case 2:
                    Handle_Create_Order();
                    break;
                case 3:
                    manage_Order_Status();
                    break;
                case 4:
                    authService.logout();
                    check = true;
                    break;
                default:
                    System.out.println("Invalid Choise")
                    break;
            }
        }
    }


    public void Handle_Create_Order() {
        System.out.println("Create New Order");

        int customerID = InputValidator.Read_Int("Enter Customer ID: ");
        Customer customer = customerService.searchCustomerById(customerID);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }
        Employee currentEmp = (Employee) authService.getCurrentUser();
        // Error
        // Order newOrder = orderService.createOrder(customer, currentEmp);

        boolean addMeals = true;
        while (true) {
            mealService.listAllMeals();
            String choise = InputValidator.Read_String("Enter Meal ID to add (\"0\" to finish)");
            if (choise == "0") {
                addMeals = false;
            }
            else {
                Meal meal = mealService.getMealById(choise);
                if (meal != null) {
                    int quan = InputValidator.Read_Int("Quantity: ");
                    orderService.addMealToOrder(newOrder.getOrderId(), meal, quan);
                }
            }
        }
        System.out.println("Order Total = "+newOrder.calculateOrderTotal());
    }
}
