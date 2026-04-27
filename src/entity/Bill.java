package entity;

import constants.PaymentStatus;

// Marwan Alaa Order file
import entity.Order;

//Mahmoud Gad Customer file
import entity.Customer;

//Mohamed Yasser AppConfig file
import config.AppConfig;

//Mohamed Yasser IdGenerator file
import helper.IdGenerator;
import java.time.LocalDate;

public class Bill {

    private int billId;                  
    private Order order;                 // Marwan Alaa Order file
    private Customer customer;           // Mahmoud Gad Customer file
    private double subtotal;             
    private double discount;             
    private double tax;                  
    private double finalTotal;           
    private PaymentStatus paymentStatus; 
    private LocalDate billDate;          

    public Bill(Order order, Customer customer, double discount) {

        //Mohamed Yasser IdGenerator file
        this.billId = IdGenerator.generateBillId();

        // Marwan Alaa Order file
        this.order = order;
        this.subtotal = order.getTotalAmount();

        // Mahmoud Gad Customer file
        this.customer = customer;

        this.discount = discount;

        this.tax = this.subtotal * AppConfig.TAX_RATE; // ← 0.14 from Mohamed Yasser

        this.finalTotal = this.subtotal - this.discount + this.tax;

        this.paymentStatus = PaymentStatus.UNPAID;

        this.billDate = LocalDate.now();

        // Mahmoud Gad Customer file
        customer.getBillHistory().add(this); 
    }

    public int getBillId()                  { return billId; }
    public Order getOrder()                 { return order; }       // Marwan Alaa Order file
    public Customer getCustomer()           { return customer; }    // Mahmoud Gad Customer file
    public double getSubtotal()             { return subtotal; }
    public double getDiscount()             { return discount; }
    public double getTax()                  { return tax; }
    public double getFinalTotal()           { return finalTotal; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public LocalDate getBillDate()          { return billDate; }

    public void setPaymentStatus(PaymentStatus status) {
        this.paymentStatus = status;
    }

    public void printBill() {
        System.out.println("========== BILL #" + billId + " ==========");

        // Marwan Alaa Order file
        System.out.println("Order ID   : " + order.getOrderId()); 

        // Mahmoud Gad Customer file
        System.out.println("Customer   : " + customer.getName()); 

        System.out.println("Date       : " + billDate);
        System.out.printf ("Subtotal   : %.2f EGP%n", subtotal);
        System.out.printf ("Discount   : %.2f EGP%n", discount);
        System.out.printf ("Tax (14%%) : %.2f EGP%n", tax);
        System.out.printf ("TOTAL      : %.2f EGP%n", finalTotal);
        System.out.println("Status     : " + paymentStatus);
        System.out.println("================================");
    }

    @Override
    public String toString() {
        return String.format("Bill#%d | Total: %.2f EGP | Status: %s | Date: %s",
                billId, finalTotal, paymentStatus, billDate);
    }
}
