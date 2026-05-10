package entity;

import config.AppConfig;

public class RewardProgram extends Program {
    private double rewardThreshold;

    public RewardProgram() {
        this("Reward Program", "Checks reward eligibility according to total spending", true,
                AppConfig.GIFT_THRESHOLD);
    }

    public RewardProgram(String name, String description, boolean active,
                         double rewardThreshold) {
        super(name, description, active);
        this.rewardThreshold = rewardThreshold;
    }

    public RewardProgram(String programId, String name, String description, boolean active,
                         double rewardThreshold) {
        super(programId, name, description, active);
        this.rewardThreshold = rewardThreshold;
    }

    public double getRewardThreshold() {
        return rewardThreshold;
    }

    public void setRewardThreshold(double rewardThreshold) {
        this.rewardThreshold = rewardThreshold;
    }

    public boolean isEligible(double totalSpending) {
        return isActive() && totalSpending >= rewardThreshold;
    }

    public boolean isEligible(Customer customer) {
        return customer != null && isEligible(customer.getTotalPayments());
    }

    public double remainingAmount(double totalSpending) {
        if (totalSpending >= rewardThreshold) {
            return 0.0;
        }

        return rewardThreshold - totalSpending;
    }

    public double remainingAmount(Customer customer) {
        if (customer == null) {
            return rewardThreshold;
        }

        return remainingAmount(customer.getTotalPayments());
    }

    public boolean registerCustomerIfEligible(Customer customer) {
        if (!isEligible(customer)) {
            return false;
        }

        return registerCustomer(customer);
    }

    @Override
    public String toString() {
        return "RewardProgram{" +
                "programId='" + getProgramId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", active=" + isActive() +
                ", rewardThreshold=" + rewardThreshold +
                '}';
    }
}
