/**
 * @brief
 * @author barn
 * @version 20121011
 */
#ifndef __A_HPP__
#define __A_HPP__

/*===========================================================================*
 * INCLUDES base definition file for DLL import/export
 *===========================================================================*/
 
/*===========================================================================*
 * INCLUDES project headers
 *===========================================================================*/
 
/*===========================================================================*
 * INCLUDES C/C++ standard library (and other external libraries)
 *===========================================================================*/
#include <string.h>

/*===========================================================================*
 * DEFINES and MACROS
 *===========================================================================*/
 
/*===========================================================================*
 * NAMESPACE, CONSTANTS and TYPE DECLARATIONS/IMPLEMENTATIONS
 *===========================================================================*/

namespace Moef
{

	/**
	 * @brief
	 */
	class A
	{
	private: //vars

		int _i;
		
	public: // functions

		A( int i = 10)
		{
			_i = i;
		}

		int getI()
		{
			return _i;
		}
	
	}; // END class A
	
} // END namespace OwnApp

#endif /* __A_HPP__ */
