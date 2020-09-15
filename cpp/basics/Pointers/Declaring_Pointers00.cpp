// my first pointer
#include <iostream>

int main()
{
  int firstvalue, secondvalue;
  int *mypointer;

  mypointer = &firstvalue;
  *mypointer = 10;
  mypointer = &secondvalue;
  *mypointer = 20;
  std::cout << "firstvalue is " << firstvalue << std::endl;
  std::cout << "secondvalue is " << secondvalue << std::endl;
  return 0;
}