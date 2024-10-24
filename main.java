import java.util.*;

// Vehicle Class
class Vehicle {
    // Static variable
    private static int totalVehicles = 0;
    
    private static int maxSpeedLimit = 120;

    // Private members
    private String type;
    private int speed;

    // Constructor
    public Vehicle(String type, int speed) {
        this.setType(type); 
        this.setSpeed(speed);  
        totalVehicles++;
    }

    // Default constructor
    public Vehicle() {
        this("Unknown", 0);
    }

    // Manual cleanup method
    public void cleanup() {
        totalVehicles--;
        System.out.println("Vehicle of type " + this.type + " cleaned up. Total vehicles: " + totalVehicles);
    }

    // Getter
    public static int getTotalVehicles() {
        return totalVehicles;
    }

    // Getter
    public String getType() {
        return type;
    }

    // Setter
    public void setType(String type) {
        if (type != null && !type.isEmpty()) {
            this.type = type;
        } else {
            this.type = "Unknown";
        }
    }

    // Getter
    public int getSpeed() {
        return speed;
    }

    // Setter
    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= maxSpeedLimit) {
            this.speed = speed;
        } else {
            this.speed = maxSpeedLimit;
        }
    }

    // Member function
    public Vehicle accelerate(int increase) {
        this.setSpeed(this.speed + increase);
        System.out.println("New Speed: " + this.speed + " km/h");
        return this;
    }

    public void displayInfo() {
        System.out.println("Vehicle Type: " + this.getType());
        System.out.println("Speed: " + this.getSpeed() + " km/h");
        System.out.println("Max Speed Limit: " + maxSpeedLimit + " km/h");
        System.out.println("Total Vehicles: " + getTotalVehicles());
    }

    // Setter
    public static void setMaxSpeedLimit(int maxSpeed) {
        maxSpeedLimit = maxSpeed;
    }

    // Getter
    public static int getMaxSpeedLimit() {
        return maxSpeedLimit;
    }
}

// TrafficLight Class
class TrafficLight {
    // Private member
    private String color;

    // Constructor
    public TrafficLight(String initialColor) {
        this.setColor(initialColor);
    }

    // Default constructor
    public TrafficLight() {
        this("Red");
    }

    // Getter
    public String getColor() {
        return color;
    }

    // Setter
    public void setColor(String color) {
        if (color.equals("Red") || color.equals("Green") || color.equals("Yellow")) {
            this.color = color;
        } else {
            this.color = "Red";
        }
    }

    // Member function
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

    // Constructor
    public Simulation(Vehicle vehicle, TrafficLight trafficLight) {
        this.vehicle = vehicle;
        this.trafficLight = trafficLight;
    }

    // Member function
    public void run() {
        System.out.println("Simulation Started:");
        vehicle.displayInfo();
        trafficLight.displayStatus();

        trafficLight.changeColor("Green");
        vehicle.accelerate(20);
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[3];
        vehicles[0] = new Vehicle("Car", 60);
        vehicles[1] = new Vehicle();
        vehicles[2] = new Vehicle("Motorcycle", 80);

        // Run simulation
        for (int i = 0; i < vehicles.length; i++) {
            System.out.println("Simulating Vehicle " + (i + 1) + ":");

            TrafficLight streetLight = new TrafficLight("Red");
            
            Simulation citySimulation = new Simulation(vehicles[i], streetLight);
            citySimulation.run();
            System.out.println();
        }

        // Manual Cleanup
        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i].cleanup();
        }
    }
}