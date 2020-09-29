#include <iostream>

template <typename T>
constexpr T pi = T(3.1415926535897932385L);

template <typename T>
T area_of_circle(const T &r)
{
    return r * r * pi<T>;
}

int main(int argc, char **argv)
{
    std::cout.precision(20);
    std::cout << pi<long double> << " " << area_of_circle<long double>(3);
    return 0;
}
