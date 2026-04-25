package helper;

import entity.Admin;
import entity.Employee;
import entity.Customer;
import entity.Meal;
import constants.MealCategory;
import service.core.AuthService;
import service.core.EmployeeService;
import service.core.CustomerService;
import service.core.MealService;

public class DataSeeder {
    private AuthService authService;
    private EmployeeService employeeService;
    private CustomerService customerService;
    private MealService mealService;

    public DataSeeder(AuthService authService, EmployeeService employeeService,
                      CustomerService customerService, MealService mealService) {
        this.authService = authService;
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.mealService = mealService;
    }

    public void seedAll() {
        seedAdmin();
        seedEmployees();
        seedCustomers();
        seedMeals();
        System.out.println("Sample data loaded successfully.");
    }

    private void seedAdmin() {
        Admin admin = new Admin("Mohamed Yasser", "01000000001", "admin@restaurant.com", "admin", "admin123");
        authService.registerUser(admin);
    }

    private void seedEmployees() {
        Employee emp1 = new Employee("Ahmed Ali", "01000000002", "ahmed@restaurant.com", "ahmed", "ahmed123", "Waiter", 4000);
        Employee emp2 = new Employee("Sara Mohamed", "01000000003", "sara@restaurant.com", "sara", "sara123", "Cashier", 4500);
        authService.registerUser(emp1);
        authService.registerUser(emp2);
        employeeService.addEmployee(emp1);
        employeeService.addEmployee(emp2);
    }

    private void seedCustomers() {
        Customer c1 = new Customer("Omar Hassan", "01000000004", "omar@gmail.com", "Cairo");
        Customer c2 = new Customer("Nour Khaled", "01000000005", "nour@gmail.com", "Giza");
        customerService.addCustomer(c1);
        customerService.addCustomer(c2);
    }

    private void seedMeals() {
        Meal m1 = new Meal("Grilled Chicken", "Juicy grilled chicken with vegetables", MealCategory.MAIN_COURSE, 120.0);
        Meal m2 = new Meal("Caesar Salad", "Fresh salad with caesar dressing", MealCategory.APPETIZER, 65.0);
        Meal m3 = new Meal("Orange Juice", "Freshly squeezed orange juice", MealCategory.DRINK, 35.0);
        Meal m4 = new Meal("Chocolate Cake", "Rich chocolate cake slice", MealCategory.DESSERT, 55.0);
        mealService.addMeal(m1);
        mealService.addMeal(m2);
        mealService.addMeal(m3);
        mealService.addMeal(m4);
    }
}