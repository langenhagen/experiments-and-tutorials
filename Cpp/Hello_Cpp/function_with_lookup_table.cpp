/******************************************************************************
/*@file Function for test challenge for celera one.
/*      Cpp only if you don't mind.
/*
/*
/* TODO Rather use std::array than plain old arrays
/*      
/*@author langenhagen
/*@version 150916
/******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include <chrono>
#include <iostream>
#include <stdint.h>  // uint32_t, uint64_t

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS

/// assignment namespace
namespace celera_one_test {

    /// Lookup array length. Reasonable values btw 10.000 and 100.000.
    const uint32_t LOOKUP_ARRAY_SIZE = 100000;

    /** Plain old lookup array for all odd numbers within { 1, 2*LOOKUP_ARRAY_SIZE-1 }.
    Not really safe, could have been const-defined with pre-calculated values,
    but I think its enough for the task to initialize it at runtime.
    @see init_lookup_array()
    @see function_with_lookup()
    */
    uint32_t lookup_array[LOOKUP_ARRAY_SIZE];

    // function declarations
    template< typename N> inline bool is_zero_or_power_of_2(N n);
    void init_lookup_array();
    uint32_t function_naive(uint64_t i);
    uint32_t function_with_lookup(uint64_t i);


    /** Checks whether a given value is zero or a power of two. Takes constant time.
    @param n The number to be checked.
    @return TRUE when n is a power of two or n==0,
            FALSE otherwise.
    */
    template< typename N>
    inline bool is_zero_or_power_of_2(N n) {
        return (n & (n - 1)) == 0;
    }


    /** Initializes the lookup array with values to all odd arguments within { 1 , 2*LOOKUP_ARRAY_SIZE+1 }.
    @see lookup_array
    @see LOOKUP_ARRAY_SIZE
    */
    void init_lookup_array() {
        for( int i = 0; i<LOOKUP_ARRAY_SIZE; ++i) {
            lookup_array[i] = function_naive(i * 2 + 1);
        }
    }


    /** Naive function implementation. Implements the following function:
    f(0) = 1
    f(1) = 1
    f(2n) = f(n)
    f(2n+1) = f(n) + f(n-1)
    @param i The input argument.
    @return The function value evaluated at i.
    @see function_with_lookup()
    */
    uint32_t function_naive(uint64_t i) {

        if (is_zero_or_power_of_2(i)) {
            // exploits the function properties  f(0) = 1  AND  f(2^n) = 1
            return 1;
        }

        if (i % 2 == 0) {
            // *** i is even ***
            return function_naive(i >> 1);
        } else {
            // *** i is odd ***
            uint64_t n(i >> 1);
            return function_naive(n) + function_naive(n - 1);
        }
    }


    /** Function implementation that uses the lookup array. Implements the following function:
    f(0) = 1
    f(1) = 1
    f(2n) = f(n)
    f(2n+1) = f(n) + f(n-1)
    @param i The input argument.
    @see lookup_array
    @see function_naive()
    @return The function value evaluated at i.
    */
    uint32_t function_with_lookup(uint64_t i) {

        if (is_zero_or_power_of_2(i)) {
            // exploits the function properties  f(0) = 1  AND  f(2^n) = 1
            return 1;
        }

        if (i % 2 == 0) {
            // *** i is even ***
            return function_with_lookup(i >> 1);
        } else {
            // *** i is odd ***

            if (i < 2 * LOOKUP_ARRAY_SIZE) {
                // *** i can be found in lookup array ***
                return lookup_array[i >> 1];
            } else {
                // *** i is too big for lookup, must be computed ***

                uint64_t n(i >> 1);
                return function_with_lookup(n) + function_with_lookup(n - 1);
            }
        }
    }

} // END namespace celera_one_test


/** Helper function that calculates the duration between a given time point in the past and now.
@param clock_start A time point, that should be in the past.
@return the time duration (e.g. milliseconds) between clock_start and now().
*/
template< typename T = std::chrono::milliseconds>
inline T time_since(std::chrono::steady_clock::time_point& clock_start) {
    return std::chrono::duration_cast< T>(std::chrono::steady_clock::now() - clock_start);
}



int main() {
    
    // init phase

    std::chrono::steady_clock::time_point clock_start;
    std::chrono::milliseconds ms;

    std::cout << "creating lookup array with " << celera_one_test::LOOKUP_ARRAY_SIZE << " elements... ";
    clock_start = std::chrono::steady_clock::now();
    celera_one_test::init_lookup_array();
    ms = time_since(clock_start);
    std::cout << "done (" << ms.count() << " ms).\n";


    // function test phase

    const uint64_t test_val = 123456789012345678;
    int32_t result(-1);

    std::cout << "calculating function value for " << test_val << " using naive implementation... ";
    clock_start = std::chrono::steady_clock::now();
    result = celera_one_test::function_naive( test_val);
    ms = time_since(clock_start);
    std::cout << "done (" << ms.count() << " ms).\n"
              << "f(" << test_val << ") = " << result << "\n";

    result = -1;

    std::cout << "calculating function value for " << test_val << " using function with lookup... ";
    clock_start = std::chrono::steady_clock::now();
    result = celera_one_test::function_with_lookup( test_val);
    ms = time_since(clock_start);
    std::cout << "done (" << ms.count() << " ms).\n"
        << "f(" << test_val << ") = " << result << "\n";


    std::cout << "\n"
                 "\n*** PROGRAM END ***";
    return 0;
}