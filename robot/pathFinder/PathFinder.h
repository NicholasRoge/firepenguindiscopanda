/*
 * Library for servo interaction for firepenguindiscopanda
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
}

#endif