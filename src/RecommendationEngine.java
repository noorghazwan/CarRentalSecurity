import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is the core logic of the Car Rental Finder application.
 * Filters, sorts, and displays the most suitable cars
 * based on the user's trip requirements.
 *
 * Security Design Principles Applied:
 * 1. Fail-Safe Defaults:
 *    If no car meets the passenger requirement, the engine
 *    returns nothing and displays a denial message. The system
 *    never recommends a car that does not fully satisfy the
 *    requested criteria.
 *
 * 2. Compartmentalization:
 *    Recommendation logic is fully isolated from authentication,
 *    validation, and display concerns. This class only receives
 *    already-validated inputs and focuses solely on filtering
 *    and ranking results.
 */

public class RecommendationEngine {

    public static void recommend(int passengers, int days, double mileage) {

        ArrayList<Car> allCars = CarDatabase.getAllCars();
        ArrayList<Car> suitable = new ArrayList<>();

        // Step 1: Filter by passenger capacity
        for (Car car : allCars) {
            if (car.getMaxPassengers() >= passengers) {
                suitable.add(car);
            }
        }

        // Step 2: Fail-Safe — if no car qualifies, deny
        if (suitable.isEmpty()) {
            System.out.println("No available cars for " + passengers + " passengers.");
            return;
        }

        // Step 3: Sort by total cost, then comfort level
        Collections.sort(suitable, (a, b) -> {
            double costA = a.calculateTotalCost(days, mileage);
            double costB = b.calculateTotalCost(days, mileage);

            if (costA != costB) {
                return Double.compare(costA, costB);
            }

            // If same cost, prefer better comfort
            return comfortRank(b.getComfortLevel()) - comfortRank(a.getComfortLevel());
        });

        // Step 4: Display results
        System.out.println("\n=== Recommended Cars ===");
        System.out.printf("%-25s %-10s %-10s %-10s %-12s%n",
                "Car", "Comfort", "Rental", "Gas", "Total");
        System.out.println("-".repeat(70));

        for (Car car : suitable) {
            double rental = car.calculateRentalCost(days);
            double gas    = car.calculateGasCost(mileage);
            double total  = car.calculateTotalCost(days, mileage);

            System.out.printf("%-25s %-10s $%-9.2f $%-9.2f $%-10.2f%n",
                    car.getCarInfo(),
                    car.getComfortLevel(),
                    rental, gas, total);
        }
    }

    private static int comfortRank(String level) {
        return switch (level) {
            case "Good" -> 3;
            case "Medium" -> 2;
            case "Poor" -> 1;
            default -> 0;
        };
    }
}