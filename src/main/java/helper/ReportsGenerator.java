package helper;

import entity.*;
import core.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReportsGenerator {

    private static final String REPORT_DIR = "reports/";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ReportsGenerator() {
        // إنشاء مجلد reports إذا لم يكن موجوداً
        new java.io.File(REPORT_DIR).mkdirs();
    }

    // ── تقرير الطلبات ──
    public void generateOrdersReport(OrderService orderService, String filename) {
        StringBuilder report = new StringBuilder();
        report.append("╔════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                        تقرير الطلبات                              ║\n");
        report.append("║                      Orders Report                                 ║\n");
        report.append("╚════════════════════════════════════════════════════════════════════╝\n\n");
        
        report.append("التاريخ والوقت / Date & Time: ").append(LocalDateTime.now().format(dateTimeFormatter)).append("\n\n");

        ArrayList<Order> allOrders = orderService.getAllOrders();
        
        if (allOrders.isEmpty()) {
            report.append("لا توجد طلبات حالياً / No Orders Available\n");
        } else {
            report.append(String.format("إجمالي الطلبات / Total Orders: %d\n\n", allOrders.size()));
            report.append("─".repeat(70)).append("\n");
            report.append(String.format("%-10s | %-15s | %-20s | %-15s | %-10s\n", 
                    "Order ID", "Customer", "Employee", "Status", "Total"));
            report.append("─".repeat(70)).append("\n");

            for (Order order : allOrders) {
                report.append(String.format("%-10s | %-15s | %-20s | %-15s | $%.2f\n",
                        order.getOrderId(),
                        order.getCustomer().getName(),
                        order.getEmployee().getName(),
                        order.getStatus(),
                        order.calculateTotal()
                ));
            }
            report.append("─".repeat(70)).append("\n\n");
        }

        writeToFile(report.toString(), filename);
    }

    // ── تقرير العملاء ──
    public void generateCustomersReport(CustomerService customerService, String filename) {
        StringBuilder report = new StringBuilder();
        report.append("╔════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                      تقرير العملاء                                ║\n");
        report.append("║                    Customers Report                                ║\n");
        report.append("╚════════════════════════════════════════════════════════════════════╝\n\n");
        
        report.append("التاريخ والوقت / Date & Time: ").append(LocalDateTime.now().format(dateTimeFormatter)).append("\n\n");

        ArrayList<Customer> allCustomers = customerService.getAllCustomers();
        
        if (allCustomers.isEmpty()) {
            report.append("لا يوجد عملاء / No Customers Available\n");
        } else {
            report.append(String.format("إجمالي العملاء / Total Customers: %d\n\n", allCustomers.size()));
            report.append("─".repeat(90)).append("\n");
            report.append(String.format("%-5s | %-15s | %-15s | %-20s | %-15s | %-15s\n", 
                    "ID", "Name", "Phone", "Email", "Payments", "Loyalty Pts"));
            report.append("─".repeat(90)).append("\n");

            for (Customer customer : allCustomers) {
                report.append(String.format("%-5d | %-15s | %-15s | %-20s | $%-14.2f | %-15d\n",
                        customer.getId(),
                        customer.getName(),
                        customer.getPhone(),
                        customer.getEmail(),
                        customer.getTotalPayments(),
                        customer.getLoyaltyPoints()
                ));
            }
            report.append("─".repeat(90)).append("\n\n");
        }

        writeToFile(report.toString(), filename);
    }

    // ── تقرير الموظفين ──
    public void generateEmployeesReport(EmployeeService employeeService, String filename) {
        StringBuilder report = new StringBuilder();
        report.append("╔════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                      تقرير الموظفين                              ║\n");
        report.append("║                    Employees Report                                ║\n");
        report.append("╚═══════════════════════════════════════════���════════════════════════╝\n\n");
        
        report.append("التاريخ والوقت / Date & Time: ").append(LocalDateTime.now().format(dateTimeFormatter)).append("\n\n");

        ArrayList<Employee> allEmployees = employeeService.getAllEmployees();
        
        if (allEmployees.isEmpty()) {
            report.append("لا يوجد موظفين / No Employees Available\n");
        } else {
            report.append(String.format("إجمالي الموظفين / Total Employees: %d\n\n", allEmployees.size()));
            report.append("─".repeat(95)).append("\n");
            report.append(String.format("%-5s | %-15s | %-15s | %-20s | %-10s | %-15s\n", 
                    "ID", "Name", "Phone", "Email", "Role", "Salary"));
            report.append("─".repeat(95)).append("\n");

            for (Employee employee : allEmployees) {
                report.append(String.format("%-5d | %-15s | %-15s | %-20s | %-10s | $%-14.2f\n",
                        employee.getId(),
                        employee.getName(),
                        employee.getPhone(),
                        employee.getEmail(),
                        employee.getRole(),
                        employee.getSalary()
                ));
            }
            report.append("─".repeat(95)).append("\n\n");
        }

        writeToFile(report.toString(), filename);
    }

    // ── تقرير الوجبات ──
    public void generateMealsReport(MealService mealService, String filename) {
        StringBuilder report = new StringBuilder();
        report.append("╔════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                       تقرير الوجبات                              ║\n");
        report.append("║                      Meals Report                                  ║\n");
        report.append("╚════════════════════════════════════════════════════════════════════╝\n\n");
        
        report.append("التاريخ والوقت / Date & Time: ").append(LocalDateTime.now().format(dateTimeFormatter)).append("\n\n");

        ArrayList<Meal> allMeals = mealService.getAllMeals();
        
        if (allMeals.isEmpty()) {
            report.append("لا توجد وجبات / No Meals Available\n");
        } else {
            report.append(String.format("إجمالي الوجبات / Total Meals: %d\n\n", allMeals.size()));
            report.append("─".repeat(85)).append("\n");
            report.append(String.format("%-8s | %-15s | %-12s | %-30s | %-8s | %-10s\n", 
                    "Meal ID", "Name", "Category", "Description", "Price", "Status"));
            report.append("─".repeat(85)).append("\n");

            for (Meal meal : allMeals) {
                report.append(String.format("%-8s | %-15s | %-12s | %-30s | $%-7.2f | %-10s\n",
                        meal.getMealId(),
                        meal.getName(),
                        meal.getCategory(),
                        meal.getDescription().length() > 30 ? 
                            meal.getDescription().substring(0, 27) + "..." : meal.getDescription(),
                        meal.getPrice(),
                        meal.isAvailable() ? "Available" : "Not Available"
                ));
            }
            report.append("─".repeat(85)).append("\n\n");
        }

        writeToFile(report.toString(), filename);
    }

    // ── تقرير الفواتير ──
    public void generateBillsReport(BillingService billingService, String filename) {
        StringBuilder report = new StringBuilder();
        report.append("╔════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                       تقرير الفواتير                             ║\n");
        report.append("║                      Bills Report                                  ║\n");
        report.append("╚════════════════════════════════════════════════════════════════════╝\n\n");
        
        report.append("التاريخ والوقت / Date & Time: ").append(LocalDateTime.now().format(dateTimeFormatter)).append("\n\n");

        ArrayList<Bill> allBills = billingService.getAllBills();
        
        if (allBills.isEmpty()) {
            report.append("لا توجد فواتير / No Bills Available\n");
        } else {
            report.append(String.format("إجمالي الفواتير / Total Bills: %d\n\n", allBills.size()));
            
            double totalRevenue = 0;
            double totalTax = 0;
            
            report.append("─".repeat(80)).append("\n");
            report.append(String.format("%-10s | %-15s | %-15s | %-12s | %-12s | %-12s\n", 
                    "Bill ID", "Customer", "Order ID", "Subtotal", "Tax", "Total"));
            report.append("─".repeat(80)).append("\n");

            for (Bill bill : allBills) {
                double subtotal = bill.getSubtotal();
                double tax = bill.getTax();
                double total = bill.getTotal();
                
                totalRevenue += subtotal;
                totalTax += tax;
                
                report.append(String.format("%-10s | %-15s | %-15s | $%-11.2f | $%-11.2f | $%-11.2f\n",
                        bill.getBillId(),
                        bill.getOrder().getCustomer().getName(),
                        bill.getOrder().getOrderId(),
                        subtotal,
                        tax,
                        total
                ));
            }
            report.append("─".repeat(80)).append("\n");
            report.append(String.format("الإجمالي / TOTAL REVENUE: $%.2f\n", totalRevenue));
            report.append(String.format("الضرائب / TOTAL TAX: $%.2f\n\n", totalTax));
        }

        writeToFile(report.toString(), filename);
    }

    // ── تقرير شامل ──
    public void generateFullReport(OrderService orderService, CustomerService customerService, 
                                   EmployeeService employeeService, MealService mealService, 
                                   BillingService billingService, String filename) {
        StringBuilder report = new StringBuilder();
        report.append("╔════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                   التقرير الشامل للمطعم                           ║\n");
        report.append("║                  Full Restaurant Report                            ║\n");
        report.append("╚════════════════════════════════════════════════════════════════════╝\n\n");
        
        report.append("التاريخ والوقت / Date & Time: ").append(LocalDateTime.now().format(dateTimeFormatter)).append("\n");
        report.append("═".repeat(70)).append("\n\n");

        // الإحصائيات الأساسية
        report.append("📊 الإحصائيات الأساسية / Basic Statistics:\n");
        report.append("─".repeat(50)).append("\n");
        report.append(String.format("عدد الطلبات / Total Orders: %d\n", orderService.getAllOrders().size()));
        report.append(String.format("عدد العملاء / Total Customers: %d\n", customerService.getAllCustomers().size()));
        report.append(String.format("عدد الموظفين / Total Employees: %d\n", employeeService.getAllEmployees().size()));
        report.append(String.format("عدد الوجبات / Total Meals: %d\n", mealService.getAllMeals().size()));
        report.append(String.format("عدد الفواتير / Total Bills: %d\n\n", billingService.getAllBills().size()));

        // الإيرادات
        double totalRevenue = 0;
        for (Bill bill : billingService.getAllBills()) {
            totalRevenue += bill.getTotal();
        }
        report.append(String.format("💰 إجمالي الإيرادات / Total Revenue: $%.2f\n\n", totalRevenue));

        report.append("═".repeat(70)).append("\n");
        report.append("تم إنشاء التقرير بواسطة نظام المطعم\n");
        report.append("Generated by Restaurant System\n");

        writeToFile(report.toString(), filename);
    }

    // ── كتابة التقرير في ملف ──
    private void writeToFile(String content, String filename) {
        try {
            String filepath = REPORT_DIR + filename;
            FileWriter writer = new FileWriter(filepath);
            writer.write(content);
            writer.close();
            System.out.println("✓ تم حفظ التقرير / Report saved: " + filepath);
        } catch (IOException e) {
            System.out.println("✗ خطأ في حفظ التقرير / Error saving report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ── طريقة مساعدة لإنشاء اسم ملف بالتاريخ الحالي ──
    public static String generateFilename(String reportType) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        return reportType + "_" + timestamp + ".txt";
    }
}
