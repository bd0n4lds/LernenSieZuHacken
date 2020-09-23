int main()
{
    auto l = [i = 0]() mutable { return i++; };
    return l();
}