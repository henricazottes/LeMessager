	package system;
     
    import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
     
import gui.ChatGUI;
    import gui.LoginGUI;
import packet.Hello;
import network.UDPChatNI;
     
    public class  Messager {    	
	        
            public static void main(String[] args){ 
            	
            	ChatController cc = null;
    	        ChatNI chatNI = null;
    	        LoginGUI loginGUI = null;
    	        ChatGUI chatGUI = null;
                   
                cc = new ChatController();
                chatNI = new ChatNI(cc);  
                loginGUI = new LoginGUI(cc);  
                chatGUI = new ChatGUI();
                
                cc.addChatNI(chatNI);
                cc.addChatGUI(chatGUI); 
                cc.addLoginGUI(loginGUI);
                
               
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
                 }      */                     
            }            
    }

