#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>

static void
usageError(char *progName)
{
	fprintf(stderr, "Usage: %s [-d] filename\n", progName);
	exit(EXIT_FAILURE);
}

int main(int argc, char *argv[])
{
	int opt;
	while ((opt = getopt(argc, argv, "d")) != -1)
	{
		switch (opt)
		{
		case 'd':;
			break;
		case '?':
			usageError(argv[0]);
		}
	}
}
