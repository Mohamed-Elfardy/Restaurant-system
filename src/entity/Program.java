package entity;

import helper.IdGenerator;

public class Program {
    private String programId;
    private String name;
    private String description;
    private boolean active;

    public Program() {
        this("PRG" + IdGenerator.generateProgramId(), "", "", true);
    }

    public Program(String name, String description) {
        this("PRG" + IdGenerator.generateProgramId(), name, description, true);
    }

    public Program(String name, String description, boolean active) {
        this("PRG" + IdGenerator.generateProgramId(), name, description, active);
    }

    public Program(String programId, String name, String description, boolean active) {
        this.programId = programId;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public boolean registerCustomer(Customer customer) {
        if (customer == null || !active) {
            return false;
        }

        if (!customer.getPrograms().contains(this)) {
            customer.getPrograms().add(this);
            return true;
        }

        return false;
    }

    public boolean unregisterCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }

        return customer.getPrograms().remove(this);
    }

    public boolean isCustomerRegistered(Customer customer) {
        return customer != null && customer.getPrograms().contains(this);
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId='" + programId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", active=" + active +
                '}';
    }
}
