int main(int argc, char **argv)
{
    if (argc < 3)
    {
        printf("usage %s offset_dst offset_src\n", argv[0]);
        exit(1);
    }

    char *src = malloc(256);
    char *dst = malloc(256);

    printf("src=%p\n", src);
    printf("dst=%p\n", dst);

    for (int i = 0; i < 256; i++)
        src[i] = i;
    memset(dst, 0x23, 256);

    memcpy(dst + 128 + atoi(argv[1]),
           src + 128 + atoi(argv[2]),
           0xfffffffffffffffe);

    //Hexdump
    for (int i = 0; i < 256; i += 32)
    {
        printf("%04x:  ", i);
        for (int j = 0; j < 32; j++)
        {
            printf("%02x", dst[i + j] & 0xff);
            if (j % 4 == 3)
                printf(" ");
        }
        printf("\n");
    }
}