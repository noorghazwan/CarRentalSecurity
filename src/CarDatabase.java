import java.util.ArrayList;

/**
 * This class stores the complete fleet of 12 rental cars selected by the group.
 * Acts as the central in-memory data source for the application.
 *
 * Fleet organized as follows:
 *   Cars  1-3  : Dimah Omar Aloufi
 *   Cars  4-6  : Lena Salah Alsharif
 *   Cars  7-9  : Noor Alhuda Ghazwan Safia
 *   Cars 10-12 : Hajar Maher Qutb
 */

public class CarDatabase {

    private static final ArrayList<Car> cars = new ArrayList<>();

    static {
        // Dimah Omar Aloufi — Cars 1–3
        cars.add(new IntermediateCar("Toyota", "Corolla 2023", 35));
        cars.add(new IntermediateCar("Ford", "Escape 2025", 39));
        cars.add(new EconomyCar("Chevrolet", "Camaro 2024", 20));

        // Lena Salah Alsharif — Cars 4–6
        cars.add(new StandardCar("Chevrolet", "Colorado 2023", 20));
        cars.add(new StandardCar("Kia", "Sportage 2025", 28));
        cars.add(new VanCar("Toyota", "Sienna 2024", 36));

        // Noor Alhuda Ghazwan Safia — Cars 7–9
        cars.add(new StandardCar("Mazda", "CX-70 2025", 25));
        cars.add(new IntermediateCar("Hyundai", "Tucson 2026", 38));
        cars.add(new IntermediateCar("Audi", "A3 2023", 32));

        // Hajar Maher Qutb — Cars 10–12
        cars.add(new StandardCar("Ram", "1500 Crew Cab 2025", 21));
        cars.add(new EconomyCar("Honda", "Prelude 2026", 44));
        cars.add(new StandardCar("BMW", "X1 2024", 28));
    }

    public static ArrayList<Car> getAllCars() {
        return cars;
    }
}