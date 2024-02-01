// Homework7A.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include<iostream>
#include<string>

using namespace std;

struct CorpData {
    string name;
    int Corp1;
    int Corp2;
    int Corp3;
    int Corp4;
    CorpData(string s = "", int corp1 = 0, int corp2 = 0, int corp3 = 0, int corp4 = 0) {
        name = s;
        Corp1 = corp1;
        Corp2 = corp2;
        Corp3 = corp3;
        Corp4 = corp4;
    }
};

void calculate(CorpData c) {

    int total = c.Corp1 + c.Corp2 + c.Corp3 + c.Corp4;
    double avg = total / 4.0;
    cout << "Division name:   " << c.name << endl;
    cout << "Annual Total:    $" << total << endl;
    cout << "Annual Quarterly:     $" << avg << endl;
}

int main() {

    CorpData corpWest("West", 10000, 20000, 30000, 40000),
        corpNorth("North", 50000, 60000, 70000, 80000),
        corpEast("East", 90000, 100000, 110000),
        corpSouth("South", 120000, 130000, 140000, 150000);

    calculate(corpWest);
    calculate(corpEast);
    calculate(corpNorth);
    calculate(corpSouth);
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
