package entity;

public abstract class User extends Person {
    private String username;
    private String password;

    public User(int id, String name, String phone, String email, String username, String password) {
        super(id, name, phone, email);
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println(getName() + " logged out successfully.");
    }

    public void updateInfo(String name, String phone, String email) {
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    @Override
    public String toString() {
        return super.toString() + " | Username: " + username;
    }
}