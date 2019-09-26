/////////////////////////////////////////////////////////////////////////////
// Header DayTime.h für die Definition der Klasse Artikel
/////////////////////////////////////////////////////////////////////////////

#ifndef _DAYTIME_
#define _DAYTIME_

class DayTime
{
	private:
		short hour, minute, second;
		bool overflow;
	public:
		DayTime( int h = 0, int m = 0, int s =0)
		{
			overflow = false;
			if( !setTime( h, m, s ))
				hour = minute = second = 0;
		}
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
		int getHour()		const { return hour; }
		int getMinute()	const { return minute; }
		int getSecond()	const { return second; }
		int asSeconds()	const
		{
			return (60*60*hour + 60*minute + second);
		}
		bool isLess(DayTime t)	const
		{
			return asSeconds() < t.asSeconds();
		}
		void swap( DayTime& t );
		void print();
};

#endif