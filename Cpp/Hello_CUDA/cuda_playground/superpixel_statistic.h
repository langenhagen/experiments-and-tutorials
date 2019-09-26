/******************************************************************************
/* @file GPU parallel Implementation of the slic superpixels algorithm.
/*
/* TODO everything TODO re-doc
/*
/* @author langenhagen
/* @version 150104
/******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)


///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS

/** Stores information about one superpixel.
 */
struct superpixel_statistic {

    float L;    ///< L part of the Lab mean color.
    float a;    ///< a part of the Lab mean color.
    float b;    ///< b part of the Lab mean color.
    
    float x;    ///< mean x coordinate.
    float y:    ///< mean y coordinate.
    
    int size;   ///< size of the superpixel.
    
    
    /// Constructor #1.
    superpixel_statistic()
        : L(0.0f), a(0.0f), b(0.0f), x(0.0f), y(0.0f)
    {}
 
};