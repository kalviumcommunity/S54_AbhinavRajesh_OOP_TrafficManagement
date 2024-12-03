// ISP: Chargeable Interface
interface Chargeable {
    void chargeBattery();
}

// DIP: Engine Interface
interface Engine {
    String getEngineType();
}

class PetrolEngine implements Engine {
    @Override
    public String getEngineType() {
        return "Petrol";
    }
}

class ElectricEngine implements Engine {
    @Override
    public String getEngineType() {
        return "Electric";
    }
}

// Abstract Base Class
abstract class BaseVehicle {
    protected Engine engine;

    public BaseVehicle(Engine engine) {
        this.engine = engine;
    }

    public abstract void displayInfo();

    public void startEngine() {
        System.out.println("Engine started for " + engine.getEngineType() + " vehicle.");
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

    public Vehicle(String type, int speed, Engine engine) {
        super(engine);
        this.info = new VehicleInfo(type, Math.min(speed, MAX_SPEED_LIMIT));
        totalVehicles++;
    }

    public Vehicle() {
        this("Unknown", 0, new PetrolEngine());
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
        System.out.println("Engine Type: " + engine.getEngineType());
        System.out.println("Max Speed Limit: " + MAX_SPEED_LIMIT + " km/h");
        System.out.println("Total Vehicles: " + totalVehicles);
    }

    public void cleanup() {
        totalVehicles--;
        System.out.println("Vehicle of type " + info.getType() + " cleaned up. Total vehicles: " + totalVehicles);
    }
}

// ElectricVehicle Class Extending Vehicle and Implementing Chargeable
class ElectricVehicle extends Vehicle implements Chargeable {
    private int batteryCapacity;

    public ElectricVehicle(String type, int speed, int batteryCapacity) {
        super(type, speed, new ElectricEngine());
        this.batteryCapacity = batteryCapacity;
    }

    @Override
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

        if (vehicle instanceof Chargeable) {
            ((Chargeable) vehicle).chargeBattery();
        }

        System.out.println();
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        BaseVehicle[] vehicles = new BaseVehicle[3];
        vehicles[0] = new Vehicle("Car", 60, new PetrolEngine());
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
