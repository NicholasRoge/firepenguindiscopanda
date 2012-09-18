/*
 * Library for servo interaction for firepenguindiscopanda
 * Author: Nathan Mann, Sam Pierce, Nicholas Roge
 * Copyright 2012, All rights reserved.
 */
#include "CameraServo.h"
 
int CameraServo::_servoMin = 0;
int CameraServo::_servoMax = 180;

CameraServo::CameraServo(int pin)
{
  _camera.attach(pin);
  _pin = pin;
}
 
CameraServo::~CameraServo() {
}
 
void CameraServo::write(int degrees) {
  _camera.write(degrees);
}
 
int CameraServo::getCurrent() {
  _current = _camera.read();
  return _current;
}