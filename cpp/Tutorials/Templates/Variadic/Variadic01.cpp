#include <sstream>
#include <iostream>
#include <vector>

template <typename T>
std::string to_string_impl(const T &t)
{
    std::stringstream ss;
    ss << t;
    return ss.str();
}

template <typename... Param>
std::vector<std::string> to_string(const Param &... param)
{
    return {to_string_impl(param)...};
}

int main(int argc, char **argv)
{
    const auto vec = to_string("hello", 1, 5.3, "World", 1.3f, 4, 3, 5, 1000, "Bob", 0xff);

    for (const auto &o : vec)
    {
        std::cout << o << '\n';
    }
}