#include <iostream>
#include <string>

class Entity
{
private:
    std::string m_Name;

public:
    Entity()
        : m_Name("Unknown")
    {
    }

    Entity(const std::string &name)
        : m_Name(name)
    {
    }

    const std::string &GetName() const { return m_Name; }
};