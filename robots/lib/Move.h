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
		void moveForward();
		void moveBackward();
		void turnRight();
		void turnLeft();
		void stop();

	private:
		static int _pinMotorOne;
		static int _pinMotorTwo;
		static int _stopNum;
		static int _forwardNum;
		static int _backwardNum;
		Servo motorOne;
		Servo motorTwo;
}
