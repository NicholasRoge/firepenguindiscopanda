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
	if (Serial.available() > 0) {
		//need to see some actual input to finish this guy, will do.
	}

}