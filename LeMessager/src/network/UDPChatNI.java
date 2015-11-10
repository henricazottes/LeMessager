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

import packet.Bye;
import packet.Hello;
import packet.HelloBack;
import packet.Packet;

import models.User;

import system.ChatController;
import system.ChatNI;
 
public class UDPChatNI implements Runnable {
	
		private Object lastPacket;
		private ChatController cc;
		
		public UDPChatNI(ChatController cc){
			this.cc = cc;
		}
 
        //public InetAddress getNetworkBroadcastAddresses() {
               
        //}
 
        public void processPacket(DatagramPacket packet) {
               
        }
 
        public void sendHello(String name) {        	  	
			// construct packet
			InetAddress ip = cc.getMyIp();
			Hello myHelloPacket = new Hello(name, ip);
			
			// send packet
			sendBroadcast(myHelloPacket);		
			
			System.out.println("Hello sent.");
        }
        
        public void sendHelloBack(String name, InetAddress ipDest) {        	  	
			// construct packet
			InetAddress ip = cc.getMyIp();
			HelloBack myHelloBackPacket = new HelloBack(name, ip);
			
			// send packet
			sendUnicast(myHelloBackPacket, ipDest);			       	
        }
 
        public void sendGoodbye(String name) {
        	// construct packet
			InetAddress ip = cc.getMyIp();
			Bye myByePacket = new Bye(name, ip);
			
			// send packet
			sendBroadcast(myByePacket);	
        	
        	System.out.println("Goodbye sent.");
        }
 
        public void sendMessage(User u, String msg) {
               
        }
 
        private void sendUnicast(Packet p, InetAddress ipDest) {
        	try {
				
				byte[] sendData = UDPChatNI.serialize(p);
				DatagramPacket udpPacket;
				udpPacket = new DatagramPacket(sendData, sendData.length, ipDest, 42025);
				
				// create UDP socket
				DatagramSocket clientSocket = new DatagramSocket();
			    
				// send packet			    
			    clientSocket.send(udpPacket);
			    
			    //close socket			    
			    clientSocket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	System.out.println(p.getClass() + " sent.");
        }
 
        private void sendBroadcast(Packet p) {        	
			try {
				
				byte[] sendData = UDPChatNI.serialize(p);
				DatagramPacket udpPacket;
				udpPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 42025);
				
				// create UDP socket
				DatagramSocket clientSocket = new DatagramSocket();
			    
				// send packet			    
			    clientSocket.send(udpPacket);
			    
			    //close socket			    
			    clientSocket.close();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
               
        }
        
               
        public void receivePackets(){      	
			try {
				// create udp socket
				DatagramSocket serverSocket = new DatagramSocket(42025);
				serverSocket.setReuseAddress(true);
				
				// create buffer
				byte[] receiveData = new byte[1024];
				
				// wait forever				
	        	while(true)
	            {      
	        		// create new datagramPacket
	        		DatagramPacket udpPacket = new DatagramPacket(receiveData, receiveData.length);
	        		System.out.println("Waiting packet...");
	                serverSocket.receive(udpPacket); // blocking
	                
					this.setLastPacket(deserialize(udpPacket.getData()));
					notifyObs();
	                                         
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
        }

        
        public void notifyObs(){
        	cc.processPackets();
        }
        
        
		public Object getLastPacket() {
			return lastPacket;
		}

		public void setLastPacket(Object lastPacket) {
			this.lastPacket = lastPacket;
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

		@Override
		public void run() {
			// TODO Auto-generated method stub
			receivePackets();			
		}
		


}