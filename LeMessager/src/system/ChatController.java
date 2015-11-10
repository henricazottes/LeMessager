package system;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import models.User;

import packet.Bye;
import packet.FileRequest;
import packet.FileResponse;
import packet.Hello;
import packet.HelloBack;
import packet.Message;

public class ChatController {
	private ChatNI chatNI;
	private Object packet;
	private ArrayList<Message> conv;
	private ArrayList<User> userList;
	private InetAddress myIp;
	private String myName;
	
	public ChatController(){		
		try {
			
			this.myIp = InetAddress.getLocalHost();
			this.userList = new ArrayList<User>();
			this.conv = new ArrayList<Message>();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				
				this.userList.add(new User(((Hello) packet).getNickname(),((Hello) packet).getIp()));
				
				// LOG
				System.out.println("New connexion: " + ((Hello) packet).getNickname() + " on " + ((Hello) packet).getIp());
							
				this.chatNI.sendHelloBack(this.myName, this.myIp);
			}else{
				// LOG
				System.out.println(" (local hello)");
			}
			
			
		}else if (packet instanceof HelloBack){				
			
			// Add the user in the user list
			this.userList.add(new User(((HelloBack) packet).getNickname(), ((HelloBack) packet).getIp()));
			
			// LOG
			System.out.println("New user added: " + ((HelloBack) packet).getNickname() + " from: " +((HelloBack) packet).getIp());
			
		}else if (packet instanceof Message){
			
			// Add message in conversation
			this.conv.add((Message) packet);
			
			// LOG
			System.out.println("New message");
			
		}else if (packet instanceof Bye){
			
			// Remove the user from the userlist
			this.userList.remove(new User(((HelloBack) packet).getNickname(), ((HelloBack) packet).getIp()));
			
			// Add a message in the conv.
			this.conv.add(new Message(new Date(), "System", ((Bye) packet).getNickname() + " has left."));
			
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
