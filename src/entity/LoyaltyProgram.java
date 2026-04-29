package entity;

import config.AppConfig;

public class LoyaltyProgram extends Program {
    private double pointsPerAmount;

    public LoyaltyProgram() {
        this("Loyalty Program", "Awards points according to customer spending", true,
                AppConfig.LOYALTY_POINTS_PER_EGP);
    }

    public LoyaltyProgram(String name, String description, boolean active,
                          double pointsPerAmount) {
        super(name, description, active);
        this.pointsPerAmount = pointsPerAmount;
    }

    public LoyaltyProgram(String programId, String name, String description, boolean active,
                          double pointsPerAmount) {
        super(programId, name, description, active);
        this.pointsPerAmount = pointsPerAmount;
    }

    public double getPointsPerAmount() {
        return pointsPerAmount;
    }

    public void setPointsPerAmount(double pointsPerAmount) {
        this.pointsPerAmount = pointsPerAmount;
    }

    public int calculatePoints(double spendingAmount) {
        if (!isActive() || spendingAmount <= 0 || pointsPerAmount <= 0) {
            return 0;
        }

        return (int) Math.floor(spendingAmount * pointsPerAmount);
    }

    public int calculatePoints(Customer customer) {
        if (customer == null) {
            return 0;
        }

        return calculatePoints(customer.getTotalPayments());
    }

    public int awardPoints(Customer customer, double spendingAmount) {
        if (customer == null) {
            return 0;
        }

        int earnedPoints = calculatePoints(spendingAmount);
        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + earnedPoints);
        registerCustomer(customer);
        return earnedPoints;
    }

    public int awardPoints(Customer customer) {
        if (customer == null) {
            return 0;
        }

        return awardPoints(customer, customer.getTotalPayments());
    }

    @Override
    public String toString() {
        return "LoyaltyProgram{" +
                "programId='" + getProgramId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", active=" + isActive() +
                ", pointsPerAmount=" + pointsPerAmount +
                '}';
    }
}
