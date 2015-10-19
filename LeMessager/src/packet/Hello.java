package packet;
import java.io.Serializable;
import java.net.InetAddress;


public class Hello extends Packet implements Serializable {
	private String nickname;
	private InetAddress ip;
	
	public Hello(String nickname, InetAddress ip){
		this.ip = ip;
		this.nickname = nickname;
	}
	public String getNickname() {
		return this.nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public InetAddress getIp() {
		return this.ip;
	}
	public void setIp(InetAddress ip) {
		this.ip = ip;
	}	
}
