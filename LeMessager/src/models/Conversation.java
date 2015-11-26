package models;

import java.util.ArrayList;

import designPatterns.Observable;
import packet.Message;

public class Conversation extends Observable {
	private ArrayList<Message> conv;
	
	public Conversation(){
		this.conv = new ArrayList<Message>();
	}
	
	public void addMessage(Message m){
		this.conv.add(m);
		//this.notify();
	}
	
	public Message getLastMessage() {
		if (conv.size() <= 0) {
			return null;
		}
		else {
			return conv.get(conv.size()-1);
		}
	}
}
