// CS939Homework4B.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>
#include <iomanip>
using namespace std;


int main()
{
	ifstream input;
	input.open("Rainfall.txt");

	string name, month1, month2;
	double value, total = 0, average, counter = 0;
	int x = 1;

	input >> month1;

	input >> month2;
	

	cout << fixed << setprecision(2);

	while (input >> value)
	{
		total += value;
		counter++;
	}


	average = total / counter;
	cout << "During the months of " << month1 << "-" << month2 << " the total rainfall was " << total << " inches and the average monthly rainfall was " << average <<" inches."<< endl;


	input.close();




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
