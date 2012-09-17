/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
#include "Adruino.h"

PathFinder::Pathfinder() 
{
	Serial.begin(_rate);
}

PathFinder::read() {
	int incoming;
	if (Serial.available() > 0) {
		incoming = Serial.read();
	}
}

PathFinder::write(int x, int y, int z) {
	Serial.write(x, y, z);
}