package Model;


public class User {

    private String role;
    private String username;
    private String telepon;
    private String password;

    public User(String role, String username, String telepon, String password) {
        this.role = role;
        this.username = username;
        this.telepon = telepon;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

}
