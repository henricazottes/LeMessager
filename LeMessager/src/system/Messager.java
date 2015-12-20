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
                chatGUI = new ChatGUI(cc);
                
                cc.addChatNI(chatNI);
                cc.addChatGUI(chatGUI); 
                cc.addLoginGUI(loginGUI);                
            }            
    }

