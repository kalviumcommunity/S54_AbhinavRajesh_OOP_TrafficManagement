#include <iostream>
#include <string>
using namespace std;

// Vehicle Class
class Vehicle {
public:
    // Constructor with This Pointer
    Vehicle(string type, int speed) {
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
    TrafficLight(string initialColor) {
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

    // Vehicle and TrafficLight objects
    Vehicle car("Car", 60);
    TrafficLight streetLight("Red");

    // Simulation object
    Simulation citySimulation(&car, &streetLight);
    
    // Run
    citySimulation.run();

    return 0;
}
