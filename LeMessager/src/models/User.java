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
	
	@Override
	public String toString(){
		return this.getName() + this.getIp();
	}
	
	@Override
	public boolean equals(Object object)
	{
	    boolean isEqual= false;
	    
	    if (object != null && object instanceof User)
	    {
	        isEqual = (this.name.equals(((User) object).getName()) && this.ip.equals(((User) object).getIp()));
	    }

	    return isEqual;
	}
}
