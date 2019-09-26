/******************************************************************************
/* @file File contains common & generic utility/convenience functions.
/* @author barn
/* @version 20131104
/******************************************************************************/
#pragma once

///////////////////////////////////////////////////////////////////////////////
// INCLUDES project headers

///////////////////////////////////////////////////////////////////////////////
//INCLUDES C/C++ standard library (and other external libraries)
#include <ostream>
#include <fstream>      // std::ifstream
#include <list>
#include <map>

#include <boost/algorithm/string.hpp>
#include <boost/thread.hpp>


///////////////////////////////////////////////////////////////////////////////
// DEFINES and MACROS

///////////////////////////////////////////////////////////////////////////////
// NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS


namespace Ogre
{
    class CompositorInstance;
    class Viewport;
}

typedef std::map< std::string, std::string>                 StringStringMap;
typedef std::map< std::string, StringStringMap>             String2DMap;
typedef std::map< std::string, Ogre::CompositorInstance*>   StringCompositorMap;
typedef std::map< std::string, Ogre::Viewport*>             StringViewportMap;

typedef boost::lock_guard<boost::mutex> lock_t;

/**
 * cout oparator on pair<float,float>.
 * @param os An ostream.
 * @param float_pair a std::pair of type <float,float>
 * @return The ostream that was the first param.
 */
std::ostream& operator<<(std::ostream& os, const std::pair<float,float> float_pair)
{
    os << float_pair.first << ";\t " << float_pair.second;
    return os;
}


/** Enhanced delete call.
@param pointer The pointer to be released
*/
template <typename T>
inline void safe_release(T& pointer)
{
    if (nullptr != pointer)
    {
        delete pointer;
        pointer = nullptr;
    }
}


/**
 * Reads pairs of key/value strings from a given config file and
 * puts them into a list of Pairs of type std::string, std::string.
 * @param file_name The filename/path to the file from where the key/values pairs
 * are to be extracted.
 * @param section_name The name of the section from which to grab the key/value pairs
 * within the specified file.
 * @param separator the separator to be used separating keys/values.
 * @return A map of type string, string.
 * First string represents the key.
 * Second string represents the value.
 */
StringStringMap readStringStringPairsFromFile( const std::string& file_name,
                                               const std::string& section_name,
                                               const char separator='=')
{
    StringStringMap ret;

    std::ifstream file(file_name);
    std::string line;

    // find section "section_name"
    while( std::getline(file, line))
    {
        boost::algorithm::trim(line);

        if( !boost::iequals(line, section_name))
            continue;
        break;
    }

    while (std::getline(file, line))
    {
        // skip empty lines and out-commented lines
        if( line.length() == 0  || line.find_first_of('#') == 0)
            continue;

        auto separator_index (line.find("="));

        // if reached end of section
        if( separator_index == std::string::npos)
            break;

        // *** inside the section ***

        std::string type( line.substr(0, separator_index));
        std::string value( line.substr( separator_index+1, line.length()-separator_index-1));

        boost::algorithm::trim( type);
        boost::algorithm::trim( value);

        ret.insert( std::make_pair(type, value));
    }

    return ret;
}


/**
 * TODO IMPLEMENT
 * Creates a 2D map of string/string keys and string values from a given config file and
 * @param file_name The filename/path to the file from where the key/key/values
 * are to be extracted.
 * @return A map<string, map<string,string>>.
 * First and second dimension strings represent the keys
 * Second string represents the value.
 */
String2DMap readString2DMapFromFile( const std::string& file_name)
{
    String2DMap ret;

    std::ifstream file(file_name);
    std::string line;

    // TODO

    return ret;
}
