package entity;

import constants.OrderStatus;
import java.time.LocalDateTime;  // Marwan
import java.util.ArrayList;

public class Order {
    private String orderId;
    private Customer customer;   // Mahmoud Gad — Customer extends Person
    private Employee employee;   // Mahmoud Gad — Employee extends User extends Person
    private ArrayList<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime orderTime;
    private double totalAmount;

    public Order(String orderId, Customer customer, Employee employee) {
        this.orderId     = orderId;
        this.customer    = customer;
        this.employee    = employee;
        this.items       = new ArrayList<>();
        this.status      = OrderStatus.PENDING;
        this.orderTime   = LocalDateTime.now();
        this.totalAmount = 0.0;
    }

    // Add a meal — merges quantity if the meal already exists in the order
    public void addItem(Meal meal, int quantity) {
        if (status != OrderStatus.PENDING && status != OrderStatus.CONFIRMED) {
            System.out.println("Cannot add items to an order with status: " + status);
            return;
        }
        for (OrderItem item : items) {
            if (item.getMeal().getMealId().equals(meal.getMealId())) {
                item.setQuantity(item.getQuantity() + quantity);
                calculateTotal();
                return;
            }
        }
        items.add(new OrderItem(meal, quantity));
        calculateTotal();
    }

    // Remove a meal by meal ID
    public boolean removeItem(String mealId) {
        if (status != OrderStatus.PENDING && status != OrderStatus.CONFIRMED) {
            System.out.println("Cannot remove items from an order with status: " + status);
            return false;
        }
        boolean removed = items.removeIf(item -> item.getMeal().getMealId().equals(mealId));
        if (removed) calculateTotal();
        return removed;
    }

    // Recalculate and return the order total
    public double calculateTotal() {
        totalAmount = 0;
        for (OrderItem item : items) {
            totalAmount += item.getSubtotal();
        }
        return totalAmount;
    }

    // Cancel the order
    public boolean cancelOrder() {
        if (status == OrderStatus.COMPLETED) {
            System.out.println("Cannot cancel a completed order.");
            return false;
        }
        if (status == OrderStatus.CANCELLED) {
            System.out.println("Order is already cancelled.");
            return false;
        }
        this.status = OrderStatus.CANCELLED;
        System.out.println("Order " + orderId + " has been cancelled.");
        return true;
    }

    public void confirmOrder() {
        if (status == OrderStatus.PENDING) {
            this.status = OrderStatus.CONFIRMED;
        }
    }

    public void completeOrder() {
        if (status == OrderStatus.CONFIRMED) {
            this.status = OrderStatus.COMPLETED;
        }
    }

    // Getters
    public String getOrderId()             { return orderId; }
    public Customer getCustomer()          { return customer; }
    public Employee getEmployee()          { return employee; }
    public ArrayList<OrderItem> getItems() { return items; }
    public OrderStatus getStatus()         { return status; }
    public LocalDateTime getOrderTime()    { return orderTime; }
    public double getTotalAmount()         { return totalAmount; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Order ID: ").append(orderId).append(" ===\n");
        sb.append("Customer : ").append(customer.getName()).append("\n");  // getName() from Person.java
        sb.append("Employee : ").append(employee.getName()).append("\n");  // getName() from Person.java
        sb.append("Time     : ").append(orderTime).append("\n");
        sb.append("Status   : ").append(status).append("\n");
        sb.append("Items    :\n");
        for (OrderItem item : items) {
            sb.append("  - ").append(item).append("\n");
        }
        sb.append(String.format("Total    : $%.2f\n", totalAmount));
        return sb.toString();
    }
}