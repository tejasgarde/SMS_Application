/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rxtxcommunication;
import gnu.io.*;
import java.io.*;
/**
 *
 * @author dell
 */
public class Rxtxcommunication {

    
	public static void main(String[] s) throws Exception
	{
        	CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM1");
                System.out.print(portIdentifier.getName());
                if(portIdentifier.isCurrentlyOwned())
                {
                    System.out.println("Port is Not Available");
                }
                else
                {
                    SerialPort sPort = (SerialPort)portIdentifier.open("ListPortClass", 200);
                    sPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    OutputStream out = sPort.getOutputStream();
                    InputStream in = sPort.getInputStream();
                    System.out.println("Enter Moble Number ");
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                    String number= br.readLine(); 
                    System.out.println("Enter Message");
                    String message = br.readLine();
                    String mValue = "AT\r";
                    out.write(mValue.getBytes());
                    System.out.println("AT Command Written to Port.");
                    out.flush();
                    System.out.println("Waiting for Reply");
                    Thread.sleep(500);
                    byte mBytesIn [] = new byte[100];
                    in.read(mBytesIn);
                    System.out.println(new String(mBytesIn));
                    mValue ="AT+CMGF=1\r";
                    out.write(mValue.getBytes());
                    Thread.sleep(500);
                    out.flush();
                    in.read(mBytesIn);
                    System.out.println(new String(mBytesIn));
                    System.out.println("Modem set to Text mode");
                    Thread.sleep(500);
                    mValue="AT+CMGS=" + "\"" +number+"\"\r";
                    out.write(mValue.getBytes());
                    Thread.sleep(500);
                    out.flush();
                    in.read(mBytesIn);
                    System.out.println(new String(mBytesIn));
                    mValue=message+"\r";
                    out.write(mValue.getBytes());
                    Thread.sleep(500);
                    out.flush();
                    in.read(mBytesIn);
                    System.out.println(new String(mBytesIn));
                    
                    mValue="\032"+"\r";
                    out.write(mValue.getBytes());
                    out.flush();
                    in.read(mBytesIn);
                    System.out.println(new String(mBytesIn));
                    in.close();
                    out.close();
                    sPort.close();
                 }
                
                
        }
                
			
}

