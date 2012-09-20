import java.util.Scanner;
import jssc.SerialPort;
import jssc.SerialPortList;
import jssc.SerialPortException;

public class Commander
{
	SerialPort serialPort;
	Scanner    in;

	void Commander() throws InterruptedException {
		this.serialPort = new SerialPort("COM1") //on windows COM# linux /dev/ttyUSB#
		
		try {
			serialPort.openPort();
			serialPort.setParams(9600, 8 , 1 0); //baud rate, size (bits), stop bit, parity
			this.in = new Scanner(System.in);
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}

	public void sendCommand(String command) {
		char buf = command.toCharArray();
		try {
			serialPort.writeByte((byte)buf[0]);
			System.out.println((byte) buf[0] + " <= was sent successfully");
		} catch (SerialPortException ex) {
			System.out.println(ex);
		}
	}

}