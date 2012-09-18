/*
 * Library for Serial interfacing for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
#ifndef PathFinder_h
#define PathFinder_h
#include "Arduino.h"

class PathFinder 
{
	public:
		PathFinder();
		void read();
		void write();
		static int rate;;

	private:
	
		int _x, _y , _z;
}

#endif