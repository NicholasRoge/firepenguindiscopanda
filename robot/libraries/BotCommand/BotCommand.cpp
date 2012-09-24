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

void BotCommand::getCommand() {
	byte[2] placer;
	for (int i = 0; i < 2; i++) {
		if (Serial.available() > 0) {
			placer[i] = Serial.read();
		}
	}
	parseCommand(placer[0], placer[1]);
}

void BotCommand::parseCommand(byte command1, byte command2) {
	switch (command1) {
		case 'q':
			//servo1
			break;
		case 'w':
			//servo2
			break;
		case 'e': 
			//servo3
			break;
		case 'r':
			//servo 1-2
			break;
		case 't':
			//motor 1 (corresponds to servo1)
			break;
		case 'y':
			//motor 2 (corresponds to servo2)
			break;
		case 'a': 
			//motor 1-2 (both servo motors)
			break;
	}
}

void BotCommand::turn(byte[] command) {
	//things with sevrvos 1/2
}

void BotCommand::move(byte[] command) {
	//things with motors 1/2
}

void BotCommand::cam(byte[] command) {
	//things with camera servo
}