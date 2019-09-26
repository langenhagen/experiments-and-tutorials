#include <conio.h>
#include <cstdlib> // malloc
#include <fstream>
#include <functional>
#include <stdio.h>
#include <iostream>
#include <string>
#include "cuda_runtime.h"

#include <barn_common.hpp>

void block_sort(int *o, const int *m, int m_sz);


//
// MAIN FUNCTION
//
void main()
{
    int m_sz = 9999;

    int *m = (int*)malloc( m_sz * sizeof(int));
    int *o = (int*)malloc( m_sz * sizeof(int));

    for( int i=0; i<m_sz; ++i)
        m[i] = rand();
    memset( o, 0, m_sz*sizeof(int));
    
    to_file( "m.txt", m, m_sz);

    block_sort( o, m, m_sz);


    
    to_file( "o.txt", o, m_sz);

    std::cout << "DONE";
    getch();
}