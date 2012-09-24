/*
 * Author: Nathan Mann
 * Copyright 2012, All rights reserved
 */

#ifndef interpcommand_h
#define interpcommand_h
#include <Arduino.h>

class InterpCommand 
{
	public:
		InterpCommand();
		~InterpCommand();
		void getCommand();
		void validateCommand();
		void sendData();
	}
#endif