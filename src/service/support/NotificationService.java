package service.support;


import entity.Gift;
import entity.Notification;
import entity.Offer;

//Mahmoud Gad Customer file
import entity.Customer;

//Mahmoud Gad Employee file
import entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private static ArrayList<Notification> notifications = new ArrayList<>();

    //Mahmoud Gad Customer file
    public static void notifyEmployeesAboutOffer(List<Employee> employees, Offer offer) {

        
        String message = "📢 New Offer: " + offer.getOfferName()
                       + " | Discount: " + offer.getDiscountAmount() + " EGP"
                       + " | Min Spend Required: " + offer.getMinimumPayment() + " EGP"
                       + " | Info: " + offer.getDescription();

        for (Employee emp : employees) {

            //Mahmoud Gad Customer file
            Notification notif = new Notification(message, emp.getName(), "OFFER");
            notifications.add(notif);

            System.out.println("🔔 Notification sent to employee: " + emp.getName());
            System.out.println("   → " + message);
        }
    }

    //Mahmoud Gad Customer file
    public static void notifyCustomerAboutGift(Customer customer, Gift gift) {

        //Mahmoud Gad Customer file
        String message = "🎁 Congratulations " + customer.getName() + "!"
                       + " You've earned: " + gift.getGiftName()
                       + " | " + gift.getDescription()
                       + " (You've spent " + gift.getRequiredSpending() + "+ EGP — thank you!)";

       //Mahmoud Gad Customer file
        Notification notif = new Notification(message, customer.getName(), "GIFT");
        notifications.add(notif);

        //Mahmoud Gad Customer file
        customer.getGifts().add(gift);

        System.out.println("🔔 Gift notification sent to: " + customer.getName());
        System.out.println("   → " + message);
    }

    //Mahmoud Gad Customer file
    public static void checkAndNotifyGifts(Customer customer, List<Gift> gifts) {

        System.out.println("   Checking gift eligibility for " + customer.getName() + "...");

        for (Gift gift : gifts) {

            boolean qualifies = customer.getTotalPayments() >= gift.getRequiredSpending();

            boolean alreadyHasGift = customer.getGifts().contains(gift);

            if (qualifies && !alreadyHasGift) {
                notifyCustomerAboutGift(customer, gift);
            }
        }
    }

    public static ArrayList<Notification> getAllNotifications() {
        return notifications;
    }

    public static void printNotificationsFor(String recipientName) {
        System.out.println("\n=== Notifications for: " + recipientName + " ===");
        boolean found = false;
        for (Notification n : notifications) {
            if (n.getRecipient().equalsIgnoreCase(recipientName)) {
                System.out.println(n);
                found = true;
            }
        }
        if (!found) System.out.println("   No notifications found for " + recipientName + ".");
    }

    public static void printNotificationsByType(String type) {
        System.out.println("\n=== All " + type + " Notifications ===");
        for (Notification n : notifications) {
            if (n.getType().equalsIgnoreCase(type)) {
                System.out.println(n);
            }
        }
    }
}
