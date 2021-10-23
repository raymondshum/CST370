/**
 * Title: hw0_2.cpp
 * Abstract: This program reads five integer numbers from a user and
 *           displays the min, max and median values on screen.
 * Author: Raymond Shum
 * ID: 9030
 * Date: 10/23/2021
 */

#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    const int NUM_INPUTS = 5;
    int min, max, input;
    int median = 0;
    int inputList [NUM_INPUTS];

    for ( int i = 0; i < NUM_INPUTS; i++) {

        cin >> input;
        inputList[i] = input;
        if(i == 0) {
            min = max = input;
        } else {
            min = min < input ? min : input;
            max = max > input ? max : input;
        }
    }

    sort(begin(inputList), end(inputList));
    cout << "MIN:" << min << endl;
    cout << "MAX:" << max << endl;
    cout << "MEDIAN:" << inputList[2] << endl;

    return 0;
}