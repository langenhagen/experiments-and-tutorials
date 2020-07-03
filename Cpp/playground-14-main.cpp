// The main file of my legacy test project that I used as a convenient playground to do small little
// experiments.
// author: andreasl
// version: 19-04-25

#define HELLO
#define NOMINMAX

#define PI 3.14159265359

#include <algorithm>
#include <array>
#include <conio.h>
#include <cmath>    // since Cpp11 isnan, isinf
#include <chrono>
#include <iostream>
#include <float.h>  // _finite
#include <fstream>
#include <functional>
#include <iterator>
#include <limits>
#include <list>
#include <locale>
#include <map>
#include <memory>
#include <mutex>
#include <numeric>
#include <queue> // priority queue
#include <random>
#include <set>
#include <stdlib.h>     // atof, itoa
#include <string>
#include <time.h>
#include <tuple>
#include <typeinfo>
#include <unordered_map>
#include <unordered_set>
#include <Windows.h> // FreeConsole

/*
#include <boost/filesystem.hpp>
#include <boost/thread.hpp>
#include <boost/thread/locks.hpp>
*/

#include <rlutil.h>
#include <barn_common.hpp>
#include <barn_test/FunctionTest.hpp>
#include <barn_test/RandomizedFunctionTest.hpp>
#include <Tweak.hpp>
#include <args_from_tuple.hpp>
#include <barn_common_tests.hpp>
#include <tuple_to_stream.hpp>

#pragma warning(disable:4018) // signed / unsigned conflict

#include "Neuron.hpp"
using namespace BANN;

using namespace std;
using namespace tuple_to_stream;
using namespace unittest;



int main() {
    setlocale(LC_ALL, "");
    srand(time(0));
    //barn_common_test_all();

    intvec v(10);

    using Float10 = std::array<float, 10>;
    using DynamicFloatNeuron = Neuron< float, float, floatvec, floatvec>;
    using FloatNeuron10 = Neuron<float, float, Float10, Float10>;

    auto sigmoid_float_function = [](float f) { return 1 / (1 + std::expf(-f)); };

    DynamicFloatNeuron dfn = DynamicFloatNeuron(floatvec{}, sigmoid_float_function);

    FloatNeuron10 fn10 = FloatNeuron10(Float10(), sigmoid_float_function);


    std::cout << "\n"
        "\n*** PROGRAM END - PRESS ANY KEY TO EXIT ***";
    getch();
}
