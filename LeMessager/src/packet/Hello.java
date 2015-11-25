package packet;
import java.net.InetAddress;

// coucou c'est moi
// moi c'est henri
public class Hello extends Packet{
	private String nickname;
	private InetAddress ip;
	static final long serialVersionUID = 43L;
	
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
