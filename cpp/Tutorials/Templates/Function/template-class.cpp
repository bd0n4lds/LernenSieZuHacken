#include <iostream>
#include <string>

#include "stack.h"

constexpr int nums[] = {1, 2, 3, 4, 5};
constexpr const char *strs[] = {"one", "two", "three", "four", "five"};

int main(int argc, char **argv)
{
    try
    {
        Stack<int> si(5);

        std::cout << "si size: " << si.size();
        cout << "si top: " << si.top();

        for (int i : nums)
        {
            si.push(i);
        }

        cout << "si top after pushes: " << si.top();
        cout << "si is " << (si.isFull() ? "" : "not ") << "full";

        while (!si.isEmpty())
        {
            cout << "popped " << si.pop();
        }
    }
    catch (StackExeption &e)
    {
        cout << "Stack error: " << e.what();
    }

    try
    {
        Stack<string> ss(5);

        cout << "ss size: " << ss.size();
        cout << "ss top: " << ss.top();

        for (const char *s : strs)
        {
            ss.push(s);
        }

        cout << "ss top after pushes: " << ss.top();
        cout << "ss is " << (ss.isFull() ? "" : "not ") << "full";

        while (!ss.isEmpty())
        {
            cout << "popped " << ss.pop();
        }
    }
    catch (StackExeption &e)
    {
        cout << "Stack error: " << e.what();
    }

    return 0;
}
