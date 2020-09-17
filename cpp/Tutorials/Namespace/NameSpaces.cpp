// namespaces
#include <iostream>

namespace first
{
  int var = 5;
}

namespace second
{
  double var = 3.1416;
}

int main()
{
  std::cout << first::var << std::endl;
  std::cout << second::var << std::endl;
  return 0;
}