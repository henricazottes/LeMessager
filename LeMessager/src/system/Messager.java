package system;

import packet.Hello;
import network.UDPChatNI;

public class  Messager {
	public static void main(String[] args){
		UDPChatNI chatNI =new UDPChatNI();
		
		if (args[0].equals("C")){			
			chatNI.sendHello();
		}else if (args[0].equals("S")){
			Hello myHelloMsg = (Hello) chatNI.receivePackets();
			System.out.println("L'utilisateur: " + myHelloMsg.getNickname() + " à l'adresse: " + myHelloMsg.getIp() );
		}
		
	}
}
