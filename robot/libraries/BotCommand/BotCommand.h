/*
 * Library for PC interaction (IE receiving commands etc) for firepenguindiscopanda
 * Author: Nathan Mann
 * Copyright 2012, All rights reserved.
 */

#ifndef botcommand_h
#define botcommand_h
#include <Arduino.h>

 class BotCommand
 {

 	public:
 		BotCommand();
 		~BotCommand();
 		void getCommand();
 		uint8_t servoOne, servoTwo, servoCamera, motorOne, motorTwo;
 		/*
 		 * servos - 0-180 for range of degrees
 		 * motors - some value between 0-255 or something
 		 */
 };
 #endif