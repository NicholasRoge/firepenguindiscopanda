/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
#ifndef cameraservo_h
#define cameraservo_h
#include "Arduino.h"
#include <Servo.h>
class CameraServo
{
	public:
		CameraServo(int pin);
		void write(int degrees);
	private:
		Servo _camera;
		int   _pin;
}
#endif