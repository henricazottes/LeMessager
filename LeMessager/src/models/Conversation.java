package models;

import java.util.ArrayList;
import packet.Message;

public class Conversation {
	private ArrayList<Message> conv;
	private String convText;
	
	public Conversation(){
		this.conv = new ArrayList<Message>();
		this.convText = "";
	}
	
	public void addMessage(Message m){
		this.conv.add(m);
		this.convText += m.getTime() + " | " + m.getFrom() + ": " + m.getPayload() + "\n";
	}
	
	public Message getLastMessage() {
		if (conv.size() <= 0) {
			return null;
		}
		else {
			return conv.get(conv.size()-1);
		}
	}
	
	@Override
	public String toString(){
		return convText;
	}
}
