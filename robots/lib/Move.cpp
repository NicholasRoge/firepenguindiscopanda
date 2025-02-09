/*
 * Author: Nathan Mann
 * Copyright 2012, All rights reserved
 */
#include <Move.h>

int Move::_pinMotorOne = 0;
int Move::_pinMotorTwo = 0;
int Move::_stopNum     = 127; //stop nu
int Move::_forwardNum  = 155; //255 max
int Move::_backwardNum = 100; //0 minim
/* For this project these torque values are not actually quite correct.
 * With the weight of the chassis and hardware we used it turned out the torque numbers
 * had to be lowered quite a bit, as if the robot moved to quickly or jerkily it would
 * cause the capture software to not be able to update the new location of the camera, 
 * causing the capture to fail.
 */

Move::Move() {
	motorOne.attach(_pinMotorOne);
	motorTwo.attach(_pinMotorTwo);
}

Move::~Move() {
	motorOne.detach();
	motorTwo.detach();
}

public void turnLeft(byte amount) {
	motorOne.write(_forwardNum);
	motorTwo.write(_backwardNum);
	//get degrees rotated;
	//when correct
	stop();
}

public void turnRight(byte amount) {
	motorTwo.write(_forwardNum);
	motorOne.write(_backwardNum);
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
