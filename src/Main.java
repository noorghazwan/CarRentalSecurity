/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Noor Safia
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int attempts = 0;
        User currentUser = null;

        System.out.println("=== Login System ===");

        // Login attempts (Fail-Safe Defaults)
        while (attempts < 3) {

            System.out.print("Username: ");
            String username = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            currentUser = AuthService.login(username, password);

            if (currentUser != null) {
                System.out.println("Login successful!");
                break;
            } else {
                attempts++;
                System.out.println("Invalid credentials. Attempts left: " + (3 - attempts));
            }
        }

        if (currentUser == null) {
            System.out.println("Access Denied. Too many attempts.");
            return;
        }

        System.out.println("Welcome " + currentUser.getUsername());
        System.out.println("Role: " + currentUser.getRole());
    }
}
