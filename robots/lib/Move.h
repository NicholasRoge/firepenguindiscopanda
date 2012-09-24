/*
 * Author: Nathan Mann
 * Copyright 2012, All rights reserved
 */
#ifndef move_h
#define move_h
#include <Arduino.h>
#include <Servo.h>

class Move
{
	public:
		Move();
		~Move();
		void lookRight();
		void lookLeft();
		void move();
		void turnRight();
		void turnLeft();

	private:
		static int _pinMotorOne;
		static int _pinMotorTwo;
		static int _pinServo;
		Servo servoOne;
		Servo motorOne;
		Servo motorTwo;
}
