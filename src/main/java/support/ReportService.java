package support;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import core.BillingService;
import entity.Bill;
import entity.Customer;
import entity.Employee;
import entity.Offer;
import entity.Payment;

public class ReportService {

    private static void openReportFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            System.out.println("⚠️ Can't open report file: " + e.getMessage());
        }
    }

    public static void generateSalesReport() {
        ArrayList<Bill> bills = BillingService.getBills();
        String fileName = "Sales_Report.txt";

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("\n╔══════════════════════════════════════════════════════╗");
            writer.println("║              📊  SALES REPORT                       ║");
            writer.println("╚══════════════════════════════════════════════════════╝");

            if (bills.isEmpty()) {
                writer.println("  No bills recorded yet.");
            } else {
                writer.printf("%-6s %-12s %-10s %-10s %-10s %-10s %-8s%n",
                        "Bill#", "Order ID", "Subtotal", "Discount", "Tax", "Total", "Status");
                writer.println("─".repeat(70));

                double totalRevenue  = 0;
                double totalDiscount = 0;
                double totalTax      = 0;

                for (Bill bill : bills) {
                    writer.printf("%-6d %-12s %-10.2f %-10.2f %-10.2f %-10.2f %-8s%n",
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

                writer.println("─".repeat(70));
                writer.printf("  Total Bills    : %d%n",     bills.size());
                writer.printf("  Total Discounts: %.2f EGP%n", totalDiscount);
                writer.printf("  Total Tax      : %.2f EGP%n", totalTax);
                writer.printf("  TOTAL REVENUE  : %.2f EGP%n", totalRevenue);
                writer.println("═".repeat(70) + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving report: " + e.getMessage());
        }
        
        openReportFile(fileName);
    }

    public static void generateCustomerReport(List<Customer> customers) {
        String fileName = "Customer_Report.txt";

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("\n╔══════════════════════════════════════════════════════╗");
            writer.println("║              👥  CUSTOMER REPORT                    ║");
            writer.println("╚══════════════════════════════════════════════════════╝");

            if (customers.isEmpty()) {
                writer.println("  No customers found.");
            } else {
                writer.printf("%-6s %-20s %-14s %-14s %-10s%n",
                        "ID", "Name", "Phone", "Total Paid", "Points");
                writer.println("─".repeat(68));

                for (Customer c : customers) {
                    writer.printf("%-6d %-20s %-14s %-14.2f %-10d%n",
                            c.getId(),              
                            c.getName(),           
                            c.getPhone(),       
                            c.getTotalPayments(),  
                            c.getLoyaltyPoints());  
                }

                writer.println("─".repeat(68));
                writer.printf("  Total Customers: %d%n", customers.size());
                writer.println("═".repeat(68) + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving report: " + e.getMessage());
        }

        openReportFile(fileName);
    }

    public static void generateEmployeeReport(List<Employee> employees) {
        String fileName = "Employee_Report.txt";

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("\n╔══════════════════════════════════════════════════════╗");
            writer.println("║            🧑‍💼  EMPLOYEE REPORT                    ║");
            writer.println("╚══════════════════════════════════════════════════════╝");

            if (employees.isEmpty()) {
                writer.println("  No employees found.");
            } else {
                writer.printf("%-6s %-20s %-14s %-15s%n",
                        "ID", "Name", "Phone", "Role");
                writer.println("─".repeat(58));

                for (Employee emp : employees) {
                    writer.printf("%-6d %-20s %-14s %-15s%n",
                            emp.getId(),    
                            emp.getName(),   
                            emp.getPhone(),  
                            emp.getRole()    
                    );
                }

                writer.println("─".repeat(58));
                writer.printf("  Total Employees: %d%n", employees.size());
                writer.println("═".repeat(58) + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving report: " + e.getMessage());
        }

        openReportFile(fileName);
    }

    public static void generatePaymentReport() {
        ArrayList<Payment> payments = BillingService.getPayments();
        String fileName = "Payment_Report.txt";

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("\n╔══════════════════════════════════════════════════════╗");
            writer.println("║              💳  PAYMENT REPORT                     ║");
            writer.println("╚══════════════════════════════════════════════════════╝");

            if (payments.isEmpty()) {
                writer.println("  No payments recorded yet.");
            } else {
                writer.printf("%-10s %-8s %-20s %-10s %-10s %-12s%n",
                        "Payment#", "Bill#", "Customer", "Amount", "Method", "Date");
                writer.println("─".repeat(74));

                double totalCollected = 0;

                for (Payment p : payments) {
                    writer.printf("%-10d %-8d %-20s %-10.2f %-10s %-12s%n",
                            p.getPaymentId(),
                            p.getBill().getBillId(),
                            //Mahmoud Gad Customer file
                            p.getCustomer().getName(), 
                            p.getAmountPaid(),
                            p.getMethod(),
                            p.getPaymentDate());

                    totalCollected += p.getAmountPaid();
                }

                writer.println("─".repeat(74));
                writer.printf("  Total Transactions : %d%n",     payments.size());
                writer.printf("  Total Collected    : %.2f EGP%n", totalCollected);
                writer.println("═".repeat(74) + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving report: " + e.getMessage());
        }

        openReportFile(fileName);
    }

    public static void generateOffersReport() {
        ArrayList<Offer> offers = BillingService.getOffers();
        String fileName = "Offers_Report.txt";

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("\n╔══════════════════════════════════════════════════════╗");
            writer.println("║              🏷️   OFFERS REPORT                     ║");
            writer.println("╚══════════════════════════════════════════════════════╝");

            if (offers.isEmpty()) {
                writer.println("  No offers found.");
            } else {
                for (Offer o : offers) {
                    writer.println("  " + o);
                }
                writer.println("═".repeat(70) + "\n");
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving report: " + e.getMessage());
        }

        openReportFile(fileName);
    }
}