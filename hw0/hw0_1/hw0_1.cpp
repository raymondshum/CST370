/**
 * Title: hw0_1.cpp
 * Abstract: This program calculates the sum and difference of two numbers. The difference
 *           will always be 0 or positive.
 * Author: Raymond Shum
 * ID: 9030
 * Date: 10/23/2021
 */

#include <iostream>
using namespace std;

int main() {
    int num1, num2, calculation;

    cin >> num1 >> num2;

    int sum = num1 + num2;
    int diff = abs(num1-num2);

    cout << "SUM:" << sum << endl;
    cout << "DIFF:" << diff << endl;
    
    return 0;
}