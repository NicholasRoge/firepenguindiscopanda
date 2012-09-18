/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
 #ifndef cameraservo_h
#define cameraservo_h
#include <Arduino.h>
#include <Servo.h>
class CameraServo
{
	public:
		CameraServo(int pin);
		~CameraServo();
		void write(int degrees);
		int getCurrent();
        static int _servoMax;
        static int _servoMin;
	private:
		Servo _camera;
		int   _pin;
		int   _current;
};
#endif