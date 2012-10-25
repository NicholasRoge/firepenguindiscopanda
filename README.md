#firepenguindiscopanda
firepenguindiscopanda is an autonomous robot capable of mapping three dimensional areas, determining it's own path, and
sending this data back to a central processor. These 3D maps (point clouds) are then rendered into models that are viewable
with a web applet.
***

##The Physical
The robot uses two fixed position motors to rotate rather than turn, with castors for balance. The fixed position motors are 
also used to propel the robot. It uses an Arduino UNO to interface with the motors, and the central computer mounted to the
chassis. The kinect sensor is mounted via a tripod to the chassis, and interfaces directly to the central computer.
***

##The Logical
* ####The Computer
The central computer is processes all of the video feed from the kinect, analyzes it, converts it into .PLY then sends it to the
server for viewing. The computer also sends instructions on direction and path for the Arduino to use in conjunction with the
low level hardware.

* ####The Server
The server simply plays host to the completed models, and hosts the web applet that is used to view them.

* ####The Arduino
The arduino simply communicates with the base hardware platform, as a medium for the computer.

***