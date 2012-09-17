/*
 * Library for Serial interfacing for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
#ifndef "PathFinder_h"
#define "PathFinder_h"
#include "Arduino.h"

class PathFinder 
{
	public:
		PathFinder();
		void read();
		void write();

	private:
		static int _rate = 9600;
		int _x, _y , _z;
}

#endif