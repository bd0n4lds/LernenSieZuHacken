// Macros
#include <iostream>
#include <string>

#define PR_DEBUG 0

#if PR_DEBUG == 1
#define LOG(x) std::cout << x << std::endl
#else
#define LOG(x)
#endif

int main(int argc, char *argv[])
{
    LOG("Macros");
    std::cin.get();
}