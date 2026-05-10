package entity;

import constants.PaymentMethod;
import constants.PaymentStatus;

//Mahmoud Gad Customer file
import entity.Customer;

//Mohamed Yasser AppConfig file
import config.AppConfig;

//Mohamed Yasser IdGenerator file
import helper.IdGenerator;

import java.time.LocalDate;

public class Payment {

    private int paymentId;         
    private Bill bill;             
    private Customer customer;     //Mahmoud Gad Customer file
    private PaymentMethod method;  
    private PaymentStatus status;  
    private double amountPaid;     
    private LocalDate paymentDate; 

    public Payment(Bill bill, Customer customer, PaymentMethod method) {

        //Mohamed Yasser IdGenerator file
        this.paymentId = IdGenerator.generatePaymentId();

        this.bill     = bill;
        this.customer = customer;      //Mahmoud Gad Customer file
        this.method   = method;
        this.status   = PaymentStatus.PAID;       
        this.amountPaid = bill.getFinalTotal();    
        this.paymentDate = LocalDate.now();

        //Mahmoud Gad Customer file
        double newTotal = customer.getTotalPayments() + amountPaid; 
        customer.setTotalPayments(newTotal);                        

        //Mohamed Yasser AppConfig file
        int pointsEarned = (int)(amountPaid * AppConfig.LOYALTY_POINTS_PER_EGP);
        customer.setLoyaltyPoints(                                  //Mahmoud Gad Customer file
            customer.getLoyaltyPoints()                            //Mahmoud Gad Customer file
        );

        System.out.println("✅ Payment recorded. Points earned: " + pointsEarned);
        System.out.println("   Customer total payments: " + newTotal + " EGP");
    }

    public int getPaymentId()        { return paymentId; }
    public Bill getBill()            { return bill; }
    public Customer getCustomer()    { return customer; }   //Mahmoud Gad Customer file
    public PaymentMethod getMethod() { return method; }
    public PaymentStatus getStatus() { return status; }
    public double getAmountPaid()    { return amountPaid; }
    public LocalDate getPaymentDate(){ return paymentDate; }

    @Override
    public String toString() {
        //Mahmoud Gad Customer file
        return String.format("Payment#%d | Customer: %s | Amount: %.2f EGP | Method: %s | Date: %s",
                paymentId,
                customer.getName(), 
                amountPaid,
                method,
                paymentDate);
    }
}
