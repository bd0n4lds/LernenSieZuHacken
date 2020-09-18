// more pointers
#include <iostream>

int main(int argc, char *argv[])
{

  int firstvalue = 5, secondvalue = 15;
  int *p1, *p2;

  p1 = &firstvalue; // p1 = address of firstvalue
  std::cout << " p1 = address of firstvalue is " << firstvalue << std::endl;

  p2 = &secondvalue; // p2 = address of secondvalue
  std::cout << " p2 = address of secondvalue is " << secondvalue << std::endl;

  *p1 = 10; // value pointed by p1 = 10
  std::cout << "value pointed by p1 = 10 firstvalue is " << firstvalue << std::endl;

  *p2 = *p1; // value pointed by p2 = value pointed by p1
  std::cout << "value pointed by p2 = value pointed by p1 secondvalue is " << secondvalue << std::endl;

  p1 = p2; // p1 = p2 (value of pointer is copied)
  std::cout << "p1 = p2 (value of pointer is copied) firstvalue is " << firstvalue << std::endl;

  *p1 = 20; // value pointed by p1 = 20
  std::cout << "value pointed by p1 = 20 secondvalue is " << secondvalue << std::endl;

  return 0;
}