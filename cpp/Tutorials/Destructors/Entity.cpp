#include <iostream>
#include <string>

class Entity
{
public:
    float X, Y;
    Entity()
    {
        std::cout << "Created Entity!" << std::endl;
    }

    Entity(float x, float y)
    {
        X = x;
        Y = y;
    }

    ~Entity()
    {
        std::cout << "Destroyed Entity!" << std::endl;
    }

    void Print()
    {
        std::cout << X << "," << Y << std::endl;
    }
};