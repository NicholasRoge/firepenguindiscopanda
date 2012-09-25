#include "InterpCommand.h"

InterpCommand::InterpCommand() {
	Serial.begin(9600);
}

InterpCommand::~InterpCommand() {
	Serial.end();
}

public void getCommand() {
	byte[2] command;
	int i = 0;
	while(1) {
		command[0] = Serial.read();
		command[1] = Serial.read();
		validateCommand(command);
		delay(2000); //a moment to perform
		sendData();
	}
}

public void validateCommand(byte[] command) {
	switch (command[0]) {
		case 'q':
			turnRight(command[1]);
			break;
		case 'w':
			turnLeft(command[1]);
			break;
		case 'e':
			moveForward(command[1]);
			break;
		case 'r':
			moveBackward(command[1]);
			break;
	}
}

public void sendData() {
	/* 
	 * Here we will be interfacing with the sensory libraries
	 * /
}