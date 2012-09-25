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
		void moveForward();
		void moveBackward();
		void turnRight();
		void turnLeft();
		void stop();

	private:
		static int _pinMotorOne;
		static int _pinMotorTwo;
		static int _pinServo;
		static int _stopNum;
		static int _forwardNum;
		static int _backwardNum;
		Servo servoOne;
		Servo motorOne;
		Servo motorTwo;
}
