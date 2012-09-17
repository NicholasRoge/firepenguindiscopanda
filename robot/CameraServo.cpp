/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
#include "Arduino.h"
#include "CameraServo.h"
#include <Servo.h>

 CameraServo::CameraServo(int pin)
 {
 	_camera.attatch(pin);
 	_pin = pin;
 }

 void CameraServo::write(int degrees) {
 	_camera.write(degrees);
 }