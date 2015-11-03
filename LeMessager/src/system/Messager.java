package system;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import packet.Hello;
import network.UDPChatNI;

public class  Messager {
	public static void main(String[] args){
		ChatController cc = null;
		ChatNI chatNI = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("User name ?: ");
		String name = scanner.next();
		
		try {
			
			cc = new ChatController(name, InetAddress.getByName("localhost"));
			chatNI = new ChatNI(cc);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cc.addChatNI(chatNI);		
		cc.processHello();
		
		
		
		
		
		
		
		
		/*Scanner scanner = new Scanner(System.in);
		System.out.print("Mode ? (S)erver / (C)lient: ");
		String mode = scanner.next();
		System.out.print("Port: ");
		int port = Integer.parseInt(scanner.next());
		
		 if (mode.equals("S")){
			 
			 Hello myHelloMsg = (Hello) chatNI.receivePackets();
			 System.out.println("L'utilisateur: " + myHelloMsg.getNickname() + " à l'adresse: " + myHelloMsg.getIp() );
			 			 
		 }else if (mode.equals("C")){
			 
			 chatNI.sendHello("Henri");			 
		 }	*/			
	}
}
