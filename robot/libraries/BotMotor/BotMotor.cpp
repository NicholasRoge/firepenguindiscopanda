#include "BotMotor.h"

BotMotor::BotMotor() {
	motor.attach(_pin);
	motor.write(0);
	motor2.attach(_pin2);
	motor2.write(0);
}

BotMottor::~BotMotor() {

}

void manipMotor(byte command) {
	
}