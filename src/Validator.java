/**
 * This class is a centralized input validation for the Car Rental Finder application.
 * All user inputs must pass through this class before any processing
 * or calculation is performed.
 *
 * Security Design Principles Applied:
 * 1. Minimize Trust Surface:
 *    No user input is trusted by default. Every value is parsed,
 *    checked for type correctness, and validated against acceptable
 *    ranges before being passed to any business logic.
 *    Invalid inputs are rejected immediately with a clear message.
 *
 * 2. Defense in Depth:
 *    Validation acts as the first security layer. Even if invalid
 *    data somehow bypasses the UI, this class ensures it is caught
 *    before reaching the RecommendationEngine or any calculation.
 */

public class Validator {

    // Validate passengers
    public static int validatePassengers(String input) {
        try {
            int passengers = Integer.parseInt(input);

            if (passengers <= 0) {
                System.out.println("Number of passengers must be greater than 0.");
                return -1;
            }

            return passengers;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }

    // Validate rental days
    public static int validateDays(String input) {
        try {
            int days = Integer.parseInt(input);

            if (days <= 0) {
                System.out.println("Rental days must be greater than 0.");
                return -1;
            }

            return days;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }

    // Validate mileage
    public static double validateMileage(String input) {
        try {
            double mileage = Double.parseDouble(input);

            if (mileage <= 0) {
                System.out.println("Mileage must be greater than 0.");
                return -1;
            }

            return mileage;

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }
}
