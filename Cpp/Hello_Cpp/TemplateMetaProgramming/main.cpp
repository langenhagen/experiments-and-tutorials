// source taken from 2O14 01 01 http://www.codeproject.com/Articles/700868/Template-Meta-Programming

#include <iostream>
#include <list>
#include <conio.h>

using namespace std;


template <bool Predicate>
struct selector
{
};

template <typename T1, typename T2>
struct type_equal
  : selector<false>
{
  static const bool value = false;
};

template <typename T>
struct type_equal<T ,T> 
  : selector<true>
{
  static const bool value = true;
};


template <typename iterator_t>
void random_fill(iterator_t begin, iterator_t end, selector<false>)
{
  for (; begin != end; ++begin)
    *begin = 1;//rand();
}
 
template <typename iterator_t>
void random_fill(iterator_t begin, iterator_t end, selector<true>)
{
  for (; begin != end; ++begin)
    *begin = 55;//'A' + (rand() %26);
}



int main()
{
    selector<true> s1;
    selector<false> s2;

    type_equal<int, float> te1(); // --> false
    type_equal<int, int> t2(); // --> true
 
    list<int> l = list<int>();
    for( int i=0; i<10; ++i)
        l.push_back(0);
    
    auto begin = l.begin();
    auto middle= ++ ++ ++ ++ l.begin() ; // TODO something bout hat
    auto end = l.end();

    random_fill(begin, middle, selector<type_equal <int, char*>::value>()); // works also
    
    random_fill(middle, end, type_equal<int , int>());
    
    for( auto it = l.begin(); it != l.end(); ++it)
        cout << *it << endl;

    getch();
}