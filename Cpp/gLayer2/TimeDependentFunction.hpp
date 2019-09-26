/**
 * This file provides a class for time dependent function objects.
 * Depends on boost chrono.
 *
 * @author barn
 * @version 20131115
 *
 * TODO remove chrono dependency when cpp11 is available in your ide.
 */
#pragma once


///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers


///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)

#include <boost/chrono.hpp>

///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS


///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


/**
 * This class represents a class for repeatedly called unary functions.
 * It tracks the state of the functions and is able to call them via a delta argument
 * that allows stepwise (e.g. relative time-dependent functions).
 * It also uses a clock that takes care on the time since the last
 * application of the function, which enables you to have the function called
 * in a more or less to the microsecond accurate timing.
 *
 * The first template paramter specifies the numeric accuracity (can be a real number value).
 * The second template parameter specifies the duration used for calculating really
 * time dependent stuff. It especially specifies the data type the duration is stored in
 * (in case of highly accurate time-durations big data types may be preferred) and the granularity
 * of the durations to be used internally, which means something like attoseconds, micro- & milliseconds,
 * or even hours. Have a look at C++11 chrono (or boost::chrono) for further help.
 *
 * TODO maybe incorporate min_argument
 */
template< class Real = float, class Duration = boost::chrono::duration<unsigned int, boost::milli>>
class TimeDependentFunction
{
    // typedefs

    typedef std::function<Real(Real)> RealRealFunction;
    typedef boost::chrono::system_clock Clock;

private: // members


    /// The function that is gonna be used for evaluation.
    RealRealFunction _function;

    /// Indicates, whether current_argument is smaller than max_argument or not.
    bool _is_active;

    /// Stores the time of the last call.
    Clock::time_point _time_of_last_call;

    /// The current function argument.
    Real _current_argument;

    /// The highest function argument that _function will take.
    Real _max_argument;

    /// A factor by which the function will be stretched, manipulating the frequency of the function.
    Real _stretch;

    /// Upper and lower cutoff of the function.
    Real _lower_cutoff, _upper_cutoff;

public: // static methods

    /**
     * Evaluates a function on the argument parameter.
     * @param at The argument on which to apply the function.
     * @param max_argument The highest argument value the function will consider.
     * Arguments above that just return the value for max_function_argument on function.
     * @param function The function that is gonna be used as the function.
     * @param stretch A factor by which the function will be stretched,
     * manipulating the frequency of the function.
     * It should be smaller than the max_function_argument.
     * @param lower_cutoff The lower cutoff of the function,
     * function values beneath that value will be set to this value.
     * @param lower_cutoff The lower cutoff of the function,
     * function values above that value will be set to this value.
     * @return Returns the cut off function value at the position specified by initial_argument.
     */
    static Real applyFunction( Real at,
                               const Real max_argument,
                               const RealRealFunction& function,
                               const Real stretch = 1.0,
                               const Real lower_cutoff = 0.0,
                               const Real upper_cutoff = 1.0)
    {
        at = at < max_argument ? at : max_argument;

        return std::min( upper_cutoff, std::max( lower_cutoff, function( stretch * at) ));
    }

public: // constructor


    /**
     * Constructor #1.
     * Sets itself inactive and fills its values with standard values
     * and an identity function, cut off within [0, 100].
     */
    TimeDependentFunction()
        :
        _is_active(false),
        _current_argument(0.0),
        _max_argument(100.0),
        _stretch( 1.0),
        _lower_cutoff(0.0),
        _upper_cutoff(1.0),
        _time_of_last_call()
    {
        _function = [](Real f){ return f;};
    }


    /**
     * Constructor #2.
     * Initiates a function evaluation and sets the object active.
     * @param max_argument The highest argument value the function will consider.
     * Arguments above that just return the value for max_function_argument on function.
     * @param function The function that is gonna be used as the function.
     * @param initial_argument The initial argument on which to apply the function.
     * This value will be set as the current argument.
     * @param stretch A factor by which the function will be stretched,
     * manipulating the frequency of the function.
     * It should be smaller than the max_function_argument.
     * @param lower_cutoff The lower cutoff of the function,
     * function values beneath that value will be set to this value.
     * @param lower_cutoff The lower cutoff of the function,
     * function values above that value will be set to this value.
     */
    TimeDependentFunction( const Real max_argument,
                           const RealRealFunction& function,
                           const Real initial_argument = 0.0,
                           const Real stretch = 1.0,
                           const Real lower_cutoff = 0.0,
                           const Real upper_cutoff = 1.0)
        :
        _function(function),
        _is_active(true),
        _current_argument(initial_argument),
        _max_argument(max_argument),
        _stretch( stretch),
        _lower_cutoff(lower_cutoff),
        _upper_cutoff(upper_cutoff),
        _time_of_last_call()
    {}


public: // methods


    /**
     * Initiates a function evaluation and evaluates the function on the inital argument parameter.
     * Naturally, sets the object into active state. If, however, the object is inactive
     * after call of this function, you probably have used inconsistent values for
     * initial_argument and max_argument or whatsoever.
     * @param max_argument The highest argument value the function will consider.
     * Arguments above that just return the value for max_function_argument on function.
     * @param function The function that is gonna be used as the function.
     * @param initial_argument The initial argument on which to apply the function.
     * This value will be set as the current argument.
     * @param stretch A factor by which the function will be stretched,
     * manipulating the frequency of the function.
     * It should be smaller than the max_function_argument.
     * @param lower_cutoff The lower cutoff of the function,
     * function values beneath that value will be set to this value.
     * @param lower_cutoff The lower cutoff of the function,
     * function values above that value will be set to this value.
     * @return Returns the cut off function value at the position specified by initial_argument.
     */
    Real apply( const Real max_argument,
                 const RealRealFunction& function,
                 const Real initial_argument = 0.0,
                 const Real stretch = 1.0,
                 const Real lower_cutoff = 0.0,
                 const Real upper_cutoff = 1.0)
    {
        _max_argument     = max_argument;
        _function         = function;
        _current_argument = initial_argument;
        _stretch          = stretch;
        _lower_cutoff     = lower_cutoff;
        _upper_cutoff     = upper_cutoff;
        _is_active        = true;

        return apply( 0);
    }


    /**
     * Retrieves the value of the function applied to the given argument delta
     * plus the current stored function argument with the function range
     * cut within the previously specified [lower_cutoff,upper_cutoff].
     * Note that this method will change the internally stored current function argument.
     * It will also change the current time as the last time the function was called,
     * if you want to use apply().
     * @param delta The argument delta that will be added to the last function argument
     * for the function. Please consider that new function argument + delta
     * is cut to max_function_argument when it is bigger than it.
     * Also, if argument + delta are bigger equal the max_argument,
     * the function is considered done and thus set inactive.
     * @return The value of the function applied to the function argument
     * with the function range cut within [lower_cutoff,upper_cutoff].
     * @see isActive(), apply(void)
     */
    Real apply( const Real delta)
    {
        Real ret;

        if( _current_argument + delta < _max_argument)
        {
            _current_argument += delta;
            ret = std::min( _upper_cutoff, std::max( _lower_cutoff, _function( _stretch * _current_argument ) ));
        }
        else
        {
            ret = std::min( _upper_cutoff, std::max( _lower_cutoff, _function(_stretch * _max_argument) ));
            _is_active = false;
        }

        _time_of_last_call = Clock::now();

        return ret;
    }

    /**
     * Retrieves the value of the function applied the time delta since the last call
     * plus the current stored function argument with the function range
     * cut within the previously specified [lower_cutoff,upper_cutoff].
     * The accuracy is specified by the Duration template Parameter of the class.
     * Note that this method will change the internally stored current function argument.
     * It will also change the current time as the last time the function was called,
     * if you want to use apply() again.
     * @return The value of the function applied to the function argument
     * with the function range cut within [lower_cutoff,upper_cutoff].
     * @see isActive(void), apply(Real)
     */
    inline Real apply()
    {
        return apply( boost::chrono::duration_cast<Duration> (boost::chrono::system_clock::now() - time_point));
    }


    /**
     * Applies the function at the given positon with the stored attributes.
     * Note that this method is not dependent on the active status of the object
     * nor will it change any value stored in the object.
     * @param at The argument which is to be applied.
     * @return The value of the function applied to the function argument
     * with the function range cut within [lower_cutoff,upper_cutoff].
     */
    inline Real applyAt( Real at)
    {
        at = at < _max_argument ? at : _max_argument;

        return std::min( _upper_cutoff, std::max( _lower_cutoff, _function( _stretch * at) ));
    }


    /**
     * A shortcut alias for the according apply method.
     * @param max_argument The highest argument value the function will consider.
     * Arguments above that just return the value for max_function_argument on function.
     * @param function The function that is gonna be used as the function.
     * @param initial_argument The initial argument on which to apply the function.
     * This value will be set as the current argument.
     * @param stretch A factor by which the function will be stretched,
     * manipulating the frequency of the function.
     * It should be smaller than the max_function_argument.
     * @param lower_cutoff The lower cutoff of the function,
     * function values beneath that value will be set to this value.
     * @param lower_cutoff The lower cutoff of the function,
     * function values above that value will be set to this value.
     * @return Returns the cut off function value at the position specified by initial_argument.
     * @see apply(const Real, const RealRealFunction&,const Real,const Real,const Real,const Real)
     */
    inline Real operator()( const Real max_argument,
                            const RealRealFunction& function,
                            const Real initial_argument = 0.0,
                            const Real stretch = 1.0,
                            const Real lower_cutoff = 0.0,
                            const Real upper_cutoff = 1.0)
    {
        return apply( max_argument, function, initial_argument, stretch, lower_cutoff, upper_cutoff);
    }


    /**
     * A shortcut alias for the according apply method.
     * @param delta The argument delta that will be added to the last function argument
     * for the function. Please consider that new function argument + delta
     * is cut to max_function_argument when it is bigger than it.
     * Also, if argument + delta are bigger equal the max_argument,
     * the function is considered done and thus set inactive.
     * @return The value of the function applied to the function argument
     * with the function range cut within [lower_cutoff,upper_cutoff].
     * @see isActive()
     * @see apply(const Real)
     */
    inline Real operator()( const Real delta)
    {
        return apply( delta);
    }


    /**
     * A shortcut alias for the according apply method.
     * @return The value of the function applied to the function argument
     * with the function range cut within [lower_cutoff,upper_cutoff].
     * @see apply(void)
     */
    inline Real operator()()
    {
        return apply();
    }


    /**
     * Indicates, whether a function process is in progress.
     * Note that this value is only informational and not
     * hindering you to call any of the functions, but it might prevent
     * you from using processor time if it may be not necessary to call the function.
     * @return Returns TRUE, when current argument is smaller than maximum argument,
     * returns FALSE, if not.
     */
    inline bool isActive()
    {
        return _is_active;
    }


    /**
     * Sets the function active and sets the current argument to a specified value.
     * All other values stay the same as specified before.
     * @param initial_argument The initial argument that is to be the new
     * current argument. Should be smaller than the hopefully previous specified
     * max_argument.
     */
    void setActive( const Real initial_argument = 0.0)
    {
        _current_argument = initial_argument;
        _is_active = true;
    }

}; // END class TimeDependentFunction


/// A TimeDependentFunction with float accuracity and Millisecond-accurate durations
typedef TimeDependentFunction<float,  boost::chrono::duration<unsigned int, boost::milli>>  TimeDependentFloatFunction;

/// A TimeDependentFunction with double accuracity and Millisecond-accurate durations
typedef TimeDependentFunction<double, boost::chrono::duration<unsigned int, boost::milli>> TimeDependentDoubleFunction;
