package entity;

public class Employee extends User {

    private String role;
    private double salary;

    public Employee(int id, String name, String phone, String email,
                    String username, String password, String role, double salary) {
        super(id, name, phone, email, username, password);
        this.role = role;
        this.salary = salary;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return "=== Employee Details ===" +
               "\n  ID       : " + getId() +
               "\n  Name     : " + getName() +
               "\n  Phone    : " + getPhone() +
               "\n  Email    : " + getEmail() +
               "\n  Username : " + getUsername() +
               "\n  Role     : " + role +
               "\n  Salary   : $" + String.format("%.2f", salary) +
               "\n========================";
    }
}