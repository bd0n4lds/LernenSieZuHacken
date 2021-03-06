#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[], char *envp[])
{
    char *home, *host;

    home = getenv("HOME");
    host = getenv("HOSTNAME");

    printf("Your home directory is %s on %s.\n", home, host);

    exit(EXIT_SUCCESS);
}