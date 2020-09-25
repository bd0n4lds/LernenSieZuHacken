#include <iostream>

template <typename T>
int size_in_bits(const T &a)
{
   return sizeof(a) * 8;
}

int main(int argc, char **argv)
{
   std::cout << size_in_bits(21);
   std::cout << size_in_bits('f');
   std::cout << size_in_bits(32.1f);
   std::cout << size_in_bits(32.1);
   return 0;
}
