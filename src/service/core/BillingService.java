package service.core;

import constants.PaymentMethod;
import constants.PaymentStatus;
import entity.Bill;
import entity.Offer;
import entity.Payment;

// Marwan Alaa Order file
import entity.Order;

//Mahmoud Gad Customer file
import entity.Customer;

import java.util.ArrayList;

public class BillingService {

    private static ArrayList<Bill>    bills    = new ArrayList<>();
    private static ArrayList<Payment> payments = new ArrayList<>();
    private static ArrayList<Offer>   offers   = new ArrayList<>();

    //Create Bill

    /**
     * Called by Yousef Alaa employee menu
     * Mahmoud Gad Customer file
     * Marwan Alaa Order file
     */
    public static Bill createBill(Order order, Customer customer) {

        double discount = applyOffer(customer);

        Bill bill = new Bill(order, customer, discount);
        
        bills.add(bill);

        System.out.println("\n✅ Bill created successfully!");
        bill.printBill(); 

        return bill;
    }

    //Process Payment

    /**
     * Called by: Yousef Alaa's employee menu
     * Mahmoud Gad Customer file
     */
    public static Payment processPayment(Bill bill, Customer customer, PaymentMethod method) {

        if (bill.getPaymentStatus() == PaymentStatus.PAID) {
            System.out.println("⚠️  Bill #" + bill.getBillId() + " is already paid.");
            return null;
        }

        Payment payment = new Payment(bill, customer, method);

        bill.setPaymentStatus(PaymentStatus.PAID);

        payments.add(payment);

        System.out.println("✅ Payment complete: " + payment);
        return payment;
    }

    //Apply Offer
    //Mahmoud Gad Customer file

    public static double applyOffer(Customer customer) {
        double bestDiscount = 0.0;
        String appliedOfferName = "";

        for (Offer offer : offers) {

            
            boolean customerQualifies = customer.getTotalPayments() >= offer.getMinimumPayment();

            if (offer.isActive() && customerQualifies) {
                if (offer.getDiscountAmount() > bestDiscount) {
                    bestDiscount = offer.getDiscountAmount();
                    appliedOfferName = offer.getOfferName();
                }
            }
        }

        if (bestDiscount > 0) {
            System.out.printf("🎁 Offer applied: '%s' — %.2f EGP discount!%n",
                    appliedOfferName, bestDiscount);
        } else {
            System.out.println("   No offers applied for this customer.");
        }

        return bestDiscount;
    }

    //Offer Management

    public static void addOffer(Offer offer) {
        offers.add(offer);
        System.out.println("✅ Offer added: " + offer.getOfferName());
    }

    public static void deactivateOffer(int offerId) {
        for (Offer offer : offers) {
            if (offer.getOfferId() == offerId) {
                offer.setActive(false);
                System.out.println("✅ Offer #" + offerId + " deactivated.");
                return;
            }
        }
        System.out.println("⚠️  Offer #" + offerId + " not found.");
    }

    //Search
    //Used by Yousef Alaa's menu when employee wants to pay a specific bill
     
    public static Bill findBillById(int billId) {
        for (Bill b : bills) {
            if (b.getBillId() == billId) return b;
        }
        System.out.println("⚠️  Bill #" + billId + " not found.");
        return null;
    }

    public static ArrayList<Bill> getBills()        { return bills; }

    public static ArrayList<Payment> getPayments()  { return payments; }

    public static ArrayList<Offer> getOffers()      { return offers; }
}
