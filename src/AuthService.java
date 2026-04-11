/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * This class handles user authentication for the Car Rental Finder application.
 * Maintains the in-memory user database and verifies login credentials.
 *
 * Security Design Principles Applied:
 * 1. Defense in Depth:
 *    Passwords are never compared in plain text. The input password
 *    is hashed using SHA-256 via HashUtil before being compared
 *    against the stored hash, adding a layer of protection even
 *    if the user list is exposed.
 *
 * 2. Compartmentalization:
 *    Authentication logic is fully separated from the rest of the
 *    application. Main.java has no knowledge of how authentication
 *    works internally. it only receives a User object or null.
 *
 * 3. Fail-Safe Defaults:
 *    If credentials do not match, the method returns null.
 *    Access is denied by default unless an exact match is found.
 */
import java.util.ArrayList;

public class AuthService {

    private static ArrayList<User> users = new ArrayList<>();

    static {
        // أبسط تخزين (in-memory)
        users.add(new User("admin", "1234", "Admin"));
        users.add(new User("user", "1111", "User"));
    }

    public static User login(String username, String password) {

        String hashedPassword = HashUtil.hashPassword(password);

        for (User u : users) {
            if (u.getUsername().equals(username) &&
                u.getPasswordHash().equals(hashedPassword)) {
                return u;
            }
        }

        return null;
    }
}
