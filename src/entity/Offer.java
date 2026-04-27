package entity;

//Mohamed Yasser IdGenerator file
import helper.IdGenerator;

//Mohamed Yasser AppConfig file
import config.AppConfig;

public class Offer {

    private int offerId;            
    private String offerName;       
    private String description;     
    private double discountAmount;  
    private double minimumPayment;  
    private boolean isActive;       

    public Offer(String offerName, String description,
                 double discountAmount, double minimumPayment) {

        //Mohamed Yasser IdGenerator file
        this.offerId = IdGenerator.generateOfferId();

        this.offerName      = offerName;
        this.description    = description;
        this.discountAmount = discountAmount;
        this.minimumPayment = minimumPayment;
        this.isActive       = true; 
    }

    public Offer(String offerName, String description, double discountAmount) {
        this(offerName, description, discountAmount,
             AppConfig.OFFER_MIN_PAYMENT); //Mohamed Yasser AppConfig file
    }

    public int getOfferId()           { return offerId; }
    public String getOfferName()      { return offerName; }
    public String getDescription()    { return description; }
    public double getDiscountAmount() { return discountAmount; }
    public double getMinimumPayment() { return minimumPayment; }
    public boolean isActive()         { return isActive; }


    public void setActive(boolean active)             { this.isActive = active; }
    public void setDiscountAmount(double amount)      { this.discountAmount = amount; }
    public void setMinimumPayment(double minimum)     { this.minimumPayment = minimum; }
    public void setOfferName(String name)             { this.offerName = name; }
    public void setDescription(String description)    { this.description = description; }

    @Override
    public String toString() {
        return String.format(
            "Offer#%d: %-20s | Discount: %.2f EGP | Min Spend: %.2f EGP | Active: %b",
            offerId, offerName, discountAmount, minimumPayment, isActive
        );
    }
}
