#include <iostream>
#include <string>

class Player : public Entity
{
private:
    std::string m_Name;

public:
    Player(const std::string &name)
        : m_Name(name) {}
    std::string GetName() override { return m_Name; }
};