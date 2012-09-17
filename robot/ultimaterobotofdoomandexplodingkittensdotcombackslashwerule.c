#include <Servo.h>


Servo cameraYaw;
int cameraYaw_pin = 0;

int x = 0;
int y = 0;
int z = 0;

void setup() {
	cameraYaw.attach(cameraYaw_pin);
}