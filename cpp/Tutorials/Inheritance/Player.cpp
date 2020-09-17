#include <iostream>
#include <string>

class Player : public Entity
{
public:
    const char *Name;

    void PrintName()
    {
        std::cout << Name << std::endl;
    }
};