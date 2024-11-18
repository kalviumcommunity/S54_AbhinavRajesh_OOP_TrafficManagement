import java.util.*;

// MotorVehicle Class
class MotorVehicle {
    protected String engineType;

    public MotorVehicle(String engineType) {
        this.engineType = engineType;
    }

    public void startEngine() {
        System.out.println("Engine started for " + engineType + " vehicle.");
    }
}

// Vehicle Class
class Vehicle extends MotorVehicle {
    // Static variable
    private static int totalVehicles = 0;
    private static int maxSpeedLimit = 120;

    // Private members
    private String type;
    private int speed;

    // Constructor
    public Vehicle(String type, int speed, String engineType) {
        super(engineType);
        this.setType(type);
        this.setSpeed(speed);
        totalVehicles++;
    }

    // Default constructor
    public Vehicle() {
        this("Unknown", 0, "Unknown");
    }

    // Method Overloading for accelerate
    public void accelerate(int increase) {
        this.setSpeed(this.speed + increase);
        System.out.println("New Speed: " + this.speed + " km/h");
    }

    // Overloaded accelerate method (string message)
    public void accelerate(String message) {
        System.out.println("Message: " + message);
    }

    // Accelerate with both integer and string parameters
    public void accelerate(int increase, String message) {
        this.setSpeed(this.speed + increase);
        System.out.println("New Speed: " + this.speed + " km/h, " + message);
    }

    // Manual cleanup method
    public void cleanup() {
        totalVehicles--;
        System.out.println("Vehicle of type " + this.type + " cleaned up. Total vehicles: " + totalVehicles);
    }

    // Static Getter
    public static int getTotalVehicles() {
        return totalVehicles;
    }

    // Getter and Setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null && !type.isEmpty()) {
            this.type = type;
        } else {
            this.type = "Unknown";
        }
    }

    // Getter and Setter
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= maxSpeedLimit) {
            this.speed = speed;
        } else {
            this.speed = maxSpeedLimit;
        }
    }

    // Display Info method
    public void displayInfo() {
        System.out.println("Vehicle Type: " + this.getType());
        System.out.println("Speed: " + this.getSpeed() + " km/h");
        System.out.println("Engine Type: " + this.engineType);
        System.out.println("Max Speed Limit: " + maxSpeedLimit + " km/h");
        System.out.println("Total Vehicles: " + getTotalVehicles());
    }

    // Static Setter and Getter
    public static void setMaxSpeedLimit(int maxSpeed) {
        maxSpeedLimit = maxSpeed;
    }

    public static int getMaxSpeedLimit() {
        return maxSpeedLimit;
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

// TrafficLight Class
class TrafficLight {
    private String color;

    public TrafficLight(String initialColor) {
        this.setColor(initialColor);
    }

    public TrafficLight() {
        this("Red");
    }

    // Getter and Setter
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color.equals("Red") || color.equals("Green") || color.equals("Yellow")) {
            this.color = color;
        } else {
            this.color = "Red";
        }
    }

    public void displayStatus() {
        System.out.println("Traffic Light Color: " + this.getColor());
    }

    public TrafficLight changeColor(String newColor) {
        this.setColor(newColor);
        System.out.println("New Traffic Light Color: " + this.getColor());
        return this;
    }
}

// Simulation Class
class Simulation {
    private Vehicle vehicle;
    private TrafficLight trafficLight;

    public Simulation(Vehicle vehicle, TrafficLight trafficLight) {
        this.vehicle = vehicle;
        this.trafficLight = trafficLight;
    }

    public void run() {
        System.out.println("Simulation Started:");
        vehicle.displayInfo();
        trafficLight.displayStatus();

        trafficLight.changeColor("Green");
        
        // Calling the overloaded methods
        vehicle.accelerate(20);
        vehicle.accelerate("Speeding up!");
        vehicle.accelerate(30, "Accelerating more quickly!");
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Vehicle("Car", 60, "Petrol");
        vehicles[1] = new Vehicle();
        vehicles[2] = new ElectricVehicle("Electric Car", 80, 100);

        for (int i = 0; i < vehicles.length; i++) {
            System.out.println("Simulating Vehicle " + (i + 1) + ":");

            TrafficLight streetLight = new TrafficLight("Red");
            Simulation citySimulation = new Simulation(vehicles[i], streetLight);
            citySimulation.run();

            if (vehicles[i] instanceof ElectricVehicle) {
                ((ElectricVehicle) vehicles[i]).chargeBattery();
            }
            System.out.println();
        }

        // Manual Cleanup
        for (Vehicle vehicle : vehicles) {
            vehicle.cleanup();
        }
    }
}
