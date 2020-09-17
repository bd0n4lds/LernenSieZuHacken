#include <iostream>
#include <string>

class Printable
{
public:
    virtual std::string GetClassName() = 0;
}