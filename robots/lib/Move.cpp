/*
 * Author: Nathan Mann
 * Copyright 2012, All rights reserved
 */I
#include <Move.h>

int Move::_pinServoOne = 0;
int Move::_pinMotorOne = 0;
int Move::_pinMotorTwo = 0;
int Move::_stopNum     = 127;
int Move::_forwardNum  = 180;
int Move::_backwardNum = 0; 

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
	//need to get my hands on a motor, the vex motors
	//are actually servos (in desguise!)
}

public void turnRight(byte amount) {

}

public void move(byte amount) {
	//negative numbers indicate backwards
}

public void stop() {
	motorOne.write(_backwardNum);
	motorTwo.write(_backwardNum);
}