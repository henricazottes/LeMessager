package models;

import java.net.InetAddress;

public class User {
	private String name;
	private InetAddress ip;
	
	public User(String name, InetAddress ip){
		this.name = name;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
}
