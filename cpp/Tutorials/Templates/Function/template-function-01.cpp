#include <cstdio>
#include <iostream>

template <typename T>
T maxof(T a, T b)
{
    return (a > b ? a : b);
}

int main(int argc, char **argv)
{
    int m = maxof<int>(7, 9);
    std::cout << "Max is: " << m;
    return 0;
}