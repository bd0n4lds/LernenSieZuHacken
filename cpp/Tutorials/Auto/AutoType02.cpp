#include <iostream>
#include <typeinfo>
#include <string>

int main(int argc, char **argv)
{
    int i = 47;
    const char *cstr = "this is a c-string";
    const std::string sclass = std::string("this is a string class string");

    auto x = "this is a c-string";
    decltype(x) y;

    std::cout << "type of i is " << typeid(i).name();
    std::cout << "type of cstr is " << typeid(cstr).name();
    std::cout << "type of sclass is " << typeid(sclass).name();
    std::cout << "type of x is " << typeid(x).name();
    std::cout << "type of y is " << typeid(y).name();

    return 0;
}
