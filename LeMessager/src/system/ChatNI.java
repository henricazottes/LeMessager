package system;
 
import java.net.InetAddress;
import java.net.UnknownHostException;

import packet.Hello;
 
import models.User;
import network.TCPChatNI;
import network.UDPChatNI;
 
public class ChatNI {
 
        //private static ChatNI instance;
        private UDPChatNI niUDP;
        private TCPChatNI niTCP;
        private ChatController cc;
 
        public ChatNI(ChatController cc) {
                // Constructor        	
                this.cc = cc;
                this.niUDP = new UDPChatNI(cc);
                
                
                // Start threads for receiving packets
                this.receivePackets();
                // ...
        }
        
        // Un singleton ne peut pas être une interface....
 
        /*public ChatNI getInstance(ChatController cc) { // Singleton method
                if (instance == null) {
                        instance = new ChatNI(cc);
                }
                return instance;
        }
       
        public static void setInstance(ChatNI instance) {
                ChatNI.instance = instance;
        }*/
        
        
        // UDP methods ============================
       
        public void sendHello(String name){
        	this.niUDP.sendHello(name);
        }
        
        public void sendHelloBack(String name, InetAddress ipDest){
        	this.niUDP.sendHelloBack(name, ipDest);
        }
        
        public void sendGoodbye(String name){
        	this.niUDP.sendGoodbye(name);
        }
        
        public void sendMessage(User u, String msg){
        	this.niUDP.sendMessage(u, msg);
        }
        
        public void receivePackets(){
        	new Thread(this.niUDP).start();
        	// new Thread(this.niTCP).start();
        }
        
        public Object getLastPacket(){
        	return this.niUDP.getLastPacket();
        }
        
        // TCP methods ============================
        
        
        
 
}