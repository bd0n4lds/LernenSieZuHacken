#include <iostream>
#include <string>

template <typename T>
T maxof(const T &a, const T &b)
{
    return (a > b ? a : b);
}

int main(int argc, char **argv)
{
    int a = 7;
    int b = 9;

    std::cout << "max is " << maxof<int>(a, b);

    return 0;
}