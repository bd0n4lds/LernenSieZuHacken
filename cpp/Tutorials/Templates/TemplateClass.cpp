#include <iostream>
#include <string>
#include <exception>

// simple exception class
class E : public std::exception
{
    const char *msg;
    E(){}; // no default constructor
public:
    explicit E(const char *s) throw() : msg(s) {}
    const char *what() const throw() { return msg; }
};

// simple fixed-size LIFO stack template
template <typename T>
class bwstack
{
private:
    static const int defaultsize = 10;
    static const int maxsize = 1000;
    int _size;
    int _top;
    T *_stkptr;

public:
    explicit bwstack(int s = defaultsize);
    ~bwstack() { delete[] _stkptr; }
    T &push(const T &);
    T &pop();
    bool isempty() const { return _top < 0; }
    bool isfull() const { return _top >= _size - 1; }
    int top() const { return _top; }
    int size() const { return _size; }
};

// Stack<T> constructor
template <typename T>
bwstack<T>::bwstack(int s)
{
    if (s > maxsize || s < 1)
        throw E("invalid stack size");
    else
        _size = s;
    _stkptr = new T[_size];
    _top = -1;
}

template <typename T>
T &bwstack<T>::push(const T &i)
{
    if (isfull())
        throw E("stack full");
    return _stkptr[++_top] = i;
}

template <typename T>
T &bwstack<T>::pop()
{
    if (isempty())
        throw E("stack empty");
    return _stkptr[_top--];
}

int main(int argc, char **argv)
{
    try
    {
        bwstack<int> si(5);

        std::cout << "si size: " << si.size() << std::endl;
        std::cout << "si top: " << si.top() << std::endl;

        for (int i : {1, 2, 3, 4, 5})
        {
            si.push(i);
        }

        std::cout << "si top after pushes: " << si.top() << std::endl;
        std::cout << "si is " << (si.isfull() ? "" : "not ") << "full" << std::endl;

        while (!si.isempty())
        {
            std::cout << "popped " << si.pop() << std::endl;
        }
    }
    catch (E &e)
    {
        std::cout << "Stack error: " << e.what() << std::endl;
    }

    try
    {
        bwstack<std::string> ss(5);

        std::cout << "ss size: " << ss.size() << std::endl;
        std::cout << "ss top: " << ss.top() << std::endl;

        for (const char *s : {"one", "two", "three", "four", "five"})
        {
            ss.push(s);
        }

        std::cout << "ss top after pushes: " << ss.top() << std::endl;
        std::cout << "ss is " << (ss.isfull() ? "" : "not ") << "full" << std::endl;

        while (!ss.isempty())
        {
            std::cout << "popped " << ss.pop() << std::endl;
        }
    }
    catch (E &e)
    {
        std::cout << "Stack error: " << e.what() << std::endl;
    }

    return 0;
}