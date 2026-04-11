public abstract class Car {

    protected String make;
    protected String model;
    protected String category;
    protected int mpg;
    protected int maxPassengers;
    protected double dailyRate;
    protected String comfortLevel;

    public Car(String make, String model, String category,
               int mpg, int maxPassengers,
               double dailyRate, String comfortLevel) {

        this.make = make;
        this.model = model;
        this.category = category;
        this.mpg = mpg;
        this.maxPassengers = maxPassengers;
        this.dailyRate = dailyRate;
        this.comfortLevel = comfortLevel;
    }

    // Rental cost
    public double calculateRentalCost(int days) {
        return dailyRate * days;
    }

    // Gas cost
    public double calculateGasCost(double mileage) {
        double gallonsNeeded = mileage / mpg;
        return gallonsNeeded * 2.25;
    }

    // Total cost
    public double calculateTotalCost(int days, double mileage) {
        return calculateRentalCost(days) + calculateGasCost(mileage);
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getComfortLevel() {
        return comfortLevel;
    }

    public String getCarInfo() {
        return make + " " + model;
    }
}
