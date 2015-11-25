package packet;

import java.net.InetAddress;

public class Bye extends Packet{
	private String nickname;
	private InetAddress ip;
	static final long serialVersionUID = 40L;
	
	public Bye(String nickname, InetAddress ip){
		this.nickname = nickname;
		this.ip = ip;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
}
