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
 		void getCommand(int command);
 };
 #endif