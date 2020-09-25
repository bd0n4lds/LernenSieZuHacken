#include <iostream>
#include <string>
#include <vector>

int main(int argc, char **argv)
{
     std::cout << "Vector from initializer list: ";
     std::vector<int> vi1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

     std::cout << "size: " << vi1.size();
     std::cout << "front: " << vi1.front();
     std::cout << "back: " << vi1.back();

     // iterator
     std::cout << "Iterator:";
     std::vector<int>::iterator itbegin = vi1.begin();
     std::vector<int>::iterator itend = vi1.end();
     for (auto it = itbegin; it < itend; ++it)
     {
          std::cout << *it << ' ';
     }
     std::cout;

     std::cout
         << "Index:";
     std::cout << "element at 5: " << vi1[5];
     std::cout << "element at 5: " << vi1.at(5);

     std::cout
         << "Range-based for loop:";
     for (int &i : vi1)
     {
          std::cout << i << ' ';
     }
     std::cout;

     std::cout << "Insert 42 at begin + 5: ";
     vi1.insert(vi1.begin() + 5, 42);
     std::cout << "size: " << vi1.size();
     std::cout << "vi1[5]: " << vi1[5];

     std::cout << "Erase at begin + 5: ";
     vi1.erase(vi1.begin() + 5);
     std::cout << "size: " << vi1.size();
     std::cout << "vi1[5]: " << vi1[5];

     std::cout << "push_back 47: ";
     vi1.push_back(47);
     std::cout << "size: " << vi1.size();
     std::cout << "vi1.back() " << vi1.back();

     std::cout << "Range-based for loop: ";
     for (int &v : vi1)
     {
          std::cout << v << ' ';
     }
     std::cout;

     // from C-array
     const static size_t size = 10;
     int ia[size] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
     std::cout << "Vector from C-array: ";
     std::vector<int> vi2(ia, ia + size);
     for (int &i : vi2)
     {
          std::cout << i << ' ';
     }
     std::cout;

     // vector of strings
     std::cout << "Vector of strings:";
     std::vector<string> vs = {"one", "two", "three", "four", "five"};
     for (string &v : vs)
     {
          std::cout << v;
     }

     return 0;
}