// Abstract Class
abstract class BaseVehicle {
    protected String engineType;

    public BaseVehicle(String engineType) {
        this.engineType = engineType;
    }

    // Abstract Method (Pure Virtual Function)
    public abstract void displayInfo();

    // Common Method
    public void startEngine() {
        System.out.println("Engine started for " + engineType + " vehicle.");
    }
}

// Vehicle Class
class Vehicle extends BaseVehicle {
    private static int totalVehicles = 0;
    private static int maxSpeedLimit = 120;
    private String type;
    private int speed;

    public Vehicle(String type, int speed, String engineType) {
        super(engineType);
        this.type = type;
        this.speed = Math.min(speed, maxSpeedLimit);
        totalVehicles++;
    }

    public Vehicle() {
        this("Unknown", 0, "Unknown");
    }

    // Overloaded
    public Vehicle accelerate(int increase) {
        this.speed = Math.min(this.speed + increase, maxSpeedLimit);
        System.out.println("New Speed: " + this.speed + " km/h");
        return this;
    }

    public Vehicle accelerate(String message) {
        System.out.println("Message: " + message);
        return this;
    }

    public Vehicle accelerate(int increase, String message) {
        this.accelerate(increase);
        System.out.println("Message: " + message);
        return this;
    }

    @Override
    public void displayInfo() {
        System.out.println("Vehicle Type: " + type);
        System.out.println("Speed: " + speed + " km/h");
        System.out.println("Engine Type: " + engineType);
        System.out.println("Max Speed Limit: " + maxSpeedLimit + " km/h");
        System.out.println("Total Vehicles: " + totalVehicles);
    }

    // Static Methods
    public static int getTotalVehicles() {
        return totalVehicles;
    }

    public static void setMaxSpeedLimit(int maxSpeed) {
        maxSpeedLimit = maxSpeed;
    }

    // Cleanup Method
    public void cleanup() {
        totalVehicles--;
        System.out.println("Vehicle of type " + type + " cleaned up. Total vehicles: " + totalVehicles);
    }
}

// ElectricVehicle Class
class ElectricVehicle extends Vehicle {
    private int batteryCapacity;

    public ElectricVehicle(String type, int speed, int batteryCapacity) {
        super(type, speed, "Electric");
        this.batteryCapacity = batteryCapacity;
    }

    public void chargeBattery() {
        System.out.println("Battery charged. Capacity: " + batteryCapacity + "%");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Battery Capacity: " + batteryCapacity + "%");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        BaseVehicle[] vehicles = new BaseVehicle[3];
        vehicles[0] = new Vehicle("Car", 60, "Petrol");
        vehicles[1] = new Vehicle();
        vehicles[2] = new ElectricVehicle("Electric Car", 80, 100);

        for (BaseVehicle vehicle : vehicles) {
            System.out.println("Simulating Vehicle:");
            vehicle.startEngine();
            vehicle.displayInfo();

            if (vehicle instanceof Vehicle) {
                ((Vehicle) vehicle).accelerate(20, "Speeding up!");
            }

            if (vehicle instanceof ElectricVehicle) {
                ((ElectricVehicle) vehicle).chargeBattery();
            }

            System.out.println();
        }

        // Cleanup Vehicles
        for (BaseVehicle vehicle : vehicles) {
            if (vehicle instanceof Vehicle) {
                ((Vehicle) vehicle).cleanup();
            }
        }
    }
}
