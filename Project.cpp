#include <iostream>
#include <string>
using namespace std;

// Vehicle Class
class Vehicle {
public:
    // Static variable to track total number of vehicles
    static int totalVehicles;

    // Static variable for maximum speed limit
    static int maxSpeedLimit;

    // Constructor with This Pointer
    Vehicle(string type = "Unknown", int speed = 0) {
        this->type = type;
        this->speed = speed;
        totalVehicles++;
    }

    // Destructor
    ~Vehicle() {
        totalVehicles--;
    }

    // Member function
    Vehicle* accelerate(int increase) {
        this->speed += increase;
        if (this->speed > maxSpeedLimit) {
            this->speed = maxSpeedLimit;
        }
        cout << "New Speed: " << this->speed << " km/h" << endl;
        return this;
    }

    void displayInfo() {
        cout << "Vehicle Type: " << this->type << endl;
        cout << "Speed: " << this->speed << " km/h" << endl;
        cout << "Max Speed Limit: " << maxSpeedLimit << " km/h" << endl;
        cout << "Total Vehicles: " << totalVehicles << endl;
    }

private:
    string type;
    int speed; 
};

// Initialize static variables
int Vehicle::totalVehicles = 0;
int Vehicle::maxSpeedLimit = 120;

// TrafficLight Class
class TrafficLight {
public:
    // Constructor with This Pointer
    TrafficLight(string initialColor = "Red") {
        this->color = initialColor;
    }

    // Member function
    void displayStatus() {
        cout << "Traffic Light Color: " << this->color << endl;
    }       

    TrafficLight* changeColor(string newColor) {
        this->color = newColor;
        cout << "New Traffic Light Color: " << this->color << endl;
        return this;
    }

private:
    string color;
};

// Simulation Class
class Simulation {
public:
    // Constructor
    Simulation(Vehicle* v, TrafficLight* t) : vehicle(v), trafficLight(t) {}

    // Member function
    void run() {
        cout << "Simulation Started:" << endl;
        vehicle->displayInfo();
        trafficLight->displayStatus();
        
        trafficLight->changeColor("Green");
        vehicle->accelerate(20);
    }

private:
    Vehicle* vehicle;        
    TrafficLight* trafficLight; 
};

// Main function
int main() {
    // Dynamically allocate an array of Vehicle objects
    Vehicle* vehicles[3];
    vehicles[0] = new Vehicle("Car", 60);
    vehicles[1] = new Vehicle("Bus", 50);
    vehicles[2] = new Vehicle("Motorcycle", 80);

    // Dynamically allocate a TrafficLight object
    TrafficLight* streetLight = new TrafficLight("Red");

    // run the simulation
    for (int i = 0; i < 3; ++i) {
        cout << "Simulating Vehicle " << (i + 1) << ":" << endl;
        Simulation* citySimulation = new Simulation(vehicles[i], streetLight);
        citySimulation->run();
        delete citySimulation;
        cout << endl;
    }

    // Clean up
    for (int i = 0; i < 3; ++i) {
        delete vehicles[i];
    }
    delete streetLight;

    return 0;
}
