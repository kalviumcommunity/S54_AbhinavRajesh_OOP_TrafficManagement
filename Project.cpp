#include <iostream>
#include <string>
using namespace std;

// Vehicle Class
class Vehicle {
public:
    // Constructor with This Pointer
    Vehicle(string type = "Unknown", int speed = 0) {
        this->type = type;
        this->speed = speed;
    }

    // Member function
    Vehicle* accelerate(int increase) {
        this->speed += increase;
        cout << "New Speed: " << this->speed << " km/h" << endl;
        return this;
    }

    void displayInfo() {
        cout << "Vehicle Type: " << this->type << endl;
        cout << "Speed: " << this->speed << " km/h" << endl;
    }

private:
    string type;
    int speed; 
};

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
    // Array of Vehicle objects
    Vehicle vehicles[3] = {
        Vehicle("Car", 60),
        Vehicle("Bus", 50),
        Vehicle("Motorcycle", 80)
    };

    // TrafficLight object
    TrafficLight streetLight("Red");

    for (int i = 0; i < 3; ++i) {
        cout << "Simulating Vehicle " << (i + 1) << ":" << endl;
        Simulation citySimulation(&vehicles[i], &streetLight);
        citySimulation.run();
        cout << endl;
    }

    return 0;
}
