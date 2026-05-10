package core;

import entity.User;
import entity.Admin;
import entity.Employee;
import database.DBConnection; // استدعاء الاتصال بالداتا بيز

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AuthService {
    private ArrayList<User> users;
    private User currentUser;

    public AuthService() {
        this.users = new ArrayList<>();
        this.currentUser = null;
        
        // بمجرد تشغيل البرنامج، هنجلب كل المستخدمين من الداتا بيز ونحطهم في الـ ArrayList
        loadUsersFromDB();
    }

    // دالة جديدة لقراءة البيانات من قاعدة البيانات
    private void loadUsersFromDB() {
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String type = rs.getString("user_type");
                
                // إعادة بناء الكائنات بناءً على نوعها
                if ("Admin".equals(type)) {
                    users.add(new Admin(
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password")
                    ));
                } else if ("Employee".equals(type)) {
                    users.add(new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getDouble("salary")
                    ));
                }
            }
        } catch (Exception e) {
            System.out.println("Note: users table might be empty or missing.");
        }
    }

    public void registerUser(User user) {
        // حماية لمنع التكرار لو الـ Seeder اشتغل كذا مرة
        if (findUserByUsername(user.getUsername()) != null) {
            return; 
        }

        // كود الإضافة للداتا بيز (IGNORE عشان لو اليوزر موجود ميعملش إيرور)
        String sql = "INSERT OR IGNORE INTO users (name, phone, email, username, password, user_type, role, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());

            // تحديد نوع المستخدم عشان نعرف نحفظ بياناته الزيادة
            if (user instanceof Admin) {
                pstmt.setString(6, "Admin");
                pstmt.setString(7, "Manager"); // Admin doesn't have a specific role in your seeder
                pstmt.setDouble(8, 0.0);
            } else if (user instanceof Employee) {
                Employee emp = (Employee) user;
                pstmt.setString(6, "Employee");
                pstmt.setString(7, emp.getRole());
                pstmt.setDouble(8, emp.getSalary());
            } else {
                pstmt.setString(6, "User");
                pstmt.setString(7, "");
                pstmt.setDouble(8, 0.0);
            }

            pstmt.executeUpdate();
            System.out.println("User registered in DB: " + user.getUsername());

        } catch (Exception e) {
            System.out.println("Error saving user to DB: " + e.getMessage());
        }

        // إضافته للذاكرة عشان يشتغل في نفس الجلسة بدون إعادة تحميل
        users.add(user);
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.login(username, password)) {
                currentUser = user;
                System.out.println("Welcome, " + user.getName() + "!");
                return user;
            }
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public void logout() {
        if (currentUser != null) {
            currentUser.logout();
            currentUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public User getCurrentUser() { return currentUser; }

    public boolean isLoggedIn() { return currentUser != null; }

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getUsers() { return users; }
}