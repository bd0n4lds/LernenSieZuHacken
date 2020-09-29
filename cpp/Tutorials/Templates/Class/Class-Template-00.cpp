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
        std::cout << "si top: " << si.top();

        for (int i : nums)
        {
            si.push(i);
        }

        std::cout << "si top after pushes: " << si.top();
        std::cout << "si is " << (si.isFull() ? "" : "not ") << "full";

        while (!si.isEmpty())
        {
            std::cout << "popped " << si.pop();
        }
    }
    catch (StackExeption &e)
    {
        std::cout << "Stack error: " << e.what();
    }

    try
    {
        Stack<std::string> ss(5);

        std::cout << "ss size: " << ss.size();
        std::cout << "ss top: " << ss.top();

        for (const char *s : strs)
        {
            ss.push(s);
        }

        std::cout << "ss top after pushes: " << ss.top();
        std::cout << "ss is " << (ss.isFull() ? "" : "not ") << "full";

        while (!ss.isEmpty())
        {
            std::cout << "popped " << ss.pop();
        }
    }
    catch (StackExeption &e)
    {
        std::cout << "Stack error: " << e.what();
    }

    return 0;
}
