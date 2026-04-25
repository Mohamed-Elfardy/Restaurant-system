package entity;

import helper.IdGenerator;

public class Admin extends User {

    public Admin(String name, String phone, String email, String username, String password) {
        super(IdGenerator.generatePersonId(), name, phone, email, username, password);
    }

    public void changeUsername(String newUsername) {
        setUsername(newUsername);
        System.out.println("Username updated successfully.");
    }

    public void changePassword(String newPassword) {
        setPassword(newPassword);
        System.out.println("Password updated successfully.");
    }

    @Override
    public String toString() {
        return "[Admin] " + super.toString();
    }
}