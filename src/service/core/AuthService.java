package service.core;

import entity.User;
import entity.Admin;
import entity.Employee;
import java.util.ArrayList;

public class AuthService {
    private ArrayList<User> users;
    private User currentUser;

    public AuthService() {
        this.users = new ArrayList<>();
        this.currentUser = null;
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("User registered: " + user.getUsername());
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
