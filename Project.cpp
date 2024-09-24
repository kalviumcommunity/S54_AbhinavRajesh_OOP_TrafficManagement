#include <iostream>
#include <string>
using namespace std;

// Vehicle Class
class Vehicle {
public:
    // Constructor
    Vehicle(string vType, int vSpeed) : type(vType), speed(vSpeed) {}

    // Member function
    void displayInfo() {
        cout << "Vehicle Type: " << type << endl;
        cout << "Speed: " << speed << " km/h" << endl;
    }

    void accelerate(int increase) {
        speed += increase;
        cout << "New Speed: " << speed << " km/h" << endl;
    }

private:
    string type;
    int speed; 
};

// TrafficLight Class
class TrafficLight {
public:
    // Constructor
    TrafficLight(string initialColor) : color(initialColor) {}

    // Member function
    void displayStatus() {
        cout << "Traffic Light Color: " << color << endl;
    }       

    void changeColor(string newColor) {
        color = newColor;
        cout << "New Traffic Light Color: " << color << endl;
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


int main() {

    Vehicle car("Car", 60);
    TrafficLight streetLight("Red");

    Simulation citySimulation(&car, &streetLight);
    
    citySimulation.run();

    return 0;
}
