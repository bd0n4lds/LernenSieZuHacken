// increaser
#include <iostream>

void increase(void *data, int psize)
{
  if (psize == sizeof(char))
  {
    char *pchar;
    pchar = (char *)data;
    ++(*pchar);
  }
  else if (psize == sizeof(int))
  {
    int *pint;
    pint = (int *)data;
    ++(*pint);
  }
}

int main(int argc, char *argv[])
{
  char a = 'x';
  int b = 1602;
  increase(&a, sizeof(a));
  increase(&b, sizeof(b));
  std::cout << a << ", " << b << '\n';
  return 0;
}