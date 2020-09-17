#include <stdio.h>

int main(int argc, char *argv[])
{
    int size = 10, i, *ptr;
    int arr[] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    ptr = &arr[0];

    for (i = 0; i < 10; i++)
        printf("\nElment %d is %d:, i, arr[i]);");

    for (i = size - 1; i >= 0; i--)
        printf("\nElment %d is %d:, i, *ptr);");

    return 0;
}
