package system;
 
import java.net.InetAddress;
import java.net.UnknownHostException;

import packet.Hello;
import packet.Message;
import models.User;
import network.TCPChatNI;
import network.UDPChatNI;
 
public class ChatNI {
 
        private UDPChatNI niUDP;
        private TCPChatNI niTCP;
        private ChatController cc;
 
        public ChatNI(ChatController cc) {
                this.cc = cc;
                this.niUDP = new UDPChatNI(cc);
                
                
                // Start threads for receiving packets
                this.receivePackets();
        }
        
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
        
        public void sendMessage(User u, Message msg){
        	this.niUDP.sendMessage(u, msg);
        }
        
        public void receivePackets(){
        	new Thread(this.niUDP).start();
        }
        
        public Object getLastPacket(){
        	return this.niUDP.getLastPacket();
        }
        
        // TCP methods ============================
        
        // TO Do...
        
 
}