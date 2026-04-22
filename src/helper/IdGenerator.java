package helper;

public class IdGenerator {
    private static int personId = 1;
    private static int customerId = 1;
    private static int mealId = 1;
    private static int orderId = 1;
    private static int billId = 1;
    private static int paymentId = 1;
    private static int offerId = 1;
    private static int giftId = 1;
    private static int notificationId = 1;
    private static int programId = 1;

    public static int generatePersonId()       { return personId++; }
    public static int generateCustomerId()     { return customerId++; }
    public static int generateMealId()         { return mealId++; }
    public static int generateOrderId()        { return orderId++; }
    public static int generateBillId()         { return billId++; }
    public static int generatePaymentId()      { return paymentId++; }
    public static int generateOfferId()        { return offerId++; }
    public static int generateGiftId()         { return giftId++; }
    public static int generateNotificationId() { return notificationId++; }
    public static int generateProgramId()      { return programId++; }
}