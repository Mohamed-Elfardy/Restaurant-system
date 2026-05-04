package helper;

import service.core.*;
import service.core.*;
import helper.InputValidator;
import helper.DisplayHelper;
import service.support.*;
import entity.*;
import java.util.*;
import constants.MealCategory;


public class MenuUtil {

    private CustomerService customerservice;

    private final AuthService authService;
    private final BillingService billingService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    // Errors
    private final MealService mealService;
    private final OrderService orderService;

    // From main.java
    public MenuUtil (AuthService authService, BillingService billingService, CustomerService customerService, 
     EmployeeService employeeService, MealService mealService,  OrderService orderService)
    {
        this.authService = new AuthService();
        this.billingService = new BillingService();
        this.customerService = new CustomerService();
        this.employeeService = new EmployeeService();

        // Errors
        this.mealService = new MealService();
        this.orderService = new OrderService();
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
                   break;
                case 2:
                    manageMeals();
                    break;
                case 3:
                    Show_Reports_Menu();
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
            System.out.println("1. Manage/Register Customers");
            System.out.println("2. Create New Order");
            System.out.println("3. Track Order Status");
            System.out.println("4. Out");

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
                    System.out.println("Invalid Choise");
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
        Order newOrder = orderService.createOrder(customer, currentEmp);

        boolean addMeals = true;
        while (addMeals) {
            mealService.listAllMeals();
            String choise = InputValidator.Read_String("Enter Meal ID to add (\"0\" to finish)");
            if (choise == "0") {
                addMeals = false;
            } else {
                Meal meal = mealService.getMealById(choise);
                if (meal != null) {
                    int quantity = InputValidator.Read_Int("Quantity: ");
                    orderService.addMealToOrder(newOrder.getOrderId(), meal, quantity);
                }
            }
        }
        String orderID = newOrder.getOrderId();
        System.out.println("Order Total = " + orderService.calculateOrderTotal(orderID));
    }
    

    public void manage_Order_Status() {
        System.out.println("Order Status");
        orderService.listAllOrders();

        String orderID = InputValidator.Read_String("Enter Order ID: ");
        Order order = orderService.getOrderById(orderID);

        if (order != null) {
            System.out.println("Current Order: " + order.getStatus());

            int choise = InputValidator.Read_Int("1. Confirm Order\n2. Complete Order\n3. Cancle Order\n4. Back\n");

            switch (choise) {
                case 1:
                    orderService.confirmOrder(orderID);
                    break;
                case 2:
                    orderService.completeOrder(orderID);
                    break;
                case 3:
                    orderService.cancelOrder(orderID);
                    break;
                case 4:
                    return;
                default:
                    break;
            }
        }
        else {
            System.out.println("Order not found");
        }
    }
    

    public void Show_Reports_Menu() {
        System.out.println("Reports");
        System.out.println("1. Sales Report");
        System.out.println("2. Employee Performane");
        System.out.println("3. Customer Activity");
        System.out.println("4. Offers & Notifications");
        System.out.println("5. Back");

        int choise = InputValidator.Read_Int("Enter Your Choise: ");

        switch (choise) {
            case 1:
                ReportService.generateSalesReport();
                break;
            case 2:
                ReportService.generateEmployeeReport(employeeService.listAllEmployees());   //ArrayList Employees
                break;
            case 3:
                ReportService.generateCustomerReport(customerService.listAllCustomers());   // ArrayList Customers
                break;
            case 4:
                handleNotifications();
                break;
            case 5:
                return;
        }

    }


    public void handleNotifications() {
        System.out.println("System Notifications");
        ArrayList<Notification> notifications = NotificationService.getAllNotifications();

        if (notifications.isEmpty()) {
            System.out.println("No New Notifications");
        } else {
            for (Notification note : notifications) {
                System.out.println("-" + note.getMessage());
            }
        }
    }
    

    public void manageEmployees() {
        System.out.println("Employees Management");
        System.out.println(
                "1. Add New Employee\n2. Update Employee\n3. Search Employee\n4. Delete Employee\n5. View All Employees\n6. Back");

        int choise = InputValidator.Read_Int("Enter Your Choise: ");

        switch (choise) {
            case 1:
                employeeService.addEmployee();
                break;
            case 2:
                employeeService.updateEmployee();
                break;
            case 3:
                int empID = InputValidator.Read_Int("Enter Employee ID: ");
                employeeService.searchEmployeeById(empID);
                break;
            case 4:
                employeeService.deleteEmployee();
                break;
            case 5:
                employeeService.listAllEmployees();
                break;
            case 6:
                return;
        }

    }
    

    public void manageMeals() {
        System.out.println("Meals Management");
        System.out.println("1. Add New Meal\n2. Update Meal\n3. Delete Meal\n4. View All Meals\n5. Back");
        int choise = InputValidator.Read_Int("Enter Your Choise: ");
        switch (choise) {
            case 1:
                String mealName = InputValidator.Read_String("Enter Meal Name: ");
                String mealDes = InputValidator.Read_String("Enter Meal Description: ");
                double mealPrice = InputValidator.Read_Double("Enter Meal Price: ");
                // Error
                // System.out.println("Select Meal Category: ");
                // MealCategory[] categories = MealCategory.values();
                // for(int i = 0 ; categories.length ; i++){

                // }

            case 3:
                String mealID = InputValidator.Read_String("Enter Meal ID: ");
                mealService.removeMeal(mealID);
                break;
            case 4:
                mealService.listAllMeals();
                break;
            case 5:
                return;

        }
    }
    

    public void manageCustomer(){
        System.out.println("Customer Management");
        System.out.println(
                "1. Add Customer\n2. Search for Customer\n3. Remove Customer\n4. Update Customer\n5. Display Customer History\n6. Back");

        int choise = InputValidator.Read_Int("Enter Your Choise: ");
        switch (choise) {
            case 1:
                customerService.addCustomer();
                break;
            case 2:
                int custID = InputValidator.Read_Int("Enter Customer ID: ");
                customerService.searchCustomerById(custID);
                break;
            case 3:
                customerService.removeCustomer();
                break;
            case 4:
                customerService.updateCustomer();
                break;
            case 5:
                customerService.displayCustomerHistory();
                break;
            case 6:
                return;
        }
    }
}
