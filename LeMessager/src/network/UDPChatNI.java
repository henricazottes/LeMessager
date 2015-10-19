package network;
 
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import packet.Hello;
import packet.Packet;

import models.User;

import system.ChatNI;
 
public class UDPChatNI extends ChatNI {
 
        //public InetAddress getNetworkBroadcastAddresses() {
               
        //}
 
        public void processPacket(DatagramPacket packet) {
               
        }
 
        public void sendHello() {
        	InetAddress ip;
			try {
				
				// construct packet
				ip = InetAddress.getByName("127.0.0.1");
				Hello myHelloPacket = new Hello("Henri&Alfred", ip);
				byte[] sendData = this.serialize(myHelloPacket);
				DatagramPacket udpPacket = new DatagramPacket(sendData, sendData.length, ip, 42025);
				
				// create UDP socket
				DatagramSocket clientSocket = new DatagramSocket();
			    
				// send packet			    
			    clientSocket.send(udpPacket);
			    
			    //close socket			    
			    clientSocket.close();
			    
			    // LOGS
			    System.out.println("Hello sent.");
			      
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        	
        }
 
        public void sendGoodbye() {
               
        }
 
        public void sendMessage(User u, String mess) {
               
        }
 
        private void sendUnicast(Packet p, InetAddress addr) {
               
        }
 
        private void sendBroadcast(Packet p) {
               
        }
        
        public Object receivePackets(){      	
			try {
				// create udp socket
				DatagramSocket serverSocket = new DatagramSocket(42025);
				
				// create buffer
				byte[] receiveData = new byte[1024];
				
				// wait forever				
	        	while(true)
	            {      
	        		// create new datagramPacket
	        		DatagramPacket udpPacket = new DatagramPacket(receiveData, receiveData.length);
	        		System.out.println("Waiting Hello...");
	                serverSocket.receive(udpPacket);
	                Hello myHello = (Hello) deserialize(udpPacket.getData());
					return myHello;
	                                         
	            }
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
        }

		public static byte[] serialize(Object obj) throws IOException {
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    ObjectOutputStream os = new ObjectOutputStream(out);
		    os.writeObject(obj);
		    return out.toByteArray();
		}
		public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		    ByteArrayInputStream in = new ByteArrayInputStream(data);
		    ObjectInputStream is = new ObjectInputStream(in);
		    return is.readObject();
		}
		


}