#ifndef MACROS_H
#define MACROS_H

// Some useful macros

#include <stdio.h>
#include <stdarg.h>
#include <stdlib.h>

#include "cuda_runtime.h"


#undef min
#undef max

#undef MIN
#define MIN(a, b) ((a) < (b) ? (a) : (b))

#undef MAX
#define MAX(a, b) ((a) > (b) ? (a) : (b))

#ifndef M_PI
#define M_PI 3.14159265
#endif

#ifndef PI
#define PI 3.14159265
#endif

#ifndef E
#define E 2.7182818284590451
#endif

#ifdef WIN32
#include <windows.h>
#define msleep Sleep
#else
#define msleep(arg) usleep((arg)*1000)
#endif

static inline unsigned char HDRtoLDR(float x) {
    if (x < 0) return 0;
    if (x > 1) return 255;
    return (unsigned char)(x * 255.0f + 0.49999f);
}

static inline float LDRtoHDR(unsigned char x) {
    return x * (1.0f/255);
}

static inline float LDR16toHDR(unsigned short x) {
    return x * (1.0f/65535);
}

static void panic(const char *fmt, ...) {
    va_list arglist;
    va_start(arglist, fmt);
    fprintf(stderr, fmt, arglist);
    va_end(arglist);
    exit(-1);
}

void my_assert(bool cond, const char *fmt, ...) {
    if (!cond) {
        va_list arglist;
        va_start(arglist, fmt);
        fprintf(stderr, fmt, arglist);
        va_end(arglist);
        exit(-1);
    }
}

// stuff below here makes up for C99 not being supported (I'm looking at you msvc!)
#ifndef isnan
static inline bool is_nan(float x) {
    unsigned char *s = ((unsigned char *)(&x));

    // exponent is 255
    bool exp255 = ((s[3] >> 1) == 127) && (s[2] & 1);
    // sign bit
    //bool positive = s[3] & 1;
    // mantissa is non zero
    bool mantissa = s[0] || s[1] || (s[2] >> 1);

    return exp255 && mantissa;
}
#endif

#ifndef isfinite
static inline float is_finite(float x) {
    unsigned char *s = ((unsigned char *)(&x));

    // exponent is 255
    bool exp255 = ((s[3] >> 1) == 127) && (s[2] & 1);
    // sign bit
    //bool positive = s[3] & 1;
    // mantissa is non zero
    bool mantissa = s[0] || s[1] || (s[2] >> 1);

    return exp255 && mantissa;
}
#endif

#ifndef isinf
static inline float is_inf(float x) {
    unsigned char *s = ((unsigned char *)(&x));

    // exponent is 255
    bool exp255 = ((s[3] >> 1) == 127) && (s[2] & 1);
    // sign bit
    //bool positive = s[3] & 1;
    // mantissa is non zero
    bool mantissa = s[0] || s[1] || (s[2] >> 1);

    return exp255 && (!mantissa);
}
#endif


#define cu_err_chk(ec) { cu_assert((ec), __FILE__, __LINE__, true); }



inline void cu_assert(cudaError_t code, const char *file, int line, bool abort) {
    if (code != cudaSuccess) {
        fprintf(stderr,"GPUassert: %s %s %d\n", cudaGetErrorString(code), file, line);
        if (abort) exit(code);
    }
}



#include <math.h>

#endif
