package entity;

import java.util.ArrayList;

public class Customer extends Person {

    private String address;
    private double totalPayments;
    private int loyaltyPoints;

    private ArrayList<Order>   orderHistory;
    private ArrayList<Bill>    billHistory;
    private ArrayList<Gift>    gifts;
    private ArrayList<Program> programs;

    public Customer(int id, String name, String phone, String email, String address) {
        super(id, name, phone, email);
        this.address       = address;
        this.totalPayments = 0.0;
        this.loyaltyPoints = 0;
        this.orderHistory  = new ArrayList<>();
        this.billHistory   = new ArrayList<>();
        this.gifts         = new ArrayList<>();
        this.programs      = new ArrayList<>();
    }

    // ── Getters ────────────────────────────────────────────────
    public String getAddress()                  { return address; }
    public double getTotalPayments()            { return totalPayments; }
    public int getLoyaltyPoints()               { return loyaltyPoints; }
    public ArrayList<Order>   getOrderHistory() { return orderHistory; }
    public ArrayList<Bill>    getBillHistory()  { return billHistory; }
    public ArrayList<Gift>    getGifts()        { return gifts; }
    public ArrayList<Program> getPrograms()     { return programs; }

    // ── Setters ────────────────────────────────────────────────
    public void setAddress(String address)            { this.address = address; }
    public void setTotalPayments(double totalPayments){ this.totalPayments = totalPayments; }
    public void setLoyaltyPoints(int loyaltyPoints)   { this.loyaltyPoints = loyaltyPoints; }
    public void setOrderHistory(ArrayList<Order> orderHistory)    { this.orderHistory = orderHistory; }
    public void setBillHistory(ArrayList<Bill> billHistory)       { this.billHistory  = billHistory; }
    public void setGifts(ArrayList<Gift> gifts)                   { this.gifts        = gifts; }
    public void setPrograms(ArrayList<Program> programs)          { this.programs     = programs; }

    @Override
    public String toString() {
        return "=== Customer Details ===" +
               "\n  ID             : " + getId() +
               "\n  Name           : " + getName() +
               "\n  Phone          : " + getPhone() +
               "\n  Email          : " + getEmail() +
               "\n  Address        : " + address +
               "\n  Total Payments : $" + String.format("%.2f", totalPayments) +
               "\n  Loyalty Points : " + loyaltyPoints +
               "\n========================";
    }
}