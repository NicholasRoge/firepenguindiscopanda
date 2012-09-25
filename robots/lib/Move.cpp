/*
 * Author: Nathan Mann
 * Copyright 2012, All rights reserved
 */I
#include <Move.h>

int Move::_pinServoOne = 0;
int Move::_pinMotorOne = 0;
int Move::_pinMotorTwo = 0;
int Move::_stopNum     = 127; //stop nu
int Move::_forwardNum  = 155; //255 max
int Move::_backwardNum = 100; //0 minim

Move::Move() {
	motorOne.attach(_pinMotorOne);
	motorTwo.attach(_pinMotorTwo);
	servoOne.attach(_pinServoOne);
}

Move::~Move() {
	motorOne.detach();
	motorTwo.detach();
	servoOne.detach();
}

public void lookRight(byte amount) {
	servoOne.write(servoOne.read()+amount);
}

public void lookLeft(byte amount) {
	servoOne.write(servoOne.read()-amount);
}

public void turnLeft(byte amount) {
	motorOne.write(_forwardNum);
	//get degrees rotated;
	//when correct
	stop();
}

public void turnRight(byte amount) {
	motorTwo.write(_forwardNum);
	//get degrees rotated
	//when correct
	stop();
}

public void moveForward(byte amount) {
	motorOne.write(_forwardNum);
	motorTwo.write(_forwardNum);
	delay(amount*10);
	stop();
}

public void moveBackward(byte amount) {
	motorOne.write(_backwardNum);
	motorTwo.write(_backwardNum);
	delay(amount*10);
	stop();
}

public void stop() {
	motorOne.write(_backwardNum);
	motorTwo.write(_backwardNum);
}