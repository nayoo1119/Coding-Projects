// Homework6A.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <string>
using namespace std;
class Car
{
private:
    int yearModel;
    string make;
    int speed;
public:
    Car(int x, string y)
    {
        yearModel = x; make = y; speed = 0;
    }
    int getYearModel()
    {
        return yearModel;
    }
    string getMake()
    {
        return make;
    }
    int getSpeed()
    {
        return speed;
    }
    void accelerate()
    {
        speed += 5;
    }
    void brake()
    {
        speed -= 5;
    }
};
int main()
{
    int count;
    Car FordMustang(1967, "Ford Mustang");
    for (int i = 0; i < 5; i++)
    {
        FordMustang.accelerate();
        cout << FordMustang.getYearModel() << " " << FordMustang.getMake() << " - " << "current speed : " << FordMustang.getSpeed()<<endl;
    }
    for (int i = 0; i < 5; i++)
    {
        FordMustang.brake();
 
        cout << FordMustang.getYearModel() << " " << FordMustang.getMake() << " - " << "current speed : " << FordMustang.getSpeed() << endl;
    }
    return 0;
}

// Run program: Ctrl + F5 or Debug > Start Without Debugging menu
// Debug program: F5 or Debug > Start Debugging menu

// Tips for Getting Started: 
//   1. Use the Solution Explorer window to add/manage files
//   2. Use the Team Explorer window to connect to source control
//   3. Use the Output window to see build output and other messages
//   4. Use the Error List window to view errors
//   5. Go to Project > Add New Item to create new code files, or Project > Add Existing Item to add existing code files to the project
//   6. In the future, to open this project again, go to File > Open > Project and select the .sln file
