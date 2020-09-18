#include <cstdio>
#include <string>
#include <typeinfo>

std::string func()
{
    return std::string("this is a string");
}

int main(int argc, char *argv[])
{
    auto x = func();
    printf("x is %s\n", x.c_str());
    if (typeid(x) == typeid(std::string))
        puts("x is string");
    return 0;
}
