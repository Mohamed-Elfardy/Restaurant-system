package service.core;

import entity.Employee;
import helper.IdGenerator;
import helper.InputValidator;

import java.util.ArrayList;

public class EmployeeService {

    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee() {
        System.out.println("\n--- Add New Employee ---");
        String name     = InputValidator.Read_String("Enter Name     : ");
        String phone    = InputValidator.Read_String("Enter Phone    : ");
        String email    = InputValidator.Read_String("Enter Email    : ");
        String username = InputValidator.Read_String("Enter Username : ");
        String password = InputValidator.Read_String("Enter Password : ");
        String role     = InputValidator.Read_String("Enter Role     : ");
        double salary   = InputValidator.Read_Double("Enter Salary   : ");

        int id = IdGenerator.generatePersonId();

        Employee employee = new Employee(id, name, phone, email, username, password, role, salary);
        employees.add(employee);

        System.out.println("Employee added successfully. Assigned ID: " + id);
    }

    public Employee searchEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public void updateEmployee() {
        System.out.println("\n--- Update Employee ---");
        int id = InputValidator.Read_Int("Enter Employee ID to update: ");

        Employee employee = searchEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        System.out.println("Found: " + employee.getName() + " — Type 'same' to keep current text, or '-1' for numbers.");

        String name = InputValidator.Read_String("New Name [" + employee.getName() + "]: ");
        if (!name.equalsIgnoreCase("same")) employee.setName(name);

        String phone = InputValidator.Read_String("New Phone [" + employee.getPhone() + "]: ");
        if (!phone.equalsIgnoreCase("same")) employee.setPhone(phone);

        String email = InputValidator.Read_String("New Email [" + employee.getEmail() + "]: ");
        if (!email.equalsIgnoreCase("same")) employee.setEmail(email);

        String username = InputValidator.Read_String("New Username [" + employee.getUsername() + "]: ");
        if (!username.equalsIgnoreCase("same")) employee.setUsername(username);

        String password = InputValidator.Read_String("New Password [hidden]: ");
        if (!password.equalsIgnoreCase("same")) employee.setPassword(password);

        String role = InputValidator.Read_String("New Role [" + employee.getRole() + "]: ");
        if (!role.equalsIgnoreCase("same")) employee.setRole(role);

        double salary = InputValidator.Read_Double("New Salary [" + employee.getSalary() + "] (-1 to keep): ");
        if (salary != -1) employee.setSalary(salary);

        System.out.println("Employee updated successfully.");
    }

    public void deleteEmployee() {
        System.out.println("\n--- Delete Employee ---");
        int id = InputValidator.Read_Int("Enter Employee ID to delete: ");

        Employee employee = searchEmployeeById(id);
        if (employee == null) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        employees.remove(employee);
        System.out.println("Employee '" + employee.getName() + "' removed successfully.");
    }

    public void listAllEmployees() {
        System.out.println("\n--- Employee List ---");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }
        for (Employee e : employees) {
            System.out.println(e);
        }
        System.out.println("Total employees: " + employees.size());
    }
}