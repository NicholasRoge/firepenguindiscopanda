/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann
 * Copyright 2012, All rights reserved.
 */
#include "BotCommand.h"

BotCommand::BotCommand()
{
	Serial.begin(9600);
}

BotCommand::~BotCommand() 
{

}

void getCommand() {
	byte[2] placer;
	for (int i = 0; i < 2; i++) {
		if (Serial.available() > 0) {
			placer[i] = Serial.read();
		}
	}
	parseCommand(placer[0], placer[1]);
}

void parseCommand(byte command1, byte command2) {
	switch (command1) {
		case 'q':
			turnWheelOne(command2);
			break;
		case 'w':
			turnWheelTwo(command2);
			break;
		case 'e': 
			cameraServo(command2);
			break;
		case 'r':
			turnWheelBoth(command2);
			break;
		case 't':
			motorOne(command2);
			break;
		case 'y':
			motorOne(command2);
			break;
		case 'a': 
			motorBoth(command2);
			break;
	}
}

void turnWheelOne(byte[] command) {
	//hand this off to servo class
}

void turnWheelTwo(byte[] command) {
	//hand this off to servo class
}

void turnWheelBoth(byte[] command) {
	//hand this off to servo class
}

void motorOne(byte[] command) {
	//things with motors 1/2
}

void motorTwo(byte[] command) {
	//htof motor class
}

motorBoth(byte[] command) {
	//htof motor class
}

void cameraServo(byte[] command) {
	//things with camera servo
}