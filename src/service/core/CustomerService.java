package service.core;

import entity.Customer;
import helper.IdGenerator;
import helper.InputValidator;

import java.util.ArrayList;

public class CustomerService {

    private ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomer() {
        System.out.println("\n--- Add New Customer ---");
        String name    = InputValidator.Read_String("Enter Name    : ");
        String phone   = InputValidator.Read_String("Enter Phone   : ");
        String email   = InputValidator.Read_String("Enter Email   : ");
        String address = InputValidator.Read_String("Enter Address : ");

        int id = IdGenerator.generatePersonId();

        Customer customer = new Customer(id, name, phone, email, address);
        customers.add(customer);

        System.out.println("Customer added successfully. Assigned ID: " + id);
    }

    public Customer searchCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void removeCustomer() {
        System.out.println("\n--- Remove Customer ---");
        int id = InputValidator.Read_Int("Enter Customer ID to remove: ");

        Customer customer = searchCustomerById(id);
        if (customer == null) {
            System.out.println("Customer with ID " + id + " not found.");
            return;
        }

        customers.remove(customer);
        System.out.println("Customer '" + customer.getName() + "' removed successfully.");
    }

    public void updateCustomer() {
        System.out.println("\n--- Update Customer ---");
        int id = InputValidator.Read_Int("Enter Customer ID to update: ");

        Customer customer = searchCustomerById(id);
        if (customer == null) {
            System.out.println("Customer with ID " + id + " not found.");
            return;
        }

        System.out.println("Found: " + customer.getName() + " — Type 'same' to keep current text.");

        String name = InputValidator.Read_String("New Name [" + customer.getName() + "]: ");
        if (!name.equalsIgnoreCase("same")) customer.setName(name);

        String phone = InputValidator.Read_String("New Phone [" + customer.getPhone() + "]: ");
        if (!phone.equalsIgnoreCase("same")) customer.setPhone(phone);

        String email = InputValidator.Read_String("New Email [" + customer.getEmail() + "]: ");
        if (!email.equalsIgnoreCase("same")) customer.setEmail(email);

        String address = InputValidator.Read_String("New Address [" + customer.getAddress() + "]: ");
        if (!address.equalsIgnoreCase("same")) customer.setAddress(address);

        System.out.println("Customer updated successfully.");
    }

    public void displayCustomerHistory() {
        System.out.println("\n--- Customer History ---");
        int id = InputValidator.Read_Int("Enter Customer ID: ");

        Customer customer = searchCustomerById(id);
        if (customer == null) {
            System.out.println("Customer with ID " + id + " not found.");
            return;
        }

        System.out.println("\n  Customer       : " + customer.getName());
        System.out.println("  Total Payments : $" + String.format("%.2f", customer.getTotalPayments()));
        System.out.println("  Loyalty Points : " + customer.getLoyaltyPoints());
        System.out.println("  Total Orders   : " + customer.getOrderHistory().size());
        System.out.println("  Total Bills    : " + customer.getBillHistory().size());
        System.out.println("  Total Gifts    : " + customer.getGifts().size());
        System.out.println("  Total Programs : " + customer.getPrograms().size());
    }

    public void listAllCustomers() {
        System.out.println("\n--- Customer List ---");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c);
        }
        System.out.println("Total customers: " + customers.size());
    }
}