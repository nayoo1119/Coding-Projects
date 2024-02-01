// Homework6B.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include<iostream>
#include<iomanip>
using namespace std;

class Inventory
{
public:
    Inventory();
    Inventory(int, int, double);
    void I(int);
    void Q(int);
    void setCost(double);
    int getItemNumber();
    int getQuantity();
    double getCost();
    double getTotalCost();
private:
    int itemNumber;
    int quantity;
    double cost;
};
void printInventory(Inventory);
Inventory::Inventory()
{
    itemNumber = 0;
    quantity = 0;
    cost = 0;
}
Inventory::Inventory(int _ItemNumber, int _quantity, double _cost)
{
    itemNumber = _ItemNumber;
    cost = _cost;
    quantity = _quantity;
}
void Inventory::I(int _ItemNumber)
{
    itemNumber = _ItemNumber;
}
void Inventory::Q(int _quantity)
{
    quantity = _quantity;
}
void Inventory::setCost(double _cost)
{
    cost = _cost;
}
int Inventory::getItemNumber()
{
    return itemNumber;
}
int Inventory::getQuantity()
{
    return quantity;
}
double Inventory::getCost()
{
    return cost;
}
double Inventory::getTotalCost()
{
    return cost * quantity;
}

int main()
{
    int number, Qun;
    double cost;
    cout << "Enter data for the new item " << endl;
    cout << "Item number: ";
    cin >> number;
    while (number < 0)
    {
        cout << "Value must be greater than zero: "<< endl;
        cin >> number;
    }
    cout << "Quantity: ";
    cin >> Qun;
    while (Qun < 0)
    {
        cout << "Value must be greater than zero: "<<endl;
        cin >> Qun;
    }
    cout << "Price: ";
    cin >> cost;
    while (cost < 0)
    {
        cout << "Value must be greater than zero: "<< endl;
        cin >> cost;
    }
    Inventory item1(number, Qun, cost);
    Inventory item2;
    printInventory(item2);
    item2.I(1234);
    item2.Q(10);
    item2.setCost(2.5);
    printInventory(item2);
    printInventory(item1);
    return 0;
}

void printInventory(Inventory item)
{
    cout << fixed << setprecision(2);
    cout << "Part Number : " << item.getItemNumber() << endl;
    cout << "Units On Hand : " << item.getQuantity() << endl;
    cout << "Price : $" << item.getCost() << endl;
    cout << "Total Cost : $" << item.getTotalCost()<<endl;
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
