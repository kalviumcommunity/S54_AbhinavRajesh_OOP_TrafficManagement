// Abstract Base Class
abstract class BaseVehicle {
    protected String engineType;

    public BaseVehicle(String engineType) {
        this.engineType = engineType;
    }

    public abstract void displayInfo();

    public void startEngine() {
        System.out.println("Engine started for " + engineType + " vehicle.");
    }
}

// Separate Class for Vehicle Information (SRP)
class VehicleInfo {
    private String type;
    private int speed;

    public VehicleInfo(String type, int speed) {
        this.type = type;
        this.speed = speed;
    }

    public void displayInfo() {
        System.out.println("Vehicle Type: " + type);
        System.out.println("Speed: " + speed + " km/h");
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

// Refactored Vehicle Class for OCP
class Vehicle extends BaseVehicle {
    private static int totalVehicles = 0;
    private static final int MAX_SPEED_LIMIT = 120;
    private VehicleInfo info;

    public Vehicle(String type, int speed, String engineType) {
        super(engineType);
        this.info = new VehicleInfo(type, Math.min(speed, MAX_SPEED_LIMIT));
        totalVehicles++;
    }

    public Vehicle() {
        this("Unknown", 0, "Unknown");
    }

    public void accelerate(int increase, String message) {
        int newSpeed = Math.min(info.getSpeed() + increase, MAX_SPEED_LIMIT);
        info.setSpeed(newSpeed);
        System.out.println("New Speed: " + newSpeed + " km/h");
        System.out.println("Message: " + message);
    }

    @Override
    public void displayInfo() {
        info.displayInfo();
        System.out.println("Engine Type: " + engineType);
        System.out.println("Max Speed Limit: " + MAX_SPEED_LIMIT + " km/h");
        System.out.println("Total Vehicles: " + totalVehicles);
    }

    public void cleanup() {
        totalVehicles--;
        System.out.println("Vehicle of type " + info.getType() + " cleaned up. Total vehicles: " + totalVehicles);
    }
}

// ElectricVehicle Class Extending Vehicle
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

// Utility Class to Simulate Vehicles (LSP)
class VehicleSimulator {
    public static void simulate(BaseVehicle vehicle) {
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
}

// Main Class
public class Main {
    public static void main(String[] args) {
        BaseVehicle[] vehicles = new BaseVehicle[3];
        vehicles[0] = new Vehicle("Car", 60, "Petrol");
        vehicles[1] = new Vehicle();
        vehicles[2] = new ElectricVehicle("Electric Car", 80, 100);

        for (BaseVehicle vehicle : vehicles) {
            VehicleSimulator.simulate(vehicle);
        }

        // Cleanup Vehicles
        for (BaseVehicle vehicle : vehicles) {
            if (vehicle instanceof Vehicle) {
                ((Vehicle) vehicle).cleanup();
            }
        }
    }
}
