/**
 * Title: hw1_1.cpp
 * Abstract: This program reads input numbers from a user and displays the
 *           closest distance between two numbers within the list.
 * Author: Raymond Shum
 * ID: 9030
 * Date: 11/2/2021
 */

#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

int main()
{
    int min = INT_MAX; // Min must be replaced on first iteration of loop
    int numElements = 0;

    // Initialize new array with value of first input
    cin >> numElements;
    int inputNums[numElements];

    // Populate array with expected number of elements
    for (int i = 0; i < numElements; i++)
    {
        cin >> inputNums[i];
    }

    // Sort array in ascending order
    sort(inputNums, inputNums + numElements);

    // Calculate minimum distance between two elements in the array
    for (int i = 0; i < numElements - 1; i++)
    {
        int j = i + 1;
        int distance = abs(inputNums[j] - inputNums[i]); // always positive

        if (distance < min)
        {
            min = distance;
        }
    }

    cout << "Min Distance:" << min << endl;

    // Print all pairs at min distance
    for (int i = 0; i < numElements - 1; i++)
    {
        int j = i + 1;
        int distance = abs(inputNums[j] - inputNums[i]);
        if (distance == min)
        {
            cout << "Pair:" << inputNums[i] << " " << inputNums[j] << endl;
        }
    }

    return 0;
}