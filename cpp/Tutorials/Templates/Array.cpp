#include <iostream>
#include <string>

template <typename T, int N>
class Array
{
private:
    T m_Array[N];

public:
    int GetSize() const { return N; }
};