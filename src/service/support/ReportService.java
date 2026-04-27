package service.support;

import entity.Bill;
import entity.Payment;
import entity.Offer;
import service.core.BillingService;

//Mahmoud Gad Customer file
import entity.Customer;

//Mahmoud Gad Employee file
import entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class ReportService {

    public static void generateSalesReport() {

        ArrayList<Bill> bills = BillingService.getBills();

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              📊  SALES REPORT                       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        if (bills.isEmpty()) {
            System.out.println("  No bills recorded yet.");
            return;
        }

        System.out.printf("%-6s %-12s %-10s %-10s %-10s %-10s %-8s%n",
                "Bill#", "Order ID", "Subtotal", "Discount", "Tax", "Total", "Status");
        System.out.println("─".repeat(70));

        double totalRevenue  = 0;
        double totalDiscount = 0;
        double totalTax      = 0;

        for (Bill bill : bills) {
            System.out.printf("%-6d %-12s %-10.2f %-10.2f %-10.2f %-10.2f %-8s%n",
                    bill.getBillId(),
                    bill.getOrder().getOrderId(),    // Marwan Alaa Order file
                    bill.getSubtotal(),
                    bill.getDiscount(),
                    bill.getTax(),
                    bill.getFinalTotal(),
                    bill.getPaymentStatus());

            totalRevenue  += bill.getFinalTotal();
            totalDiscount += bill.getDiscount();
            totalTax      += bill.getTax();
        }

        System.out.println("─".repeat(70));
        System.out.printf("  Total Bills    : %d%n",     bills.size());
        System.out.printf("  Total Discounts: %.2f EGP%n", totalDiscount);
        System.out.printf("  Total Tax      : %.2f EGP%n", totalTax);
        System.out.printf("  TOTAL REVENUE  : %.2f EGP%n", totalRevenue);
        System.out.println("═".repeat(70) + "\n");
    }

    //Mahmoud Gad Customer file
    public static void generateCustomerReport(List<Customer> customers) {

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              👥  CUSTOMER REPORT                    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        if (customers.isEmpty()) {
            System.out.println("  No customers found.");
            return;
        }

        System.out.printf("%-6s %-20s %-14s %-14s %-10s%n",
                "ID", "Name", "Phone", "Total Paid", "Points");
        System.out.println("─".repeat(68));

        for (Customer c : customers) {
            System.out.printf("%-6d %-20s %-14s %-14.2f %-10d%n",

                    c.getId(),              
                    c.getName(),           
                    c.getPhone(),       
                    c.getTotalPayments(),  
                    c.getLoyaltyPoints());  
        }

        System.out.println("─".repeat(68));
        System.out.printf("  Total Customers: %d%n", customers.size());
        System.out.println("═".repeat(68) + "\n");
    }

    //Mahmoud Gad Employee file
    public static void generateEmployeeReport(List<Employee> employees) {

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║            🧑‍💼  EMPLOYEE REPORT                    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        if (employees.isEmpty()) {
            System.out.println("  No employees found.");
            return;
        }

        System.out.printf("%-6s %-20s %-14s %-15s%n",
                "ID", "Name", "Phone", "Role");
        System.out.println("─".repeat(58));

        for (Employee emp : employees) {
            System.out.printf("%-6d %-20s %-14s %-15s%n",
                    
                    emp.getId(),    
                    emp.getName(),   
                    emp.getPhone(),  
                    emp.getRole()    
                                    
            );
        }

        System.out.println("─".repeat(58));
        System.out.printf("  Total Employees: %d%n", employees.size());
        System.out.println("═".repeat(58) + "\n");
    }


    public static void generatePaymentReport() {

        ArrayList<Payment> payments = BillingService.getPayments();

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              💳  PAYMENT REPORT                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        if (payments.isEmpty()) {
            System.out.println("  No payments recorded yet.");
            return;
        }

        System.out.printf("%-10s %-8s %-20s %-10s %-10s %-12s%n",
                "Payment#", "Bill#", "Customer", "Amount", "Method", "Date");
        System.out.println("─".repeat(74));

        double totalCollected = 0;

        for (Payment p : payments) {
            System.out.printf("%-10d %-8d %-20s %-10.2f %-10s %-12s%n",
                    p.getPaymentId(),
                    p.getBill().getBillId(),

                    //Mahmoud Gad Customer file
                    p.getCustomer().getName(), 

                    p.getAmountPaid(),
                    p.getMethod(),
                    p.getPaymentDate());

            totalCollected += p.getAmountPaid();
        }

        System.out.println("─".repeat(74));
        System.out.printf("  Total Transactions : %d%n",     payments.size());
        System.out.printf("  Total Collected    : %.2f EGP%n", totalCollected);
        System.out.println("═".repeat(74) + "\n");
    }

    public static void generateOffersReport() {

        ArrayList<Offer> offers = BillingService.getOffers();

        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║              🏷️   OFFERS REPORT                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        if (offers.isEmpty()) {
            System.out.println("  No offers found.");
            return;
        }

        for (Offer o : offers) {
            System.out.println("  " + o);
        }
        System.out.println("═".repeat(70) + "\n");
    }
}
