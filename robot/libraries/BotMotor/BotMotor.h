#ifndef botmotor_h
#define botmotor_h
#include <Arduino.h>
#include <Servo.h>

class BotMotor 
{
	public:
		BotMotor();
		~BotMotor();
		void manipMotor();
		Servo motor;
		Servo motor2;
}