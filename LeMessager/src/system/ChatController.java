package system;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import models.Conversation;
import models.User;
import models.UserList;

import packet.Bye;
import packet.FileRequest;
import packet.FileResponse;
import packet.Hello;
import packet.HelloBack;
import packet.Message;

public class ChatController {
	private ChatNI chatNI;
	private Object packet;
	private Conversation conv;
	private UserList userList;
	private InetAddress myIp;
	private String myName;
	
	public ChatController(){		
		try {
			
			this.myIp = InetAddress.getLocalHost();
			try {
		        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		        while (interfaces.hasMoreElements()) {
		            NetworkInterface iface = interfaces.nextElement();
		            // filters out 127.0.0.1 and inactive interfaces
		            if (iface.isLoopback() || !iface.isUp())
		                continue;

		            Enumeration<InetAddress> addresses = iface.getInetAddresses();
		            while(addresses.hasMoreElements()) {
		                this.myIp = addresses.nextElement();		                
		            }
		        }
		    } catch (SocketException e) {
		        throw new RuntimeException(e);
		    }
			// LOG
			System.out.println("My ip: " + this.myIp);
			
			this.userList = new UserList();
			this.conv = new Conversation();
			this.myName = "Henri";
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public InetAddress getMyIp() {
		return myIp;
	}

	public void setMyIp(InetAddress myIp) {
		this.myIp = myIp;
	}

	public void addChatNI(ChatNI chatNI){
		this.chatNI = chatNI;
	}
	
	public void processHello(){
		this.chatNI.sendHello(this.myName);
	}
	
	
	public void processGoodbye(){
		this.chatNI.sendGoodbye(this.myName);
	}
	
	public void processPackets(){
		packet = this.chatNI.getLastPacket();
		
		System.out.println(packet.getClass() + " received.");
		
		if(packet instanceof Hello){
			
			if(!(((Hello) packet).getNickname().equals(this.myName)
							&& ((Hello) packet).getIp().equals(this.myIp))){ // Hello from me
				
				User newUser = new User(((Hello) packet).getNickname(),((Hello) packet).getIp());
				
				if(!this.userList.contains(newUser)){
					this.userList.addUser(newUser);
				}
				
				
				// LOG
				System.out.println(this.userList);
				System.out.println("New connexion: " + ((Hello) packet).getNickname() + " on " + ((Hello) packet).getIp());
							
				this.chatNI.sendHelloBack(this.myName, ((Hello) packet).getIp());
			}else{
				// LOG
				System.out.println(" (local hello)");
			}
			
			
		}else if (packet instanceof HelloBack){		
			
			if(!(((HelloBack) packet).getNickname().equals(this.myName)
					&& ((HelloBack) packet).getIp().equals(this.myIp))){ // HelloBack from me
				// Add the user in the user list
				this.userList.addUser(new User(((HelloBack) packet).getNickname(), ((HelloBack) packet).getIp()));
				// LOG
				System.out.println("New user added: " + ((HelloBack) packet).getNickname() + " from: " +((HelloBack) packet).getIp());
				System.out.println(this.userList);
			}else{
				// LOG
				System.out.println(" (local helloback)");
			}
			
		}else if (packet instanceof Message){
			
			// Add message in conversation
			this.conv.addMessage((Message) packet);
			
			// LOG
			System.out.println("New message");
			
		}else if (packet instanceof Bye){
			
			// Remove the user from the userlist
			this.userList.removeUser(new User(((Bye) packet).getNickname(), ((Bye) packet).getIp()));
			System.out.println(this.userList);
			// Add a message in the conv.
			this.conv.addMessage(new Message(new Date(), "System", ((Bye) packet).getNickname() + " has left."));
			
		}else if (packet instanceof FileRequest){
			
		}else if (packet instanceof FileResponse){
			
		}
		
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

}
