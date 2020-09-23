#include <iostream>

template <typename T>
T GetMax(T a, T b)
{
  T result;
  result = (a > b) ? a : b;
  return (result);
}

int main(int argc, char **argv)
{
  int i = 5, j = 6, k;
  long l = 10, m = 5, n;

  k = GetMax<int>(i, j);
  n = GetMax<long>(l, m);

  std::cout << k;
  std::cout << n;

  return 0;
}