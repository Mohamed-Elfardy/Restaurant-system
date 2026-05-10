package entity;

public class MarketingProgram extends Program {
    private String channel;
    private String campaignName;

    public MarketingProgram() {
        this("", "", true, "", "");
    }

    public MarketingProgram(String name, String description, boolean active,
                            String channel, String campaignName) {
        super(name, description, active);
        this.channel = channel;
        this.campaignName = campaignName;
    }

    public MarketingProgram(String programId, String name, String description, boolean active,
                            String channel, String campaignName) {
        super(programId, name, description, active);
        this.channel = channel;
        this.campaignName = campaignName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public boolean registerCustomerForCampaign(Customer customer) {
        return registerCustomer(customer);
    }

    @Override
    public String toString() {
        return "MarketingProgram{" +
                "programId='" + getProgramId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", active=" + isActive() +
                ", channel='" + channel + '\'' +
                ", campaignName='" + campaignName + '\'' +
                '}';
    }
}
