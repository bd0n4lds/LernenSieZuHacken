#include <iostream>
#include <vector>

void PrintValue(int value)
{
    std::cout << "Value: " << value << std::endl;
}

void ForEach(const std::vector<int> &values, void (*func)(int))
{
    for (int value : values)
        func(value);
}

int main(int argc, char *argv[])
{
    std::vector<int> values = {1, 5, 7, 3, 4, 9};
    ForEach(values, PrintValue);
    return 0;
}