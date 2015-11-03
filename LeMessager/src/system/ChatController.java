package system;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

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
	
	public ChatController(String name, InetAddress ip){		
		try {
			
			this.myName = name;
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
			
			this.userList.add(new User(((Hello) packet).getNickname(),((Hello) packet).getIp()));
			
			// LOG
			System.out.println("New connexion: " + ((Hello) packet).getNickname() + " on " + ((Hello) packet).getIp());
						
			this.chatNI.sendHelloBack(this.myName, this.myIp);
			
			
			
		}else if (packet instanceof HelloBack){
			
		}else if (packet instanceof Message){
			
		}else if (packet instanceof Bye){
			
		}else if (packet instanceof FileRequest){
			
		}else if (packet instanceof FileResponse){
			
		}
		
	}

}
