package system;
 
import java.net.InetAddress;
 
import network.TCPChatNI;
import network.UDPChatNI;
 
public class ChatNI {
 
        private static ChatNI instance;
        private UDPChatNI niUDP;
        private TCPChatNI niTCP;
        private ChatController cc;
 
        private void ChatNI(ChatController cc) {
                // Constructor
                this.cc = cc;
                // ...
        }
 
        public ChatNI getInstance() { // Singleton method
                if (instance == null) {
                        instance = new ChatNI();
                }
                return instance;
        }
       
        public UDPChatNI getNiUDP() {
                return niUDP;
        }
 
        public void setNiUDP(UDPChatNI niUDP) {
                this.niUDP = niUDP;
        }
 
        public TCPChatNI getNiTCP() {
                return niTCP;
        }
 
        public void setNiTCP(TCPChatNI niTCP) {
                this.niTCP = niTCP;
        }
 
        public ChatController getChatController() {
                return cc;
        }
 
        public void setChatController(ChatController cc) {
                this.cc = cc;
        }
 
        public static void setInstance(ChatNI instance) {
                ChatNI.instance = instance;
        }
       
        public void start(InetAddress addr) {
               
        }
       
        public void exit() {
                // fermer threads
               
        }
 
}