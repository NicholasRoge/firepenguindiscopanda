/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
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
	byte[8] placer;
	for (int i = 0; i < 8; i++) {
		if (Serial.available() > 0) {
			placer[i] = Serial.read();
		}
	}
}

void turn(byte[] command) {
	//things with sevrvos 1/2
}

void move(byte[] command) {
	//things with motors 1/2
}

void cam(byte[] command) {
	//things with camera servo
}