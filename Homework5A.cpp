// Homework5A.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

// This program has three functions: main, first, and second.
#include <iostream>
#include <algorithm>
using namespace std;
void getJudgeData(double& judgescore);
double calcScore(double score1, double score2, double score3, double score4, double score5);
double findLowest(double score1, double score2, double score3, double score4, double score5);
double findHighest(double score1, double score2, double score3, double score4, double score5);
int main()
{
	double score1, score2, score3, score4, score5;
	getJudgeData(score1);
	getJudgeData(score2);
	getJudgeData(score3);
	getJudgeData(score4);
	getJudgeData(score5);
	cout << endl;
	cout << "Final Score : " << calcScore(score1, score2, score3, score4, score5)<<endl;
	return 0;
}
void getJudgeData(double& judgescore)
{
	static int i = 0;
	cout << "Judge #" << i + 1 << " - Please enter a score between 0.0 and 10.0 : ";
	cin >> judgescore;
	while (judgescore < 0 || judgescore > 10)
	{
		cout << "Score must be between 0.0 and 10.0 : ";
			cin >> judgescore;
	}
	++i;
}
double calcScore(double score1, double score2, double score3, double score4, double score5)
{
	return (score1 + score2 + score3 + score4 + score5-findLowest(score1, score2, score3, score4, score5)-findHighest(score1, score2, score3, score4, score5)) / 3;
}
double findLowest(double score1, double score2, double score3, double score4, double score5)
{
	return min({ score1, score2, score3, score4, score5 });
}
double findHighest(double score1, double score2, double score3, double score4, double score5)
{
	return max(
		{ score1, score2, score3, score4, score5 });
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
