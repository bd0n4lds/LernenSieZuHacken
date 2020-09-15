#include <iostream>

int main(void)
{

	int arr[10], i, num, n, c = 0, pos;
	std::cout << "Enter the array size : ";
	std::cin >> n;

	std::cout << "Enter Array Elements : ";
	for (i = 0; i < n; i++)
	{
		std::cin >> arr[i];
	}

	std::cout << "Enter the number to be search : ";
	std::cin >> num;

	for (i = 0; i < n; i++)
	{
		if (arr[i] == num)
		{
			c = 1;
			pos = i + 1;
			break;
		}
	}
	if (c == 0)
		std::cout << "Number not found..!!";
	else
		std::cout << num << " found at position " << pos;
}