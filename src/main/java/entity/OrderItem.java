package entity;

public class OrderItem {  // Marwan
    private Meal meal;
    private int quantity;
    private double priceAtOrderTime; 

    public OrderItem(Meal meal, int quantity) {
        this.meal             = meal;
        this.quantity         = quantity;
        this.priceAtOrderTime = meal.getPrice(); 
    }

    // Getters
    public Meal getMeal()               { return meal; }
    public int getQuantity()            { return quantity; }
    public double getPriceAtOrderTime() { return priceAtOrderTime; }

    // Setter
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getSubtotal() {
        return priceAtOrderTime * quantity;
    }
    
    @Override
    public String toString() {
        return String.format("%s x%d @ $%.2f = $%.2f",
                meal.getName(), quantity, priceAtOrderTime, getSubtotal());
    }
}