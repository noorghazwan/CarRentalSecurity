/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Noor Safia
 */
public class User {
      private String username;
    private String passwordHash;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.passwordHash = HashUtil.hashPassword(password);
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }
}
