/////////////////////////////////////////////////////////////////////////////
// Header DayTime.h für die Definition der Klasse Artikel
/////////////////////////////////////////////////////////////////////////////
#ifndef _DAYTIME_
#define _DAYTIME_

#include <iostream>
#include <iomanip>
using namespace std;

class DayTime
{
	private:
		short hour, minute, second;
		bool overflow, underflow;
	public:
																												//Konstruktoren
		DayTime( int h = 0, int m = 0, int s =0)
		{
			overflow = false;
			if( !setTime( h, m, s ))
				hour = minute = second = 0;
		}
																												//Set-Methoden
		bool setTime(int hour, int minute, int second = 0)
		{
			if(			hour		>= 0 && hour		< 24
					&&	minute	>= 0 && minute	< 60
					&&	second	>= 0 && second	< 60 )
			{
				this->hour		= (short)hour;
				this->minute	= (short)minute;
				this->second	= (short)second;
				return true;
			}
			else
				return false;
		}
																												//Get-Methoden
		int getHour()		const { return hour; }
		int getMinute()	const { return minute; }
		int getSecond()	const { return second; }
		int asSeconds()	const
		{
			return (60*60*hour + 60*minute + second);
		}
																												//Operatoren
		DayTime& operator++( void )
		{
			++second;

			if( second >=60 )
			{ second = 0 ; ++minute; }
			if( minute >=60 )
			{ minute = 0 ; ++hour; }
			if( hour >=24 )
			{ hour = 0; overflow = true;}
			
			return *this;
		}

		DayTime& operator--( void )
		{
			if(second == 0 && minute == 0 && hour == 0)
				return *this;

			--second;

			if( second < 0 )
			{ second = 59 ; --minute; }
			if( minute < 0 )
			{ minute = 59 ; --hour; }
			if( hour < 00 )
			{ hour = 23; underflow = true;}
			
			return *this;
		}

		DayTime operator++( int )
		{
			DayTime temp(*this);

			++second;

			if( second >=60 )
			{ second = 0 ; ++minute; }
			if( minute >=60 )
			{ minute = 0 ; ++hour; }
			if( hour >=24 )
			{ hour = 0; overflow = true;}
			
			return temp;
		}

		DayTime operator--( int )
		{
			if(second == 0 && minute == 0 && hour == 0)
				return *this;

			DayTime temp(*this);

			--second;

			if( second < 0 )
			{ second = 59 ; --minute; }
			if( minute < 0 )
			{ minute = 59 ; --hour; }
			if( hour < 0 )
			{ hour = 23; underflow = true; }
			
			return temp;
		}
																											//Sonstige
		void swap( DayTime& t );
		void print();
};

///////////////////////////////////////////////////////
//Vergleichsoperatoren
inline bool operator<( const DayTime& t1, const DayTime& t2)
{ return t1.asSeconds() < t2.asSeconds(); }

inline bool operator>( const DayTime& t1, const DayTime& t2)
{ return t1.asSeconds() > t2.asSeconds(); }

inline bool operator<=( const DayTime& t1, const DayTime& t2)
{ return t1.asSeconds() <= t2.asSeconds(); }

inline bool operator>=( const DayTime& t1, const DayTime& t2)
{ return t1.asSeconds() >= t2.asSeconds(); }

inline bool operator==( const DayTime& t1, const DayTime& t2)
{ return t1.asSeconds() == t2.asSeconds(); }

inline bool operator!=( const DayTime& t1, const DayTime& t2)
{ return !(t1==t2); }

///////////////////////////////////////////////////////
//Shiftoperatoren
inline ostream& operator<<( ostream& os, const DayTime& t)
{ 
	cout	<< setfill('0') << setw(2) << t.getHour()
				<< ':' << setw(2) << t.getMinute()
				<< ':' << setw(2) << t.getSecond();
	return os;
}

inline istream& operator>>( istream& is, DayTime& t)
{ 
	char c1=0, c2=0;
	int h=0, m=0, s=0;
	
	is >> h >> c1 >> m >> c2 >> s;

	if( !(c1 == ':' && c2 == ':' && t.setTime(h,m,s) ) )
		is.setstate(ios::failbit);
	return is;
}



#endif