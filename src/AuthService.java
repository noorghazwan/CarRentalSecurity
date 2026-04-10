/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Noor Safia
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
