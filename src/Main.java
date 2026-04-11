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

        // Start session management and access control
        if (currentUser != null) {
            AccessControl.startSession(currentUser);

            boolean running = true;
            while (running) {
                System.out.println("\n=== Car Rental Menu ===");
                System.out.println("1. Calculate Rental Cost");
                System.out.println("2. Update Car data");
                System.out.println("3. Logout");
                System.out.print("Select an option: ");

                String choice = input.nextLine();

                if (!choice.matches("[1-3]")) {
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
                    continue;
                }

                switch (choice) {
                    case "1":
                        if (AccessControl.isAuthorized("USER_TASK")) {
                           // System.out.println("Processing rental cost calculation...");
                            int passengers = -1;
                            int days = -1;
                            double mileage = -1;

                            while (passengers == -1) {
                                System.out.print("Enter number of passengers: ");
                                passengers = Validator.validatePassengers(input.nextLine());
                            }

                            while (days == -1) {
                                System.out.print("Enter rental days: ");
                                days = Validator.validateDays(input.nextLine());
                            }

                            while (mileage == -1) {
                                System.out.print("Enter estimated mileage: ");
                                mileage = Validator.validateMileage(input.nextLine());
                            }

                            System.out.println("Inputs accepted successfully!");
                        }
                        break;
                    case "2":
                        if (AccessControl.isAuthorized("ADMIN_TASK")) {
                            System.out.println("Processing car data update...");
                        }
                        break;
                    case "3":
                        AccessControl.logout();
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

                // Check session validity after each action
                if (!AccessControl.isSessionActive()) {
                    running = false;
                }
            }
        }
    }
}
