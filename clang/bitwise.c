/*
Write the code for the definition of set_flag and check_flag
so that the output of your program looks like the following:
10000 0000 00010000 0000 0000 1000

You can think of the set_flag function as taking an integer and
making sure that the nth bit is a 1. The check_flag function
simply returns an integer that is zero when the nth bit is
zero and 1 when it is 1. You may find the shifting operators
helpful as well as the bitwise operations & and |.
*/

#include <stdio.h>

void set_flag(int *flag_holder, int flag_position);

int check_flag(int flag_holder, int flag_position);

int main(int argc, char *argv[])
{
	int flag_holder = 0;
	int i;

	set_flag(&flag_holder, 3);

	for (i = 31; i >= 0; i--){
		printf("%d", check_flag(flag_holder, i));
		if (i % 4 == 0){
			printf(" ");
		}
	}
	printf("\n");
	return 0;
}

void set_flag(int *flag_holder, int flag_position)
{
	*flag_holder &= (1 << flag_position);
}

int check_flag(int flag_holder, int flag_position)
{
	int flag = (flag_holder >> flag_position) & 1;
	return flag;
}
