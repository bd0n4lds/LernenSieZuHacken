// more pointers
#include <iostream>

int main(int argc, char *argv[])
{
  int firstvalue = 5, secondvalue = 15;
  int *p1, *p2;

  p1 = &firstvalue;  // p1 = address of firstvalue
  p2 = &secondvalue; // p2 = address of secondvalue
  *p1 = 10;          // value pointed by p1 = 10
  *p2 = *p1;         // value pointed by p2 = value pointed by p1
  p1 = p2;           // p1 = p2 (value of pointer is copied)
  *p1 = 20;          // value pointed by p1 = 20

  std::cout << "firstvalue is " << firstvalue << std::endl;
  std::cout << "secondvalue is " << secondvalue << std::endl;
  return 0;
}