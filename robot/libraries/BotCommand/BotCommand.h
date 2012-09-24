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
 		void parseCommand();
 		void turnWheelOne(byte);
 		void turnWheelTwo(byte);
 		void turnWheelBoth(byte);
 		void motorOne(byte);
 		void motorTwo(byte);
 		void motorBoth(byte);
 		void cameraServo(byte);

 		/*
 		 * servos: 0-180 for rotation in degrees
 		 * motors: 0-255 for something or another I guess probably.
 		 */
 		uint8_t servoOne, servoTwo, servoCamera, motorOne, motorTwo;

 };
 #endif