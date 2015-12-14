	

    package system;
     
    import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import gui.ChatGUI;
    import gui.LoginGUI;
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
            private ChatGUI chatGUI;
            private LoginGUI loginGUI;
            private Object packet;
            private Conversation conv;
            private HashMap<String, Conversation> convs;
			public HashMap<String, Conversation> getConvs() {
				return convs;
			}

			public void setConvs(HashMap<String, Conversation> convs) {
				this.convs = convs;
			}

			private UserList userList;
			private Boolean notFound;
            private InetAddress myIp;
            private String myName;
            private String OS = System.getProperty("os.name").toLowerCase();
           
            public ChatController(){   
                    try {
                    	this.notFound = true;
                    	this.convs = new HashMap<String, Conversation>();          	
                    	
                       
                        this.myIp = InetAddress.getLocalHost();
                        try {
                        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                        if (OS.indexOf("win") >= 0) {
                        	while (interfaces.hasMoreElements() && notFound) {
                                NetworkInterface iface = interfaces.nextElement();
                                // filters out 127.0.0.1 and inactive interfaces
                                if (iface.isLoopback() || !iface.isUp())
                                    continue;
                                
                                // Search the IP address     
                                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                                while(addresses.hasMoreElements() && notFound) {
                                    this.myIp = addresses.nextElement();  
                                    if(this.myIp.getHostName().toString().substring(1).matches("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")){
                                        this.notFound = false;
                                    }
                                }
                                System.out.println("====");
                            }
                        }
                        else {
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
                        } 
                    } catch (SocketException e) {
                        throw new RuntimeException(e);
                    }
                        // LOG
                        System.out.println("My ip: " + this.myIp);
                       
                        this.userList = new UserList();
                        this.conv = new Conversation();
                        this.myName = "";
                       
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
           
            public void addChatGUI(ChatGUI chatGui){
                    this.chatGUI = chatGui;
            }
            
            public ChatGUI getChatGUI(){
            	return this.chatGUI;
            }
            
            public void addLoginGUI(LoginGUI loginGui){
                this.loginGUI = loginGui;
	        }
	        
	        public LoginGUI getLoginGUI(){
	        	return this.loginGUI;
	        }
           
            public void processHello(){
                    this.chatNI.sendHello(this.myName);
            }
           
           
            public void processGoodbye(){
                    this.chatNI.sendGoodbye(this.myName);
            }
            
            public void sendMessage(User u, Message msg){
            	this.chatNI.sendMessage(u, msg);
            }
            
            public void createBroadcast(){
            	User bc;
				try {
					bc = new User("BROADCAST", InetAddress.getByName("255.255.255.255"));
					this.userList.addUser(bc);
	        		System.out.println("user broadcast crÈÈ");
	        		System.out.println(userList.toString());
	        		this.convs.put("BROADCAST", new Conversation());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		        		
            }
            
            public void createRobot(){
            	User bc;
				try {
					bc = new User("ROBOT", InetAddress.getByName("255.255.255.255"));
					this.userList.addUser(bc);
	        		System.out.println("user broadcast crÈÈ");
	        		System.out.println(userList.toString());
	        		this.convs.put("ROBOT", new Conversation());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		        		
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
                                            this.chatGUI.updateList();
                                            this.convs.put(((Hello) packet).getNickname(), new Conversation());
                                            this.chatGUI.repaint();
                                            //this.chatGUI.updateList(this.getUserListText()); // TEST affichage userlist dans le GUI
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
                                    //this.chatGUI.updateList(this.getUserListText()); // TEST affichage userlist dans le GUI
                                    // LOG
                                    System.out.println("New user added: " + ((HelloBack) packet).getNickname() + " from: " +((HelloBack) packet).getIp());
                                    System.out.println(this.userList);
                                    this.chatGUI.updateList();
                                    this.convs.put(((HelloBack) packet).getNickname(), new Conversation());
                                    this.chatGUI.repaint();
                            }else{
                                    // LOG
                                    System.out.println(" (local helloback)");
                            }
                           
                    }else if (packet instanceof Message){
                           
                            // Add message in conversation
                    	if (!((Message) packet).getIp().equals(this.myIp)) {
                    		User newUser = new User(((Message) packet).getFrom(),((Message) packet).getIp());
                            if(!this.userList.contains(newUser)){
                            	this.userList.addUser(newUser);
                            	this.convs.put(((Message) packet).getFrom(), new Conversation());
                            }
                            if(((Message) packet).isBroadcast()){
                            	this.convs.get("BROADCAST").addMessage((Message) packet);
                            }else{
                            	this.convs.get(((Message) packet).getFrom()).addMessage((Message) packet);
                            }            
                            this.chatGUI.updateConv(this.getConv());                            
                    	}
                            
                           
                            // LOG
                            System.out.println("New message : \n" + ((Message) packet).getPayload());
                           
                    }else if (packet instanceof Bye){
                           
                            // Remove the user from the userlist
                            this.userList.removeUser(new User(((Bye) packet).getNickname(), ((Bye) packet).getIp()));
                            System.out.println(this.userList);
                            // Add a message in the conv.
                            try {
								this.conv.addMessage(new Message(new Date(), "System", ((Bye) packet).getNickname() + " has left.", InetAddress.getByName("0.0.0.0"), true));
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                            this.chatGUI.updateList();
                            this.chatGUI.repaint();
                            
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
            
            public UserList getUserList() {
            	return this.userList;
            }
            
            public Conversation getConv() {
				return this.conv;
			}

			public void setConv(Conversation conv) {
				this.conv = conv;
			}
           
            // M√©thode pour r√©cup√©rer la liste (elle sera envoy√©e au GUI par la m√©thode updateList dans ChatGUI)
           // public String getUserListText() {
            //        return this.getUserList();
            //}
     
    }

