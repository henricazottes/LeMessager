	package system;
     
    import java.net.InetAddress;
    import java.net.UnknownHostException;
    import java.util.Scanner;
     
    import gui.LoginGUI;
    import packet.Hello;
    import network.UDPChatNI;
     
    public class  Messager {
            public static void main(String[] args){
                   
                    // Init system components
                   
                    ChatController cc = null;
                    ChatNI chatNI = null;
                    LoginGUI chatGui = null;
                   
                    //Scanner scanner = new Scanner(System.in);
                    //System.out.print("User name ?: ");
                    //String name = scanner.next();
                   
                    cc = new ChatController();
                    chatGui = new LoginGUI(cc);
                    while(cc.getMyName() == ""){} // Tentative pour régler le problème "Exception in threah Thread-0". Il faudrait attendre d'appuyer sur connecter avant de lancer le chatNI.
                    chatNI = new ChatNI(cc);
                   
                    cc.addChatNI(chatNI);
                    cc.addChatGui(chatGui);
                   
                   
                    // Create the
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
                     }      */                     
            }
    }

