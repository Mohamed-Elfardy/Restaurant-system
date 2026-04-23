package service;

import constants.OrderStatus;
import entity.Customer;
import entity.Employee;
import entity.Meal;
import entity.Order;
import helper.IdGenerator;

import java.util.ArrayList;  // Marwan

public class OrderService {
    private ArrayList<Order> orders;

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    // ── CREATE 
    public Order createOrder(Customer customer, Employee employee) {
        String id = IdGenerator.generateOrderId();
        Order order = new Order(id, customer, employee);
        orders.add(order);
        System.out.println("Order created: " + id);
        return order;
    }

    // ── ADD / REMOVE MEALS 
    public void addMealToOrder(String orderId, Meal meal, int quantity) {
        Order order = getOrderById(orderId);
        if (order == null) return;
        order.addItem(meal, quantity);
    }

    public void removeMealFromOrder(String orderId, String mealId) {
        Order order = getOrderById(orderId);
        if (order == null) return;
        boolean removed = order.removeItem(mealId);
        System.out.println(removed ? "Item removed." : "Item not found in order.");
    }

    // ── TOTAL 
    public double calculateOrderTotal(String orderId) {
        Order order = getOrderById(orderId);
        if (order == null) return 0;
        return order.calculateTotal();
    }

    // ── STATUS 
    public boolean cancelOrder(String orderId) {
        Order order = getOrderById(orderId);
        if (order == null) return false;
        return order.cancelOrder();
    }

    public void confirmOrder(String orderId) {
        Order order = getOrderById(orderId);
        if (order == null) return;
        order.confirmOrder();
        System.out.println("Order " + orderId + " confirmed.");
    }

    public void completeOrder(String orderId) {
        Order order = getOrderById(orderId);
        if (order == null) return;
        order.completeOrder();
        System.out.println("Order " + orderId + " completed.");
    }

    // ── SEARCH / LIST 
    public Order getOrderById(String orderId) {
        for (Order o : orders) {
            if (o.getOrderId().equals(orderId)) return o;
        }
        System.out.println("Order not found: " + orderId);
        return null;
    }

    public ArrayList<Order> getOrdersByStatus(OrderStatus status) {
        ArrayList<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if (o.getStatus() == status) result.add(o);
        }
        return result;
    }

    public void listAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (Order o : orders) System.out.println(o);
    }

    // ── GETTER 
    public ArrayList<Order> getAllOrders() { return orders; }
}