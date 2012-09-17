/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
#include "Arduino.h"
#include "CameraServo.h"

 CameraServo::CameraServo(int pin)
 {
 	pinMode(pin, OUTPUT);
 	_pin = pin;
 }