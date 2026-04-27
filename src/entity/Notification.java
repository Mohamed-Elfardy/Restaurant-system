package entity;

//Mohamed Yasser IdGenerator file
import helper.IdGenerator;

import java.time.LocalDate;

public class Notification {

    private int notificationId;  
    private String message;      
    private String recipient;    
    private String type;         
    private LocalDate sentDate;  

    public Notification(String message, String recipient, String type) {

        //Mohamed Yasser IdGenerator file
        this.notificationId = IdGenerator.generateNotificationId();

        this.message   = message;
        this.recipient = recipient;
        this.type      = type;
        this.sentDate  = LocalDate.now();
    }

    public int getNotificationId() { return notificationId; }
    public String getMessage()     { return message; }
    public String getRecipient()   { return recipient; }
    public String getType()        { return type; }
    public LocalDate getSentDate() { return sentDate; }

    @Override
    public String toString() {
        return String.format("[%s] To: %-20s | %s | Sent: %s",
                type, recipient, message, sentDate);
    }
}
