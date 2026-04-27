package entity;

//Mohamed Yasser IdGenerator file
import helper.IdGenerator;

//Mohamed Yasser AppConfig file
import config.AppConfig;


public class Gift {

    private int giftId;              
    private String giftName;          
    private String description;       
    private double requiredSpending;  

    public Gift(String giftName, String description, double requiredSpending) {

        //Mohamed Yasser IdGenerator file
        this.giftId           = IdGenerator.generateGiftId();

        this.giftName         = giftName;
        this.description      = description;
        this.requiredSpending = requiredSpending;
    }

    public Gift(String giftName, String description) {
        this(giftName, description,
             AppConfig.GIFT_THRESHOLD); //Mohamed Yasser AppConfig file
    }

    public int getGiftId()              { return giftId; }
    public String getGiftName()         { return giftName; }
    public String getDescription()      { return description; }
    public double getRequiredSpending() { return requiredSpending; }

    public void setGiftName(String name)             { this.giftName = name; }
    public void setDescription(String desc)          { this.description = desc; }
    public void setRequiredSpending(double amount)   { this.requiredSpending = amount; }

    @Override
    public String toString() {
        return String.format(
            "Gift#%d: %-20s | Required Spend: %.2f EGP | Detail: %s",
            giftId, giftName, requiredSpending, description
        );
    }
}
